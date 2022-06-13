package com.mac0321.SuperGerenciadorMusical.services;

import java.io.IOException;

import org.apache.hc.core5.http.ParseException;

import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.AbstractModelObject;
import se.michaelthelin.spotify.model_objects.special.SnapshotResult;
import se.michaelthelin.spotify.requests.data.playlists.AddItemsToPlaylistRequest;

public class AdicionadorDeMúsicasNumaPlaylist extends ServiçosDoAplicativo implements ServiçoDeModificaçãoDeMúsicasDeUmaPlaylist {
	
	private AddItemsToPlaylistRequest addItemsToPlaylistRequest;

	public AdicionadorDeMúsicasNumaPlaylist(String accessToken) {
		super(accessToken);
	}
	
	@Override
	public AbstractModelObject executaServiço(String playlistID, String uris[]) {
		SnapshotResult snapshot_playlist_id = null;
		try {
			addItemsToPlaylistRequest = spotifyApi.addItemsToPlaylist(playlistID, uris).build();
			snapshot_playlist_id = addItemsToPlaylistRequest.execute();
			System.out.println("Músicas adicionadas na playlist!");
	    } 
		catch (NullPointerException | IOException | SpotifyWebApiException | ParseException exception) {
			System.out.println("Não foi possível adicionar as músicas na playlist");
	    }
		return snapshot_playlist_id;
	}
	
}
