package com.mac0321.SuperGerenciadorMusical.services;

import java.io.IOException;

import org.apache.hc.core5.http.ParseException;

import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.requests.data.playlists.ChangePlaylistsDetailsRequest;

public class ModificadorDeInformaçõesDaPlaylist extends ServiçosDoAplicativo {

	private ChangePlaylistsDetailsRequest changePlaylistsDetailsRequest;
	
	public ModificadorDeInformaçõesDaPlaylist(String accessToken) {
		super(accessToken);
	}

	public int executaServiço(String playlistID, String nome_da_playlist, boolean serColaborativa, boolean serPública, String descrição) {
		int sucesso = 0;
		try {
			this.changePlaylistsDetailsRequest = this.spotifyApi.changePlaylistsDetails(playlistID).name(nome_da_playlist).collaborative(serColaborativa).public_(serPública).description(descrição).build();
			this.changePlaylistsDetailsRequest.execute();
			sucesso = 1;
		    System.out.println("Playlist modificada com sucesso!");
		} 
		catch (NullPointerException | IOException | SpotifyWebApiException | ParseException exception) {
			System.out.println("Impossível de modificar as informações da playlist");
		}
		return sucesso;
	}

}
