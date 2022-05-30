package com.mac0321.SuperGerenciadorMusical.services;

import java.io.IOException;

import org.apache.hc.core5.http.ParseException;

import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.specification.Paging;
import se.michaelthelin.spotify.model_objects.specification.PlaylistSimplified;
import se.michaelthelin.spotify.requests.data.search.simplified.SearchPlaylistsRequest;

public class BuscadorDePlaylistsPúblicas extends ServiçoDeBusca {
	
	private SearchPlaylistsRequest searchPlaylistsRequest;
	
	BuscadorDePlaylistsPúblicas(String accessToken) {
		super(accessToken);
	}

	@Override
	public void ExecutaServiço() {
    	Paging<PlaylistSimplified> playlistSimplifiedPaging = null;
	    try {
	    	searchPlaylistsRequest = this.spotifyApi.searchPlaylists(this.tagDeProcura).limit(50).offset(this.offset).build();
	    	playlistSimplifiedPaging = searchPlaylistsRequest.execute();
	    } 
	    catch (IOException | SpotifyWebApiException | ParseException exception) {
	      System.out.println("Impossível de buscar mais playlists");
	    }
	}

}
