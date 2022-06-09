package com.mac0321.SuperGerenciadorMusical.services;

import java.io.IOException;

import org.apache.hc.core5.http.ParseException;

import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.specification.Paging;
import se.michaelthelin.spotify.model_objects.specification.Track;
import se.michaelthelin.spotify.requests.data.search.simplified.SearchTracksRequest;

public class BuscadorDeMúsicasPorTag extends ServiçoDeBusca{
	
	private SearchTracksRequest searchTracksRequest;
	private String filter;
	
	BuscadorDeMúsicasPorTag(String accessToken){
		super(accessToken);
	}

	public void setFilter(String filter) {
		this.filter = filter;
	}
	
	@Override
	public void executaServiço() {
		Paging<Track> músicasBuscadas = null;
		try {
			searchTracksRequest = spotifyApi.searchTracks(tagDeProcura).limit(50).offset(this.offset).includeExternal(this.filter).build();
			músicasBuscadas = searchTracksRequest.execute();
			System.out.println("Músicas buscadas com sucesso!");
		} 
		catch (IOException | SpotifyWebApiException | ParseException exception) {
			System.out.println("Não foi possível buscar as músicas desejadas");
		}
	}

}
