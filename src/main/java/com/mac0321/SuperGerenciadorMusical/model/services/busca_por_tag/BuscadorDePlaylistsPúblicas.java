package com.mac0321.SuperGerenciadorMusical.model.services.busca_por_tag;

import java.io.IOException;

import org.apache.hc.core5.http.ParseException;

import com.mac0321.SuperGerenciadorMusical.models.services.ServiçoDeBusca;
import com.mac0321.SuperGerenciadorMusical.models.services.ServiçoSpotify;

import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.specification.Paging;
import se.michaelthelin.spotify.model_objects.specification.PlaylistSimplified;
import se.michaelthelin.spotify.requests.data.search.simplified.SearchPlaylistsRequest;

public class BuscadorDePlaylistsPúblicas extends ServiçoSpotify implements ServiçoDeBusca  {
	
	private SearchPlaylistsRequest searchPlaylistsRequest;
	
	public BuscadorDePlaylistsPúblicas(String accessToken) {
		super(accessToken);
	}

	@Override
	public Paging<PlaylistSimplified> executaServiço(String tagDeProcura, int offset) {
    	Paging<PlaylistSimplified> playlists_públicas = null;
	    try {
	    	searchPlaylistsRequest = this.spotifyApi.searchPlaylists(tagDeProcura).limit(50).offset(offset).build();
	    	playlists_públicas = this.searchPlaylistsRequest.execute();
	    	System.out.println("Playlists buscadas com sucesso!");
	    } 
	    catch (NullPointerException | IOException | SpotifyWebApiException | ParseException exception) {
	      System.out.println("Impossível de buscar mais playlists!");
	    }
	    return playlists_públicas;
	}

}
