package com.mac0321.SuperGerenciadorMusical.model.services.busca_por_id;

import java.io.IOException;

import org.apache.hc.core5.http.ParseException;

import com.mac0321.SuperGerenciadorMusical.models.services.ServiçoSpotify;

import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.specification.Artist;
import se.michaelthelin.spotify.requests.data.artists.GetArtistRequest;

public class ProcuradorDeArtista extends ServiçoSpotify implements ServiçoDeProcuraÚnica {

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
