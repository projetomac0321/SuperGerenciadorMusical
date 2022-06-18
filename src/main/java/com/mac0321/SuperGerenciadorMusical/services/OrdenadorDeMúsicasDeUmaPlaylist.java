package com.mac0321.SuperGerenciadorMusical.services;

import java.io.IOException;

import org.apache.hc.core5.http.ParseException;

import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.special.SnapshotResult;
import se.michaelthelin.spotify.requests.data.playlists.ReorderPlaylistsItemsRequest;

public class OrdenadorDeMúsicasDeUmaPlaylist extends ServiçosDoAplicativo {

	private ReorderPlaylistsItemsRequest reorderPlaylistsItemsRequest;

	public OrdenadorDeMúsicasDeUmaPlaylist(String accessToken) {
		super(accessToken);
	}

	// Inutilizado
	public SnapshotResult executaServiço(String playlistID, int índice_de_início, int número_de_músicas, int índice_posterior) {
		SnapshotResult snapshot_playlist_id = null;
	    try {
	    	this.reorderPlaylistsItemsRequest = this.spotifyApi.reorderPlaylistsItems(playlistID, índice_de_início, índice_posterior).range_length(número_de_músicas).build();
	    	snapshot_playlist_id = this.reorderPlaylistsItemsRequest.execute();
		    System.out.println("Músicas reordenadas com sucesso!");
		} 
	    catch (NullPointerException | IOException | SpotifyWebApiException | ParseException exception) {
		      System.out.println("Não foi possível reordenar as músicas da playlist!");
		}
		return snapshot_playlist_id;
	}
}
