package com.mac0321.SuperGerenciadorMusical.services;

import java.io.IOException;
import java.util.Scanner;

import org.apache.hc.core5.http.ParseException;

import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.special.SnapshotResult;
import se.michaelthelin.spotify.requests.data.playlists.AddItemsToPlaylistRequest;

public class AdicionadorDeMúsicasNumaPlaylist extends ServiçosDoAplicativo {
	private AddItemsToPlaylistRequest addItemsToPlaylistRequest;
	private BuscadorDeMúsicas buscadorDeMúsicas;
	private BuscadorDeIdDaPlaylist buscadorDeIdDaPlaylist;


	public AdicionadorDeMúsicasNumaPlaylist(String accessToken) {
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
		nomeDaPlaylist = sc.next();
		playlistID = this.buscadorDeIdDaPlaylist.buscaIDdaPlaylist(nomeDaPlaylist);
		this.adicionaMúsicasNaPlaylist(playlistID, uris);
		sc.close();
	}
	
	public SnapshotResult adicionaMúsicasNaPlaylist(String playlistId, String [] uris) {
		SnapshotResult snapshot_id = null;
		try {
			addItemsToPlaylistRequest = spotifyApi.addItemsToPlaylist(playlistId, uris).build();
			snapshot_id = addItemsToPlaylistRequest.execute();
			System.out.println("Músicas adicionadas na playlist!");
	    } 
		catch (NullPointerException | IOException | SpotifyWebApiException | ParseException exception) {
			System.out.println("Error: " + exception.getMessage());
	    }
		return snapshot_id;
	  }
}
