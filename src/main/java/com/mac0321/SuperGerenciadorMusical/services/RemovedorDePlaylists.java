package com.mac0321.SuperGerenciadorMusical.services;

import java.io.IOException;

import org.apache.hc.core5.http.ParseException;

import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.requests.data.follow.legacy.UnfollowPlaylistRequest;


public class RemovedorDePlaylists extends ServiçosDoAplicativo {
	
	private UnfollowPlaylistRequest unfollowPlaylistRequest;
	private String userID;
	private String playlistID;

	public void setPlaylistID(String playlistID) {
		this.playlistID = playlistID;
	}

	public RemovedorDePlaylists(String accessToken) {
		super(accessToken);
		UsuárioAtual usuárioAtual = new UsuárioAtual(accessToken);
		this.userID = usuárioAtual.getUsuárioAtual().getId();
		this.playlistID = null;
	}

	@Override
	public void executaServiço() {
		String respostaDaApi = null;
	    try {
	    	unfollowPlaylistRequest = spotifyApi.unfollowPlaylist(this.userID,this.playlistID).build();
	    	respostaDaApi = unfollowPlaylistRequest.execute();
	    	System.out.println("Playlist removida com sucesso!");
		} 
	    catch (NullPointerException | IOException | SpotifyWebApiException | ParseException exception) {
	    	System.out.println("Essa playlist não existe ou não foi possível removê-la.");
	    }
	}

}
