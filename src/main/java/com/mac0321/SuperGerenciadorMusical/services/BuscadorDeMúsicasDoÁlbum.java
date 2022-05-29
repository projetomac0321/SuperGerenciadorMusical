package com.mac0321.SuperGerenciadorMusical.services;

import java.io.IOException;

import org.apache.hc.core5.http.ParseException;

import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.specification.Paging;
import se.michaelthelin.spotify.model_objects.specification.TrackSimplified;
import se.michaelthelin.spotify.requests.data.albums.GetAlbumsTracksRequest;

public class BuscadorDeMúsicasDoÁlbum extends ServiçoDeBusca {

	private GetAlbumsTracksRequest getAlbumsTracksRequest;
	
	BuscadorDeMúsicasDoÁlbum(String accessToken) {
		super(accessToken);
	}

	@Override
	public void ExecutaServiço() {
		Paging<TrackSimplified> trackSimplifiedPaging = null;
		try {
			getAlbumsTracksRequest = spotifyApi.getAlbumsTracks(this.tagDeProcura).limit(50).offset(this.offset).build();
			trackSimplifiedPaging = getAlbumsTracksRequest.execute();
		} 
		catch (IOException | SpotifyWebApiException | ParseException exception) {
			System.out.println("Erro ao obter as músicas do álbum!");
		}
	}

}
