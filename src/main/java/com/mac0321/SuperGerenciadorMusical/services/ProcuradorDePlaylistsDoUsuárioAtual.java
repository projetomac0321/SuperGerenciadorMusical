package com.mac0321.SuperGerenciadorMusical.services;

import java.io.IOException;

import org.apache.hc.core5.http.ParseException;

import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.specification.Paging;
import se.michaelthelin.spotify.model_objects.specification.PlaylistSimplified;
import se.michaelthelin.spotify.requests.data.playlists.GetListOfCurrentUsersPlaylistsRequest;


public class ProcuradorDePlaylistsDoUsuárioAtual extends ServiçosDoAplicativo {
	
	private GetListOfCurrentUsersPlaylistsRequest getListOfCurrentUsersPlaylistsRequest;
	
	public ProcuradorDePlaylistsDoUsuárioAtual(String accessToken) {
		super(accessToken);
	}
	
	public Paging<PlaylistSimplified> executaServiço(int offset) {
		Paging<PlaylistSimplified> playlists_do_usuário = null;
		try {
			getListOfCurrentUsersPlaylistsRequest = spotifyApi.getListOfCurrentUsersPlaylists().limit(50).offset(offset).build();
			playlists_do_usuário = getListOfCurrentUsersPlaylistsRequest.execute();
			System.out.println("Playlists do usuário obtidas com sucesso!");
	    }
		catch (NullPointerException | IOException | SpotifyWebApiException | ParseException exception) {
			System.out.println("Não foi possível encontrar as playlists do usuário");
	    }
		return playlists_do_usuário;
	}

}