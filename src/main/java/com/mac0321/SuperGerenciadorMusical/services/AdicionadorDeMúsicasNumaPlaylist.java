package com.mac0321.SuperGerenciadorMusical.services;

import java.io.IOException;

import org.apache.hc.core5.http.ParseException;

import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.special.SnapshotResult;
import se.michaelthelin.spotify.requests.data.playlists.AddItemsToPlaylistRequest;

public class AdicionadorDeMúsicasNumaPlaylist extends ModificadorDeMúsicasNumaPlaylist {
	
	private AddItemsToPlaylistRequest addItemsToPlaylistRequest;

	public AdicionadorDeMúsicasNumaPlaylist(String accessToken) {
		super(accessToken);
	}
	
	@Override
	public void executaServiço() {
		SnapshotResult snapshot_id = null;
		try {
			addItemsToPlaylistRequest = spotifyApi.addItemsToPlaylist(this.playlistID, this.uris).build();
			snapshot_id = addItemsToPlaylistRequest.execute();
			System.out.println("Músicas adicionadas na playlist!");
	    } 
		catch (NullPointerException | IOException | SpotifyWebApiException | ParseException exception) {
			System.out.println("Não foi possível adicionar as músicas na playlist");
	    }
	}
	
}
