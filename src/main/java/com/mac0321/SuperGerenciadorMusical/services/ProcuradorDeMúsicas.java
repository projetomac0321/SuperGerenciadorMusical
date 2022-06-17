package com.mac0321.SuperGerenciadorMusical.services;

import java.io.IOException;

import org.apache.hc.core5.http.ParseException;

import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.specification.Track;
import se.michaelthelin.spotify.requests.data.tracks.GetSeveralTracksRequest;

public class ProcuradorDeMúsicas extends ServiçosDoAplicativo {
	
	private  GetSeveralTracksRequest getSeveralTracksRequest;
	
	public ProcuradorDeMúsicas(String accessToken) {
		super(accessToken);
	}

	public Track[] executaServiço(String ids[]) {
		Track[] músicas = null;
		try {
			this.getSeveralTracksRequest = this.spotifyApi.getSeveralTracks(ids).build();
			músicas = this.getSeveralTracksRequest.execute();
			System.out.println("Músicas encontradas com sucesso!");
		}
		catch (NullPointerException | IOException | SpotifyWebApiException | ParseException exception) {
			System.out.println("Impossível de obter as músicas!");
		}
		return músicas;
	}
	  
}
