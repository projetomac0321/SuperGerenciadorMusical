package com.mac0321.SuperGerenciadorMusical.models.services.playlist_usuarios;

import java.io.IOException;

import org.apache.hc.core5.http.ParseException;

import com.mac0321.SuperGerenciadorMusical.models.services.ServiçoSpotify;

import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.specification.Paging;
import se.michaelthelin.spotify.model_objects.specification.PlaylistSimplified;
import se.michaelthelin.spotify.requests.data.playlists.GetListOfCurrentUsersPlaylistsRequest;


public class ProcuradorDePlaylistsDoUsuárioAtual extends ServiçoSpotify {
	
	private GetListOfCurrentUsersPlaylistsRequest getListOfCurrentUsersPlaylistsRequest;
	
	public ProcuradorDePlaylistsDoUsuárioAtual(String accessToken) {
		super(accessToken);
	}
	
	public Paging<PlaylistSimplified> executaServiço(int offset) {
		Paging<PlaylistSimplified> playlists_do_usuário = null;
		try {
			this.getListOfCurrentUsersPlaylistsRequest = this.spotifyApi.getListOfCurrentUsersPlaylists().limit(50).offset(offset).build();
			playlists_do_usuário = this.getListOfCurrentUsersPlaylistsRequest.execute();
			System.out.println("Playlists do usuário obtidas com sucesso!");
	    }
		catch (NullPointerException | IOException | SpotifyWebApiException | ParseException exception) {
			System.out.println("Não foi possível encontrar as playlists do usuário");
	    }
		return playlists_do_usuário;
	}

}