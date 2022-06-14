package com.mac0321.SuperGerenciadorMusical.services;

import java.io.IOException;

import org.apache.hc.core5.http.ParseException;

import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.AbstractModelObject;
import se.michaelthelin.spotify.model_objects.specification.Playlist;
import se.michaelthelin.spotify.requests.data.playlists.CreatePlaylistRequest;

public class CriadorDePlaylist extends ServiçosDoAplicativo {
	
	private CreatePlaylistRequest createPlaylistRequest;
	private String userID = null;
	
	public CriadorDePlaylist(String accessToken) {
		super(accessToken);
		UsuárioAtual user = new UsuárioAtual(accessToken);
		userID = user.executaServiço().getId();
	}

	public AbstractModelObject executaServiço(String nome_da_playlist, boolean serColaborativa, boolean serPública, String descrição) {
		Playlist nova_playlist = null;
	    try {
	    	createPlaylistRequest = spotifyApi.createPlaylist(this.userID, nome_da_playlist).collaborative(serColaborativa).public_(serPública).description(descrição).build();
	        nova_playlist = createPlaylistRequest.execute();
	        System.out.println("A playlist foi criada com sucesso.");
	    }
	    catch (IOException | SpotifyWebApiException | ParseException exception) {
	    	System.out.println("Não foi possível criar a playlist");
	    }
	    return nova_playlist;
	}

}
