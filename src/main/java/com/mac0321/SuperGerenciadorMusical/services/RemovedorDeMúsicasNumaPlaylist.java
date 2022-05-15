package com.mac0321.SuperGerenciadorMusical.services;

import java.io.IOException;
import java.util.Scanner;

import org.apache.hc.core5.http.ParseException;

import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.special.SnapshotResult;
import se.michaelthelin.spotify.requests.data.playlists.RemoveItemsFromPlaylistRequest;


public class RemovedorDeMúsicasNumaPlaylist extends ServiçosDoAplicativo {
	private RemoveItemsFromPlaylistRequest removeItemsFromPlaylistRequest;
	private BuscadorDeMúsicas buscadorDeMúsicas;
	private BuscadorDeIdDaPlaylist buscadorDeIdDaPlaylist;

	public RemovedorDeMúsicasNumaPlaylist(String accessToken) {
		super(accessToken);	
		buscadorDeMúsicas = new BuscadorDeMúsicas(accessToken);
		buscadorDeIdDaPlaylist = new BuscadorDeIdDaPlaylist(accessToken);

	}
	@Override
	public void ExecutaServiço() {
		Scanner sc = new Scanner(System.in);
		String nomeDaPlaylist, playlistID;
		String [] uris;
		uris = this.buscadorDeMúsicas.buscaListaDeURIsDasMúsicasDesejadas();
		System.out.println("Digite o nome da playlist que deseja adicionar essas músicas: ");
		nomeDaPlaylist = sc.nextLine();
		playlistID = this.buscadorDeIdDaPlaylist.buscaIDdaPlaylist(nomeDaPlaylist);
		this.removeMúsicasDaPlaylist(playlistID, uris);
	}
	
	public SnapshotResult removeMúsicasDaPlaylist(String playlistID, String [] uris) {
		SnapshotResult snapshot_id = null;
		JsonArray músicas = new JsonArray();
	    for(String uri: uris) 
	    	músicas.add(JsonParser.parseString(uri).getAsJsonObject());
		try {
	    	removeItemsFromPlaylistRequest = spotifyApi.removeItemsFromPlaylist(playlistID, músicas).build();
	    	snapshot_id = removeItemsFromPlaylistRequest.execute();
	    	System.out.println("Músicas removidas com sucesso da playlist");
	    } 
	    catch (NullPointerException | IOException | SpotifyWebApiException | ParseException exception) {
	    	System.out.println("Error: " + exception.getMessage());
	    }
	    return snapshot_id;
	  }
}
