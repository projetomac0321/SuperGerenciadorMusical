package com.mac0321.SuperGerenciadorMusical.services;

import java.io.IOException;

import org.apache.hc.core5.http.ParseException;

import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.special.SnapshotResult;
import se.michaelthelin.spotify.requests.data.playlists.RemoveItemsFromPlaylistRequest;


public class RemovedorDeMúsicasNumaPlaylist extends ServiçosDoAplicativo implements ServiçoDeModificaçãoDeMúsicasDeUmaPlaylist {
	
	private RemoveItemsFromPlaylistRequest removeItemsFromPlaylistRequest;
	private GeradorDeJson geradorDeJson = new GeradorDeJson();
	
	public RemovedorDeMúsicasNumaPlaylist(String accessToken) {
		super(accessToken);	
	}
	
	@Override
	public SnapshotResult executaServiço(String playlistID, String uris[]) {
		SnapshotResult snapshot_playlist_id = null;
		try {
	    	removeItemsFromPlaylistRequest = spotifyApi.removeItemsFromPlaylist(playlistID, this.geradorDeJson.urisParaJsonArray(uris)).build();
	    	snapshot_playlist_id = removeItemsFromPlaylistRequest.execute();
	    	System.out.println("Músicas removidas com sucesso da playlist");
	    } 
	    catch (NullPointerException | IOException | SpotifyWebApiException | ParseException exception) {
	    	System.out.println("Não foi possível excluir as músicas da playlist");
	    }
		return snapshot_playlist_id;
	}
	
}
