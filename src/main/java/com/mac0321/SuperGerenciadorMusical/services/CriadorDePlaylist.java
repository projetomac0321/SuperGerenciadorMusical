package com.mac0321.SuperGerenciadorMusical.services;

import java.io.IOException;

import org.apache.hc.core5.http.ParseException;

import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.specification.Playlist;
import se.michaelthelin.spotify.requests.data.playlists.CreatePlaylistRequest;

public class CriadorDePlaylist extends ServiçosDoAplicativo {
	
	private CreatePlaylistRequest createPlaylistRequest;
	private String userID = null;
	private String nomeDaPlaylist;
	private boolean serColaborativa;
	private boolean serPública;
	private String descrição;

	public CriadorDePlaylist(String accessToken) {
		super(accessToken);
		UsuárioAtual usuárioAtual = new UsuárioAtual(accessToken);
		this.userID = usuárioAtual.getUsuárioAtual().getId();
		this.nomeDaPlaylist = null;
		this.serColaborativa = false;
		this.serPública = false;
		this.descrição = null;
	}

	public void setNomeDaPlaylist(String nomeDaPlaylist) {
		this.nomeDaPlaylist = nomeDaPlaylist;
	}

	public void setSerColaborativa(boolean serColaborativa) {
		this.serColaborativa = serColaborativa;
	}

	public void setSerPública(boolean serPública) {
		this.serPública = serPública;
	}

	public void setDescrição(String descrição) {
		this.descrição = descrição;
	}
	
	@Override
	public void executaServiço() {
		Playlist novaPlaylist = null;
	    try {
	    	createPlaylistRequest = spotifyApi.createPlaylist(this.userID, this.nomeDaPlaylist).collaborative(this.serColaborativa).public_(this.serPública).description(this.descrição).build();
	        novaPlaylist = createPlaylistRequest.execute();
	        System.out.println("A playlist foi criada com sucesso.");
	    }
	    catch (IOException | SpotifyWebApiException | ParseException exception) {
	    	System.out.println("Não foi possível criar a playlist");
	    }
	}
	
	// JOGA FORA
	public Playlist criaPlaylist(String nomeDaPlaylist, boolean serColaborativa, boolean serPública, String descrição) {
		Playlist novaPlaylist = null;
	    try {
	    	createPlaylistRequest = spotifyApi.createPlaylist(this.userID, nomeDaPlaylist).collaborative(serColaborativa).public_(serPública).description(descrição).build();
	        novaPlaylist = createPlaylistRequest.execute();
	        System.out.println("A playlist foi criada com sucesso.");
	    }
	    catch (IOException | SpotifyWebApiException | ParseException exception) {
	    	System.out.println("Não foi possível criar a playlist");
	    }
	    return novaPlaylist;
	  }

}
