package com.mac0321.SuperGerenciadorMusical.services;

import java.io.IOException;

import org.apache.hc.core5.http.ParseException;

import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.requests.data.follow.legacy.UnfollowPlaylistRequest;


public class RemovedorDePlaylists extends ServiçosDoAplicativo {
	
	private UnfollowPlaylistRequest unfollowPlaylistRequest;

	public RemovedorDePlaylists(String accessToken) {
		super(accessToken);
	}

	public int executaServiço(String userID, String playlistID) {
		int sucesso = 0;
	    try {
	    	unfollowPlaylistRequest = spotifyApi.unfollowPlaylist(userID, playlistID).build();
	    	unfollowPlaylistRequest.execute();
	    	sucesso = 1;
	    	System.out.println("Playlist removida com sucesso!");
		} 
	    catch (NullPointerException | IOException | SpotifyWebApiException | ParseException exception) {
	    	System.out.println("Essa playlist não existe ou não foi possível removê-la.");
	    }
	    return sucesso;
	}

}
