package com.mac0321.SuperGerenciadorMusical.services;

import java.io.IOException;

import org.apache.hc.core5.http.ParseException;

import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.requests.data.follow.legacy.UnfollowPlaylistRequest;


public class RemovedorDePlaylists extends ServiçosDoAplicativo {
	
	private UnfollowPlaylistRequest unfollowPlaylistRequest;
	private String userID = null;

	public RemovedorDePlaylists(String accessToken) {
		super(accessToken);
		UsuárioAtual user = new UsuárioAtual(accessToken);
		userID = user.executaServiço().getId();
	}

	public int executaServiço(String playlistID) {
		int sucesso = 0;
	    try {
	    	this.unfollowPlaylistRequest = this.spotifyApi.unfollowPlaylist(this.userID, playlistID).build();
	    	this.unfollowPlaylistRequest.execute();
	    	sucesso = 1;
	    	System.out.println("Playlist removida com sucesso!");
		} 
	    catch (NullPointerException | IOException | SpotifyWebApiException | ParseException exception) {
	    	System.out.println("Essa playlist não existe ou não foi possível removê-la.");
	    }
	    return sucesso;
	}

}
