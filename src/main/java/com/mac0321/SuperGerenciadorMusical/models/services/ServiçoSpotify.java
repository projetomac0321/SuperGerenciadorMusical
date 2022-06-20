package com.mac0321.SuperGerenciadorMusical.models.services;

import se.michaelthelin.spotify.SpotifyApi;

public abstract class ServiçoSpotify extends ServiçosDoAplicativo {

	protected final SpotifyApi spotifyApi;
	public ServiçoSpotify(String accessToken){
		spotifyApi = new SpotifyApi.Builder().setAccessToken(accessToken).build();
	}

}
