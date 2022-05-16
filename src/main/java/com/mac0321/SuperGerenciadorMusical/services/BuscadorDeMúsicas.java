package com.mac0321.SuperGerenciadorMusical.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.hc.core5.http.ParseException;

import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.specification.Paging;
import se.michaelthelin.spotify.model_objects.specification.Track;
import se.michaelthelin.spotify.requests.data.search.simplified.SearchTracksRequest;


public class BuscadorDeMúsicas {
	private SearchTracksRequest searchTracksRequest;
	private final SpotifyApi spotifyApi;
	
	BuscadorDeMúsicas(String accessToken){
		spotifyApi = new SpotifyApi.Builder().setAccessToken(accessToken).build();
	}
	
	public String[] buscaListaDeURIsDasMúsicasDesejadas() {
		String [] arrayDeURIs;
		String tagDeProcura;
		Scanner sc = new Scanner(System.in);
		System.out.println("Digite o nome da música que você deseja procurar: ");
		tagDeProcura = sc.next();
		arrayDeURIs = this.geraArrayDeURIs(this.geraListaDeMúsicasDesejadas(tagDeProcura));
		return arrayDeURIs;
	}
	
	private Paging<Track> buscaMúsicas(String tagDeProcura, int offset){
		Paging<Track> músicasBuscadas = null;
		try {
			searchTracksRequest = spotifyApi.searchTracks(tagDeProcura).limit(5).offset(offset).build();
			músicasBuscadas = searchTracksRequest.execute();
			
		} 
		catch (IOException | SpotifyWebApiException | ParseException exception) {}
		return músicasBuscadas;
	}

	private List<Track> geraListaDeMúsicasDesejadas(String tagDeProcura){
		Scanner sc = new Scanner(System.in);
		int índiceEscolhido = 0;
		List<Track> listaDeMúsicasBuscadas = new ArrayList<>();
		Paging<Track> novasMúsicasBuscadas = null;
		int offset = 0;
		boolean háMúsicasASeremBuscadas = true;
		while(háMúsicasASeremBuscadas){
			novasMúsicasBuscadas = this.buscaMúsicas(tagDeProcura, offset);
			try {
				if(novasMúsicasBuscadas.getItems().length == 0)
					háMúsicasASeremBuscadas = false;
				else {
					this.imprimeMúsicas(novasMúsicasBuscadas);
					System.out.println("Digite na linha abaixo o número dos índices das músicas que deseja adicionar, caso não deseje adicionar nenhuma digite 5");
					while(índiceEscolhido != 5) {
						índiceEscolhido = sc.nextInt();
						if(índiceEscolhido >= 0 && índiceEscolhido < 5)
							listaDeMúsicasBuscadas.add(novasMúsicasBuscadas.getItems()[índiceEscolhido]);
					}
					índiceEscolhido = 0;
					offset += 5;
					System.out.println("Digite true, caso deseje buscar mais músicas, digite false caso contrário");
					háMúsicasASeremBuscadas = sc.nextBoolean();
				}
			}
			catch (NullPointerException exception) {
				háMúsicasASeremBuscadas = false;
			}
		}
		if(listaDeMúsicasBuscadas.size() == 0)
			System.out.println("Busca não retornou resultados.");
		return listaDeMúsicasBuscadas;
	}

	private void imprimeMúsicas(Paging<Track> pagingDeMúsicas) {
		int contador;
		for(contador = 0; contador < pagingDeMúsicas.getItems().length; contador ++)
			System.out.println("Índice: " + Integer.toString(contador) + " Nome: " +
					pagingDeMúsicas.getItems()[contador].getName() + " Album: " + pagingDeMúsicas.getItems()[contador].getAlbum().getName());
	}
	
	private String [] geraArrayDeURIs(List<Track> listaDeMúsicasBuscadas) {
		String [] arrayDeURIs = new String[listaDeMúsicasBuscadas.size()];
		int contador;
		for (contador = 0; contador < listaDeMúsicasBuscadas.size(); contador ++) 
			arrayDeURIs[contador] = listaDeMúsicasBuscadas.get(contador).getUri();
		return arrayDeURIs;
	}
}
