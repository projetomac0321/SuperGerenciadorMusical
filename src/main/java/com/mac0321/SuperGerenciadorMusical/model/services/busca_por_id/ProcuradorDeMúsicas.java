package com.mac0321.SuperGerenciadorMusical.model.services.busca_por_id;

import java.io.IOException;

import org.apache.hc.core5.http.ParseException;

import com.mac0321.SuperGerenciadorMusical.models.services.ServiçoSpotify;
import com.neovisionaries.i18n.CountryCode;

import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.specification.Track;
import se.michaelthelin.spotify.requests.data.tracks.GetSeveralTracksRequest;
import se.michaelthelin.spotify.requests.data.tracks.GetTrackRequest;

public class ProcuradorDeMúsicas extends ServiçoSpotify {
	
	private GetSeveralTracksRequest getSeveralTracksRequest;
	private GetTrackRequest getTrackRequest;
	private CountryCode countryCode;
	
	public ProcuradorDeMúsicas(String accessToken) {
		super(accessToken);
		this.countryCode = CountryCode.BR;
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

	public Track executaServiço(String id) {
		Track música = null;
		try {
			this.getTrackRequest = spotifyApi.getTrack(id).market(this.countryCode).build();
			música = this.getTrackRequest.execute();
			System.out.println("Música obtida com sucesso!");
	    } 
		catch (NullPointerException | IOException | SpotifyWebApiException | ParseException exception) {
			System.out.println("Não foi possível obter a música");
	    }
		return música;
	  }
}
