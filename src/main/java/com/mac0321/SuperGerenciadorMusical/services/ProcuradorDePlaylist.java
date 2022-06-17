package com.mac0321.SuperGerenciadorMusical.services;

import java.io.IOException;

import org.apache.hc.core5.http.ParseException;

import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.specification.Playlist;
import se.michaelthelin.spotify.requests.data.playlists.GetPlaylistRequest;

public class ProcuradorDePlaylist extends ServiçosDoAplicativo implements ServiçoDeProcuraÚnica {

	private GetPlaylistRequest getPlaylistRequest;

	public ProcuradorDePlaylist(String accessToken) {
		super(accessToken);
	}

	@Override
	public Playlist executaServiço(String id) {
		Playlist playlist_desejada = null;
		try {
			this.getPlaylistRequest = this.spotifyApi.getPlaylist(id).build();
			playlist_desejada = this.getPlaylistRequest.execute();
			System.out.println("PLaylist obtida com sucesso!");
		} 
		catch (NullPointerException| IOException | SpotifyWebApiException | ParseException exception) {
			System.out.println("Não foi possível obter a playlist");
		}
		return playlist_desejada;
	}

}