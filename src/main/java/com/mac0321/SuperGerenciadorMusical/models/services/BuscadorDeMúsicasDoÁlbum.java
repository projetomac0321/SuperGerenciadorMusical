package com.mac0321.SuperGerenciadorMusical.models.services;

import java.io.IOException;

import org.apache.hc.core5.http.ParseException;

import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.specification.Paging;
import se.michaelthelin.spotify.model_objects.specification.TrackSimplified;
import se.michaelthelin.spotify.requests.data.albums.GetAlbumsTracksRequest;

public class BuscadorDeMúsicasDoÁlbum extends ServiçoSpotify implements ServiçoDeBusca {

	private GetAlbumsTracksRequest getAlbumsTracksRequest;
	
	public BuscadorDeMúsicasDoÁlbum(String accessToken) {
		super(accessToken);
	}

	@Override
	public Paging<TrackSimplified> executaServiço(String tagDeProcura, int offset){
		Paging<TrackSimplified> músicas_do_álbum = null;
		try {
			this.getAlbumsTracksRequest = this.spotifyApi.getAlbumsTracks(tagDeProcura).limit(50).offset(offset).build();
			músicas_do_álbum = this.getAlbumsTracksRequest.execute();
			System.out.println("Músicas do álbum obtidas com sucesso!");
		} 
		catch (NullPointerException |IOException | SpotifyWebApiException | ParseException exception) {
			System.out.println("Erro ao obter as músicas do álbum!");
		}
		return músicas_do_álbum;
	}

}
