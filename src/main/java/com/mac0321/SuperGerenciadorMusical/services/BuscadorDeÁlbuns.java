package com.mac0321.SuperGerenciadorMusical.services;

import java.io.IOException;

import org.apache.hc.core5.http.ParseException;

import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.specification.AlbumSimplified;
import se.michaelthelin.spotify.model_objects.specification.Paging;
import se.michaelthelin.spotify.requests.data.search.simplified.SearchAlbumsRequest;

public class BuscadorDeÁlbuns extends ServiçosDoAplicativo implements ServiçoDeBusca {

	private SearchAlbumsRequest searchAlbumsRequest;

	public BuscadorDeÁlbuns(String accessToken) {
		super(accessToken);
	}
	
	@Override
	public Paging<AlbumSimplified> executaServiço(String tagDeProcura, int offset) {
		Paging<AlbumSimplified> album = null;
	    try {
	    	searchAlbumsRequest = this.spotifyApi.searchAlbums(tagDeProcura).limit(50).offset(offset).build();
	    	album = searchAlbumsRequest.execute();
	    } 
	    catch (IOException | SpotifyWebApiException | ParseException exception) {
	    	System.out.println("Impossível de buscar os álbuns ");
	    }
	    return album;
	}

}
