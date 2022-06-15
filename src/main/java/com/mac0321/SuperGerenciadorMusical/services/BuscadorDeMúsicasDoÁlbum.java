package com.mac0321.SuperGerenciadorMusical.services;

import java.io.IOException;

import org.apache.hc.core5.http.ParseException;

import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.AbstractModelObject;
import se.michaelthelin.spotify.model_objects.specification.Paging;
import se.michaelthelin.spotify.model_objects.specification.TrackSimplified;
import se.michaelthelin.spotify.requests.data.albums.GetAlbumsTracksRequest;

public class BuscadorDeMúsicasDoÁlbum extends ServiçosDoAplicativo implements ServiçoDeBusca {

	private GetAlbumsTracksRequest getAlbumsTracksRequest;
	
	BuscadorDeMúsicasDoÁlbum(String accessToken) {
		super(accessToken);
	}

	@Override
	public Paging<TrackSimplified> executaServiço(String tagDeProcura, int offset){
		Paging<TrackSimplified> músicas_do_álbum = null;
		try {
			getAlbumsTracksRequest = this.spotifyApi.getAlbumsTracks(tagDeProcura).limit(50).offset(offset).build();
			músicas_do_álbum = getAlbumsTracksRequest.execute();
		} 
		catch (IOException | SpotifyWebApiException | ParseException exception) {
			System.out.println("Erro ao obter as músicas do álbum!");
		}
		return músicas_do_álbum;
	}

}
