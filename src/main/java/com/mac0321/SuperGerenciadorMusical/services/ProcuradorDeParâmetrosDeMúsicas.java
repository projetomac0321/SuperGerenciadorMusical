package com.mac0321.SuperGerenciadorMusical.services;

import java.io.IOException;

import org.apache.hc.core5.http.ParseException;

import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.AbstractModelObject;
import se.michaelthelin.spotify.model_objects.specification.AudioFeatures;
import se.michaelthelin.spotify.requests.data.tracks.GetAudioFeaturesForSeveralTracksRequest;

public class ProcuradorDeParâmetrosDeMúsicas extends ServiçosDoAplicativo {

	private GetAudioFeaturesForSeveralTracksRequest getAudioFeaturesForSeveralTracksRequest;
	
	ProcuradorDeParâmetrosDeMúsicas(String accessToken) {
		super(accessToken);
	}

	public AbstractModelObject [] executaServiço(String [] ids_das_músicas) {
		AudioFeatures[] paramêtros_das_músicas = null;	
		try {
			getAudioFeaturesForSeveralTracksRequest = this.spotifyApi.getAudioFeaturesForSeveralTracks(ids_das_músicas).build();
			paramêtros_das_músicas = getAudioFeaturesForSeveralTracksRequest.execute();
		} 
		catch(IOException | SpotifyWebApiException | ParseException exception) {
			System.out.println("Impossível de buscar os parâmetros das músicas");
		}
		return paramêtros_das_músicas;
	}

}
