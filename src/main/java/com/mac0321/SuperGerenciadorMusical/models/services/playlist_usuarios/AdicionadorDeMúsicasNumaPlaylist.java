package com.mac0321.SuperGerenciadorMusical.models.services.playlist_usuarios;

import java.io.IOException;

import org.apache.hc.core5.http.ParseException;

import com.mac0321.SuperGerenciadorMusical.models.services.ServiçoSpotify;

import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.special.SnapshotResult;
import se.michaelthelin.spotify.requests.data.playlists.AddItemsToPlaylistRequest;

public class AdicionadorDeMúsicasNumaPlaylist extends ServiçoSpotify implements ServiçoDeModificaçãoDeMúsicasDeUmaPlaylist {
	
	private AddItemsToPlaylistRequest addItemsToPlaylistRequest;

	public AdicionadorDeMúsicasNumaPlaylist(String accessToken) {
		super(accessToken);
	}
	
	@Override
	public SnapshotResult executaServiço(String playlistID, String uris[]) {
		SnapshotResult snapshot_playlist_id = null;
		try {
			this.addItemsToPlaylistRequest = this.spotifyApi.addItemsToPlaylist(playlistID, uris).build();
			snapshot_playlist_id = this.addItemsToPlaylistRequest.execute();
			System.out.println("Músicas adicionadas na playlist!");
	    } 
		catch (NullPointerException | IOException | SpotifyWebApiException | ParseException exception) {
			System.out.println("Não foi possível adicionar as músicas na playlist");
	    }
		return snapshot_playlist_id;
	}
	
}
