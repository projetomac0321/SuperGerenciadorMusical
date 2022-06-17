package com.mac0321.SuperGerenciadorMusical.services;

import java.io.IOException;

import org.apache.hc.core5.http.ParseException;

import com.neovisionaries.i18n.CountryCode;

import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.specification.Track;
import se.michaelthelin.spotify.requests.data.artists.GetArtistsTopTracksRequest;

public class ProcuradorDeTopMúsicasDoArtista extends ServiçosDoAplicativo {

	private GetArtistsTopTracksRequest getArtistsTopTracksRequest;
	private CountryCode countryCode;
	
	public ProcuradorDeTopMúsicasDoArtista(String accessToken) {
		super(accessToken);
		countryCode = CountryCode.BR;
	}

	public Track[] executaServiço(String id) {
		Track[] músicas_do_autor = null;
		try {
			getArtistsTopTracksRequest = spotifyApi.getArtistsTopTracks(id, countryCode).build();
			músicas_do_autor = getArtistsTopTracksRequest.execute();
			System.out.println("Músicas do artista obtidas com sucesso");
		}
		catch (NullPointerException| IOException | SpotifyWebApiException | ParseException exception) {
			System.out.println("Não foi possível obter as músicas do artista");
	    }
		return músicas_do_autor;
	}

}
