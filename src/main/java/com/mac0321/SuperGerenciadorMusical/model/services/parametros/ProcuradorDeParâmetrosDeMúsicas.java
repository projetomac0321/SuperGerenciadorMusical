package com.mac0321.SuperGerenciadorMusical.model.services.parametros;

import java.io.IOException;

import org.apache.hc.core5.http.ParseException;

import com.mac0321.SuperGerenciadorMusical.models.services.ServiçoSpotify;

import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.specification.AudioFeatures;
import se.michaelthelin.spotify.requests.data.tracks.GetAudioFeaturesForSeveralTracksRequest;

public class ProcuradorDeParâmetrosDeMúsicas extends ServiçoSpotify {

	private GetAudioFeaturesForSeveralTracksRequest getAudioFeaturesForSeveralTracksRequest;
	
	public ProcuradorDeParâmetrosDeMúsicas(String accessToken) {
		super(accessToken);
	}

	public AudioFeatures[] executaServiço(String[] ids_das_músicas) {
		AudioFeatures[] paramêtros_das_músicas = null;	
		try {
			this.getAudioFeaturesForSeveralTracksRequest = this.spotifyApi.getAudioFeaturesForSeveralTracks(ids_das_músicas).build();
			paramêtros_das_músicas = this.getAudioFeaturesForSeveralTracksRequest.execute();
			System.out.println("Parâmetros das músicas obtidos com sucesso!");
		} 
		catch(NullPointerException | IOException | SpotifyWebApiException | ParseException exception) {
			System.out.println("Impossível de buscar os parâmetros das músicas!");
		}
		return paramêtros_das_músicas;
	}

}
