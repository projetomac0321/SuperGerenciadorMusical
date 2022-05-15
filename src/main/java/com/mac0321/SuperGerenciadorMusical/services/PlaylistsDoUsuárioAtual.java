package com.mac0321.SuperGerenciadorMusical.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.hc.core5.http.ParseException;

import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.specification.Paging;
import se.michaelthelin.spotify.model_objects.specification.PlaylistSimplified;
import se.michaelthelin.spotify.requests.data.playlists.GetListOfCurrentUsersPlaylistsRequest;



public class PlaylistsDoUsuárioAtual extends ServiçosDoAplicativo {
	private GetListOfCurrentUsersPlaylistsRequest getListOfCurrentUsersPlaylistsRequest;
	
	public PlaylistsDoUsuárioAtual(String accessToken) {
		super(accessToken);
	}

	@Override
	public void ExecutaServiço() {
		List<Paging<PlaylistSimplified>> listaDePlaylists;
		listaDePlaylists = this.buscaListaDePlaylistsDoUsuárioAtual();
		int contador;
		for(Paging<PlaylistSimplified> conjuntoDePlaylists: listaDePlaylists) {
			for (contador=0; contador<conjuntoDePlaylists.getTotal(); contador++) {
				System.out.println(conjuntoDePlaylists.getItems()[contador].getName());
			}
		}
		
	}
	
	public List<Paging<PlaylistSimplified>> buscaListaDePlaylistsDoUsuárioAtual(){
		List<Paging<PlaylistSimplified>> listaDePlaylists = new ArrayList<>();
		Paging<PlaylistSimplified> novasPlaylistsBuscadas = null;
		int offset = 0;
		boolean háPlaylistsASeremListadas = true;
		while(háPlaylistsASeremListadas) {
			novasPlaylistsBuscadas = this.buscaPlaylistsDoUsuárioAtual(offset);
			if(novasPlaylistsBuscadas.getItems().length == 0) {
				háPlaylistsASeremListadas = false;
			}
			else {
				listaDePlaylists.add(novasPlaylistsBuscadas);
				offset += 50;	
			}
		}
		return listaDePlaylists;
	}

	private Paging<PlaylistSimplified> buscaPlaylistsDoUsuárioAtual(int offset) {
		Paging<PlaylistSimplified> playlistsDoUsuário = null;
		try {
			getListOfCurrentUsersPlaylistsRequest = spotifyApi.getListOfCurrentUsersPlaylists().limit(50).offset(offset).build();
			playlistsDoUsuário = getListOfCurrentUsersPlaylistsRequest.execute();
	    }
		catch (NullPointerException | IOException | SpotifyWebApiException | ParseException exception) {
			System.out.println("Error: " + exception.getMessage());
	    }
		return playlistsDoUsuário;
	}
}
