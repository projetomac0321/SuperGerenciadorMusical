package com.mac0321.SuperGerenciadorMusical.services;

import java.io.IOException;

import org.apache.hc.core5.http.ParseException;

import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.specification.AlbumSimplified;
import se.michaelthelin.spotify.model_objects.specification.Paging;
import se.michaelthelin.spotify.requests.data.search.simplified.SearchAlbumsRequest;

public class BuscadorDeÁlbuns extends ServiçoDeBusca {

	private SearchAlbumsRequest searchAlbumsRequest;

	BuscadorDeÁlbuns(String accessToken) {
		super(accessToken);
	}
	
	@Override
	public void executaServiço() {
		Paging<AlbumSimplified> albumSimplifiedPaging = null;
	    try {
	    	searchAlbumsRequest = this.spotifyApi.searchAlbums(this.tagDeProcura).limit(50).offset(this.offset).build();
	    	albumSimplifiedPaging = searchAlbumsRequest.execute();
	    } 
	    catch (IOException | SpotifyWebApiException | ParseException exception) {
	    	System.out.println("Impossível de buscar os álbuns ");
	    }
	}

}
