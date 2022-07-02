package com.mac0321.SuperGerenciadorMusical.models.services;

import java.io.IOException;

import org.apache.hc.core5.http.ParseException;

import com.google.gson.JsonParser;

import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.miscellaneous.Device;

public class GerenciadorDePlayback extends ServiçoSpotify {
	GeradorDeJson geradorDeJson = new GeradorDeJson();
	Device[] devices;
	
	public GerenciadorDePlayback (String accessToken) {
		super(accessToken);
	}
	
	public void obterDevice () {
		try {
			devices = spotifyApi.getUsersAvailableDevices().build().execute();
			for (int i = 0; i < devices.length; i++) {
				System.out.println(devices[i]);
			}
			
		} catch (IOException | SpotifyWebApiException | ParseException e) {
			System.out.println("Error: " + e.getMessage());
		}
	}
	
	public void iniciarPlayback(String uriDaMúsica) {
		String[] uriEmVetor = new String[1];
		uriEmVetor[0] = uriDaMúsica;
		try {
			spotifyApi
			.startResumeUsersPlayback()
			.device_id(devices[0].getId())
			.uris(JsonParser.parseString("[" + "\"" + uriDaMúsica + "\"]").getAsJsonArray())
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
