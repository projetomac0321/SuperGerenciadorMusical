package com.mac0321.SuperGerenciadorMusical.services;

import java.io.IOException;

import org.apache.hc.core5.http.ParseException;

import com.google.gson.JsonArray;

import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.requests.data.follow.legacy.UnfollowPlaylistRequest;


public class RemovedorDePlaylists extends ServiçosDoAplicativo {
	
	private UnfollowPlaylistRequest unfollowPlaylistRequest;
	private GeradorDeJson geradorDeJson;

	public RemovedorDePlaylists(String accessToken) {
		super(accessToken);
		geradorDeJson = new GeradorDeJson();
	}

	public JsonArray executaServiço(String userID, String playlistID) {
		String respostaDaApi = null;
	    try {
	    	unfollowPlaylistRequest = spotifyApi.unfollowPlaylist(userID, playlistID).build();
	    	respostaDaApi = unfollowPlaylistRequest.execute();
	    	System.out.println("Playlist removida com sucesso!");
		} 
	    catch (NullPointerException | IOException | SpotifyWebApiException | ParseException exception) {
	    	System.out.println("Essa playlist não existe ou não foi possível removê-la.");
	    }
	    return this.geradorDeJson.stringParaJsonArray(respostaDaApi);
	}

}
