package com.mac0321.SuperGerenciadorMusical.services;

import java.io.IOException;

import org.apache.hc.core5.http.ParseException;

import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.specification.Artist;
import se.michaelthelin.spotify.model_objects.specification.Paging;
import se.michaelthelin.spotify.requests.data.search.simplified.SearchArtistsRequest;

public class BuscadorDeArtistas extends ServiçosDoAplicativo implements ServiçoDeBusca {

	private SearchArtistsRequest searchArtistsRequest;
	
	public BuscadorDeArtistas(String accessToken) {
		super(accessToken);
	}

	@Override
	public Paging<Artist> executaServiço(String tagDeProcura, int offset) {
		Paging<Artist> artistas_buscados = null;
		try {
			searchArtistsRequest = spotifyApi.searchArtists(tagDeProcura).limit(50).offset(offset).build();
			artistas_buscados = searchArtistsRequest.execute();
			System.out.println("Álbuns obtidos com sucesso!");
		}
		catch (NullPointerException| IOException | SpotifyWebApiException | ParseException exception) {
			System.out.println("Não foi possível obter os álbuns");
		}
		return artistas_buscados;
	}

}
