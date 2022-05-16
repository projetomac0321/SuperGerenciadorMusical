package com.mac0321.SuperGerenciadorMusical.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.hc.core5.http.ParseException;

import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.specification.Paging;
import se.michaelthelin.spotify.model_objects.specification.Track;
import se.michaelthelin.spotify.requests.data.search.simplified.SearchTracksRequest;

public class BuscadorDeMúsicasPorTag {
	
	private SearchTracksRequest searchTracksRequest;
	private SelecionadorDeMúsicasPorTag selecionadorDeMúsicasPorTag;
	private final SpotifyApi spotifyApi;
	
	BuscadorDeMúsicasPorTag(String accessToken){
		spotifyApi = new SpotifyApi.Builder().setAccessToken(accessToken).build();
		selecionadorDeMúsicasPorTag = new SelecionadorDeMúsicasPorTag();
	}

	public String[] buscaURIsDasMúsicas(String tagDeProcura){
		String [] uris = null;
		List<Track> listaDeMúsicasBuscadas = new ArrayList<>();
		Paging<Track> novasMúsicasBuscadas = null;
		int offset = 0;
		boolean háMúsicasASeremBuscadas = true;
		while(háMúsicasASeremBuscadas){
			novasMúsicasBuscadas = this.buscaMúsicasDoSpotify(tagDeProcura, offset);
			try {
				háMúsicasASeremBuscadas = this.selecionadorDeMúsicasPorTag.imprimeSeleçãoDeMúsicas(novasMúsicasBuscadas, listaDeMúsicasBuscadas);
				offset += 5;
			}
			catch (NullPointerException exception) {
				háMúsicasASeremBuscadas = false;
			}
		}
		if(listaDeMúsicasBuscadas.size() == 0)
			System.out.println("Busca não retornou resultados.");
		else
			uris = this.geraArrayDeURIs(listaDeMúsicasBuscadas);
		return uris;
	}
	
	private Paging<Track> buscaMúsicasDoSpotify(String tagDeProcura, int offset) {
		Paging<Track> músicasBuscadas = null;
		try {
			searchTracksRequest = spotifyApi.searchTracks(tagDeProcura).limit(5).offset(offset).build();
			músicasBuscadas = searchTracksRequest.execute();
			
		} 
		catch (IOException | SpotifyWebApiException | ParseException exception) {}
		return músicasBuscadas;
	}

	private String[] geraArrayDeURIs(List<Track> listaDeMúsicasBuscadas) {
		String [] arrayDeURIs = new String[listaDeMúsicasBuscadas.size()];
		int contador;
		for (contador = 0; contador < listaDeMúsicasBuscadas.size(); contador ++) 
			arrayDeURIs[contador] = listaDeMúsicasBuscadas.get(contador).getUri();
		return arrayDeURIs;
	}
}
