package com.mac0321.SuperGerenciadorMusical.models.services.playlist_usuarios;

import java.io.IOException;

import org.apache.hc.core5.http.ParseException;

import com.mac0321.SuperGerenciadorMusical.models.entities.UsuárioAtual;
import com.mac0321.SuperGerenciadorMusical.models.services.ServiçoSpotify;

import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.specification.Playlist;
import se.michaelthelin.spotify.requests.data.playlists.CreatePlaylistRequest;

public class CriadorDePlaylist extends ServiçoSpotify {
	
	private CreatePlaylistRequest createPlaylistRequest;
	private String userID = null;
	
	public CriadorDePlaylist(String accessToken) {
		super(accessToken);
		UsuárioAtual user = new UsuárioAtual(accessToken);
		userID = user.executaServiço().getId();
	}

	public Playlist executaServiço(String nome_da_playlist, boolean serColaborativa, boolean serPública, String descrição) {
		Playlist nova_playlist = null;
	    try {
	    	this.createPlaylistRequest = this.spotifyApi.createPlaylist(this.userID, nome_da_playlist).collaborative(serColaborativa).public_(serPública).description(descrição).build();
	        nova_playlist = this.createPlaylistRequest.execute();
	        System.out.println("A playlist foi criada com sucesso!");
	    }
	    catch (NullPointerException | IOException | SpotifyWebApiException | ParseException exception) {
	    	System.out.println("Não foi possível criar a playlist!");
	    }
	    return nova_playlist;
	}

}
