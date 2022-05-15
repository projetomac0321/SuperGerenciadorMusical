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
			searchTracksRequest = spotifyApi.searchTracks(tagDeProcura).limit(50).offset(offset).includeExternal("audio").build();
			músicasBuscadas = searchTracksRequest.execute();
			System.out.println(músicasBuscadas);
		} 
		catch (IOException | SpotifyWebApiException | ParseException exception) {
			System.out.println("Busca não retornou resultados.");
		}
		return músicasBuscadas;
	}

	private List<Paging<Track>> geraListaDeMúsicasDesejadas(String tagDeProcura){
		List<Paging<Track>> listaDeMúsicasBuscadas = new ArrayList<>();
		Paging<Track> novasMúsicasBuscadas = null;
		int offset = 0;
		boolean háMúsicasASeremBuscadas = true;
		while(háMúsicasASeremBuscadas){
			novasMúsicasBuscadas = this.buscaMúsicas(tagDeProcura, offset);
			try {
				if(novasMúsicasBuscadas.getItems().length == 0)
					háMúsicasASeremBuscadas = false;
				else {
					offset += 50;
					listaDeMúsicasBuscadas.add(novasMúsicasBuscadas);
				}
			}
			catch (NullPointerException exception) {
				háMúsicasASeremBuscadas = false;
			}
		}
		return listaDeMúsicasBuscadas;
	}

	private String [] geraArrayDeURIs(List<Paging<Track>> listaDeMúsicasBuscadas) {
		List<String> listaDeURIs = new ArrayList<>();
		String[] arrayDeURIs;
		int contador;
		for(Paging<Track> pagingDeMúsicas: listaDeMúsicasBuscadas) {
			for(contador = 0; contador < pagingDeMúsicas.getItems().length; contador ++) {
				listaDeURIs.add(pagingDeMúsicas.getItems()[contador].getUri());
			}
		}
		arrayDeURIs = new String[listaDeURIs.size()];
		for (contador = 0; contador < listaDeURIs.size(); contador ++) 
			arrayDeURIs[contador] = listaDeURIs.get(contador);
		return arrayDeURIs;
	}
}
