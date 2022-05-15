package com.mac0321.SuperGerenciadorMusical.services;

import java.io.IOException;
import java.util.Scanner;

import org.apache.hc.core5.http.ParseException;

import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.specification.Playlist;
import se.michaelthelin.spotify.requests.data.playlists.CreatePlaylistRequest;

public class CriadorDePlaylist extends ServiçosDoAplicativo {
	private CreatePlaylistRequest createPlaylistRequest;
	private String userID = null;

	public CriadorDePlaylist(String accessToken) {
		super(accessToken);
		UsuárioAtual usuárioAtual = new UsuárioAtual(accessToken);
		this.userID = usuárioAtual.getUsuárioAtual().getId();
	}

	@Override
	public void ExecutaServiço() {
		Playlist playlistCriada;
		String nomeDaPlaylist, descrição;
		boolean serColaborativa, serPública;
		Scanner sc = new Scanner(System.in);
		System.out.println("Digite o nome da playlist: ");
		nomeDaPlaylist = sc.next();
		System.out.println("Digite true caso a playlist seja coloborativa, digite false caso contrário");
		serColaborativa = sc.nextBoolean();
		System.out.println("Digite true caso a playlist seja pública, digite false caso contrário");
		serPública = sc.nextBoolean();
		System.out.println("Digite a descrição da playlist");
		descrição = sc.next();
		playlistCriada = this.criaPlaylist(nomeDaPlaylist, serColaborativa, serPública, descrição);
		if(playlistCriada == null)
			System.out.println("Infelizmente, foi impossível de criar a playlist");
		else
			System.out.println(playlistCriada);
		sc.close();
	}
	
	public Playlist criaPlaylist(String nomeDaPlaylist, boolean serColaborativa, boolean serPública, String descrição) {
		Playlist novaPlaylist = null;
	
	    try {
	    	createPlaylistRequest = spotifyApi.createPlaylist(this.userID, nomeDaPlaylist).collaborative(serColaborativa).public_(serPública).description(descrição).build();
	        novaPlaylist = createPlaylistRequest.execute();
	    }
	    catch (IOException | SpotifyWebApiException | ParseException exception) {
	    	System.out.println("Error: " + exception.getMessage());
	    }
	    return novaPlaylist;
	  }


}
