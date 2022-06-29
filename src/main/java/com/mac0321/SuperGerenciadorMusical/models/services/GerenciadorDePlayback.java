package com.mac0321.SuperGerenciadorMusical.models.services;

import java.io.IOException;

import org.apache.hc.core5.http.ParseException;

import com.google.gson.JsonParser;

import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;

public class GerenciadorDePlayback extends ServiçoSpotify {
	GeradorDeJson geradorDeJson = new GeradorDeJson();
	
	public GerenciadorDePlayback (String accessToken) {
		super(accessToken);
	}
	
	public void iniciarPlayback(String uriDaMúsica) {
		String[] uriEmVetor = new String[1];
		uriEmVetor[0] = uriDaMúsica;
		try {
			spotifyApi
			.startResumeUsersPlayback()
			.uris(JsonParser.parseString(uriDaMúsica).getAsJsonArray())
			.build()
			.execute();
		} catch (IOException | SpotifyWebApiException | ParseException e) {
			System.out.println("Error: " + e.getMessage());
		} 
	}
	
	public void pausarPlayback () {
		try {
			spotifyApi
			.pauseUsersPlayback()
			.build()
			.execute();
		} catch (IOException | SpotifyWebApiException | ParseException e) {
			System.out.println("Error: " + e.getCause().getMessage());
		} 
	}
}
