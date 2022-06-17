package com.mac0321.SuperGerenciadorMusical.services;

import java.io.IOException;

import org.apache.hc.core5.http.ParseException;

import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.specification.Artist;
import se.michaelthelin.spotify.requests.data.artists.GetArtistRequest;

public class ProcuradorDeArtista extends ServiçosDoAplicativo implements ServiçoDeProcuraÚnica {

	private GetArtistRequest getArtistRequest;
	
	public ProcuradorDeArtista(String accessToken) {
		super(accessToken);
	}

	@Override
	public Artist executaServiço(String id) {
		Artist artista = null;
		try {
			getArtistRequest = spotifyApi.getArtist(id).build();
			artista = getArtistRequest.execute();
		    System.out.println("Artista obtido com sucesso!");
		}
		catch (NullPointerException | IOException | SpotifyWebApiException | ParseException exception) {
		      System.out.println("Impossível de obter o artista");
		 }
		return artista;
	}

}
