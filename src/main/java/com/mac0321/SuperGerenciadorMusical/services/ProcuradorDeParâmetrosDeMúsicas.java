package com.mac0321.SuperGerenciadorMusical.services;

import java.io.IOException;

import org.apache.hc.core5.http.ParseException;

import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.specification.AudioFeatures;
import se.michaelthelin.spotify.requests.data.tracks.GetAudioFeaturesForSeveralTracksRequest;

public class ProcuradorDeParâmetrosDeMúsicas extends ServiçosDoAplicativo {

	private String[] idsDasMúsicas;	
	private GetAudioFeaturesForSeveralTracksRequest getAudioFeaturesForSeveralTracksRequest;

	ProcuradorDeParâmetrosDeMúsicas(String accessToken) {
		super(accessToken);
		this.idsDasMúsicas = null;
	}

	@Override
	public void executaServiço() {
		AudioFeatures[] audioFeatures;	
		try {
			getAudioFeaturesForSeveralTracksRequest = this.spotifyApi.getAudioFeaturesForSeveralTracks(this.idsDasMúsicas).build();
			audioFeatures = getAudioFeaturesForSeveralTracksRequest.execute();
		} 
		catch(IOException | SpotifyWebApiException | ParseException exception) {
			System.out.println("Impossível de buscar os parâmetros das músicas");
		}
	}
	
	public void setIdsDasMúsicas(String[] idsDasMúsicas) {
		this.idsDasMúsicas = idsDasMúsicas;
	}

}
