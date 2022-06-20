package com.mac0321.SuperGerenciadorMusical.models.services;

import se.michaelthelin.spotify.SpotifyApi;

<<<<<<< HEAD
public class ServiçoSpotify extends ServiçosDoAplicativo {
=======
public abstract class ServiçoSpotify extends ServiçosDoAplicativo {
>>>>>>> etapa3

	protected final SpotifyApi spotifyApi;
	public ServiçoSpotify(String accessToken){
		spotifyApi = new SpotifyApi.Builder().setAccessToken(accessToken).build();
	}

}
