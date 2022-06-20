package com.mac0321.SuperGerenciadorMusical.models.services;

import java.io.IOException;

import org.apache.hc.core5.http.ParseException;

import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.specification.Paging;
import se.michaelthelin.spotify.model_objects.specification.Track;
import se.michaelthelin.spotify.requests.data.search.simplified.SearchTracksRequest;

public class BuscadorDeMúsicasPorTag extends ServiçoSpotify implements ServiçoDeBusca{
	
	private SearchTracksRequest searchTracksRequest;
	
	public BuscadorDeMúsicasPorTag(String accessToken){
		super(accessToken);
	}
	
	@Override
	public Paging<Track> executaServiço(String tagDeProcura, int offset) {
		Paging<Track> músicas_buscadas = null;
		try {
			this.searchTracksRequest = this.spotifyApi.searchTracks(tagDeProcura).limit(50).offset(offset).build();
			músicas_buscadas = this.searchTracksRequest.execute();
			System.out.println("Músicas buscadas com sucesso!");
		} 
		catch (NullPointerException | IOException | SpotifyWebApiException | ParseException exception) {
			System.out.println("Não foi possível buscar as músicas desejadas");
		}
		return músicas_buscadas;
	}

}
