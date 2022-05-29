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
	private BuscadorDeIdDaPlaylist buscadorDeIdDaPlaylist;
	private BuscadorDeMúsicasPorPlaylist buscadorDeMúsicasPorPlaylist;
	
	public RemovedorDeMúsicasNumaPlaylist(String accessToken) {
		super(accessToken);	
		buscadorDeMúsicasPorPlaylist = new BuscadorDeMúsicasPorPlaylist(accessToken);
		buscadorDeIdDaPlaylist = new BuscadorDeIdDaPlaylist(accessToken);
	}
	
	@Override
	public void ExecutaServiço() {
		Scanner sc = new Scanner(System.in);
		String nomeDaPlaylist, playlistID = null;
		String [] uris;
		while (playlistID == null) {
			System.out.println("Digite o nome da playlist que deseja remover essas músicas: ");
			nomeDaPlaylist = sc.nextLine();
			playlistID = this.buscadorDeIdDaPlaylist.buscaIDdaPlaylist(nomeDaPlaylist);
		}
		uris = this.buscadorDeMúsicasPorPlaylist.buscaURIsDasMúsicas(playlistID);
		try {
			this.removeMúsicasDaPlaylist(playlistID, uris);
		}
		catch(NullPointerException exception) {
			System.out.println("Não há músicas a serem excluídas!");
		}
	}

	private SnapshotResult removeMúsicasDaPlaylist(String playlistID, String [] uris) {
		SnapshotResult snapshot_id = null;
		JsonArray músicas;
		String urisEmString = "[";
		
	    for(int contador = 0; contador < uris.length; contador ++) {
	    	if (contador != uris.length - 1)
	    		urisEmString += "{\"uri\":\"" + uris[contador] + "\"},";
	    	else
	    		urisEmString += "{\"uri\":\"" + uris[contador] + "\"}";
	    } 
	    urisEmString += "]";
		try {
	    	músicas = JsonParser.parseString(urisEmString).getAsJsonArray();
	    	removeItemsFromPlaylistRequest = spotifyApi.removeItemsFromPlaylist(playlistID, músicas).build();
	    	snapshot_id = removeItemsFromPlaylistRequest.execute();
	    	System.out.println("Músicas removidas com sucesso da playlist");
	    } 
	    catch (NullPointerException | IOException | SpotifyWebApiException | ParseException exception) {
	    	System.out.println("Não foi possível excluir as músicas da playlist");
	    }
	    return snapshot_id;
	  }
}
