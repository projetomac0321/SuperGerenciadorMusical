package com.mac0321.SuperGerenciadorMusical.model.services.busca_por_tag;

import java.io.IOException;

import org.apache.hc.core5.http.ParseException;

import com.mac0321.SuperGerenciadorMusical.models.services.ServiçoSpotify;

import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.specification.AlbumSimplified;
import se.michaelthelin.spotify.model_objects.specification.Paging;
import se.michaelthelin.spotify.requests.data.search.simplified.SearchAlbumsRequest;

public class BuscadorDeÁlbuns extends ServiçoSpotify implements ServiçoDeBusca {

	private SearchAlbumsRequest searchAlbumsRequest;

	public BuscadorDeÁlbuns(String accessToken) {
		super(accessToken);
	}
	
	@Override
	public Paging<AlbumSimplified> executaServiço(String tagDeProcura, int offset) {
		Paging<AlbumSimplified> album = null;
	    try {
	    	this.searchAlbumsRequest = this.spotifyApi.searchAlbums(tagDeProcura).limit(50).offset(offset).build();
	    	album = this.searchAlbumsRequest.execute();
	    	System.out.println("Álbuns buscados com sucesso!");
	    } 
	    catch (NullPointerException |IOException | SpotifyWebApiException | ParseException exception) {
	    	System.out.println("Impossível de buscar os álbuns ");
	    }
	    return album;
	}

}
