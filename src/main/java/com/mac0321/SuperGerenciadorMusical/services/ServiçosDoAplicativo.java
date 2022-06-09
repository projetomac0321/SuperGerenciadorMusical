package com.mac0321.SuperGerenciadorMusical.services;

import org.springframework.stereotype.Service;

import se.michaelthelin.spotify.SpotifyApi;

@Service
public abstract class ServiçosDoAplicativo {

	protected final SpotifyApi spotifyApi;
	ServiçosDoAplicativo(String accessToken){
		spotifyApi = new SpotifyApi.Builder().setAccessToken(accessToken).build();
	}
	
	public abstract void executaServiço();

}
