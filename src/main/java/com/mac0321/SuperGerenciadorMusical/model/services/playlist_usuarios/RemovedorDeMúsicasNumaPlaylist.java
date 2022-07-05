package com.mac0321.SuperGerenciadorMusical.model.services.playlist_usuarios;

import java.io.IOException;

import org.apache.hc.core5.http.ParseException;

import com.mac0321.SuperGerenciadorMusical.models.entities.GeradorDeJson;
import com.mac0321.SuperGerenciadorMusical.models.services.ServiçoSpotify;

import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.special.SnapshotResult;
import se.michaelthelin.spotify.requests.data.playlists.RemoveItemsFromPlaylistRequest;


public class RemovedorDeMúsicasNumaPlaylist extends ServiçoSpotify implements ServiçoDeModificaçãoDeMúsicasDeUmaPlaylist {
	
	private RemoveItemsFromPlaylistRequest removeItemsFromPlaylistRequest;
	private GeradorDeJson geradorDeJson = new GeradorDeJson();
	
	public RemovedorDeMúsicasNumaPlaylist(String accessToken) {
		super(accessToken);	
	}
	
	@Override
	public SnapshotResult executaServiço(String playlistID, String uris[]) {
		SnapshotResult snapshot_playlist_id = null;
		try {
	    	this.removeItemsFromPlaylistRequest = this.spotifyApi.removeItemsFromPlaylist(playlistID, this.geradorDeJson.urisParaJsonArray(uris)).build();
	    	snapshot_playlist_id = this.removeItemsFromPlaylistRequest.execute();
	    	System.out.println("Músicas removidas com sucesso da playlist");
	    } 
	    catch (NullPointerException | IOException | SpotifyWebApiException | ParseException exception) {
	    	System.out.println("Não foi possível excluir as músicas da playlist");
	    }
		return snapshot_playlist_id;
	}
	
}
