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
		sc.close();
		return arrayDeURIs;
	}
	
	private Paging<Track> buscaMúsicas(String tagDeProcura, int offset){
		Paging<Track> músicasBuscadas = null;
		try {
			searchTracksRequest = spotifyApi.searchTracks(tagDeProcura).limit(50).offset(offset).includeExternal("audio").build();
			músicasBuscadas = searchTracksRequest.execute();
		} 
		catch (NullPointerException | IOException | SpotifyWebApiException | ParseException exception) {
			System.out.println("Error: " + exception.getMessage());
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
			if(novasMúsicasBuscadas == null)
				háMúsicasASeremBuscadas = false;
			else {
				offset += 50;
				listaDeMúsicasBuscadas.add(novasMúsicasBuscadas);
			}
		}
		return listaDeMúsicasBuscadas;
	}

	private String [] geraArrayDeURIs(List<Paging<Track>> listaDeMúsicasBuscadas) {
		List<String> listaDeURIs = new ArrayList<>();
		Track [] arrayDeMúsicas;
		int contador, tamanhoDoArray;
		for(Paging<Track> pagingDeMúsicas: listaDeMúsicasBuscadas) {
			tamanhoDoArray = pagingDeMúsicas.getLimit();
			arrayDeMúsicas = pagingDeMúsicas.getItems();
			for(contador = 0; contador < tamanhoDoArray; contador ++) {
				listaDeURIs.add(arrayDeMúsicas[contador].getUri());
			}
		}
		return (String[]) listaDeURIs.toArray();
	}
}
