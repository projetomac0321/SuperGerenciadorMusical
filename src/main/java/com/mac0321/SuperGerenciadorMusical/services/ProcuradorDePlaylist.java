package com.mac0321.SuperGerenciadorMusical.services;

import java.io.IOException;

import org.apache.hc.core5.http.ParseException;

import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.specification.Playlist;
import se.michaelthelin.spotify.requests.data.playlists.GetPlaylistRequest;

public class ProcuradorDePlaylist extends ServiçosDoAplicativo {

	private GetPlaylistRequest getPlaylistRequest;

	public ProcuradorDePlaylist(String accessToken) {
		super(accessToken);
	}

	  public Playlist executaServiço(String playlistID) {
		  Playlist playlist_desejada = null;
		  try {
			  getPlaylistRequest = spotifyApi.getPlaylist(playlistID).build();
			  playlist_desejada = getPlaylistRequest.execute();
	
		      System.out.println("PLaylist obtida com sucesso!");
		    } catch (NullPointerException| IOException | SpotifyWebApiException | ParseException exception) {
		      System.out.println("Não foi possível obter a playlist");
		    }
		  return playlist_desejada;
	  }

}