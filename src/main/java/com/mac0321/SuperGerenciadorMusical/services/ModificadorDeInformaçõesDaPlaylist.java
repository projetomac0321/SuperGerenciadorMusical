package com.mac0321.SuperGerenciadorMusical.services;

import java.io.IOException;

import org.apache.hc.core5.http.ParseException;

import com.google.gson.JsonArray;

import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.requests.data.playlists.ChangePlaylistsDetailsRequest;

public class ModificadorDeInformaçõesDaPlaylist extends ServiçosDoAplicativo {

	private ChangePlaylistsDetailsRequest changePlaylistsDetailsRequest;
	private GeradorDeJson geradorDeJson;
	
	ModificadorDeInformaçõesDaPlaylist(String accessToken) {
		super(accessToken);
		geradorDeJson = new GeradorDeJson();
	}

	public JsonArray executaServiço(String playlistID, String nome_da_playlist, boolean serColaborativa, boolean serPública, String descrição) {
		String respostaDaApi = null;
		try {
			changePlaylistsDetailsRequest = spotifyApi.changePlaylistsDetails(playlistID).name(nome_da_playlist).public_(serPública)
					.collaborative(serColaborativa).description(descrição).build();
			respostaDaApi = changePlaylistsDetailsRequest.execute();
		    System.out.println("Playlist modificada com sucesso!");
		} 
		catch (NullPointerException | IOException | SpotifyWebApiException | ParseException exception) {
			System.out.println("Impossível de modificar as informações da playlist");
		}
		return this.geradorDeJson.stringParaJsonArray(respostaDaApi);
	}

}
