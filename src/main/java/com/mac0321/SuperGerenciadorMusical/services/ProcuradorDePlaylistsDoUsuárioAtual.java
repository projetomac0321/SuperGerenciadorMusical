package com.mac0321.SuperGerenciadorMusical.services;

import java.io.IOException;

import org.apache.hc.core5.http.ParseException;

import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.specification.Paging;
import se.michaelthelin.spotify.model_objects.specification.PlaylistSimplified;
import se.michaelthelin.spotify.requests.data.playlists.GetListOfCurrentUsersPlaylistsRequest;


public class ProcuradorDePlaylistsDoUsuárioAtual extends ServiçosDoAplicativo {
	
	private GetListOfCurrentUsersPlaylistsRequest getListOfCurrentUsersPlaylistsRequest;
	private int offset;
	
	public ProcuradorDePlaylistsDoUsuárioAtual(String accessToken) {
		super(accessToken);
		this.offset = 0;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}
	
	@Override
	public void executaServiço() {
		Paging<PlaylistSimplified> playlistsDoUsuário = null;
		try {
			getListOfCurrentUsersPlaylistsRequest = spotifyApi.getListOfCurrentUsersPlaylists().limit(50).offset(this.offset).build();
			playlistsDoUsuário = getListOfCurrentUsersPlaylistsRequest.execute();
			System.out.println("Playlists do usuário obtidas com sucesso!");
	    }
		catch (NullPointerException | IOException | SpotifyWebApiException | ParseException exception) {
			System.out.println("Não foi possível encontrar as playlists do usuário");
	    }
		
	}

}