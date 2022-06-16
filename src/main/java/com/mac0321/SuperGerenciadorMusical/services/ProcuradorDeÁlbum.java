package com.mac0321.SuperGerenciadorMusical.services;

import java.io.IOException;

import org.apache.hc.core5.http.ParseException;

import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.specification.Album;
import se.michaelthelin.spotify.requests.data.albums.GetAlbumRequest;

public class ProcuradorDeÁlbum extends ServiçosDoAplicativo implements ServiçoDeProcuraÚnica {

	private GetAlbumRequest getAlbumRequest;
	
	ProcuradorDeÁlbum(String accessToken) {
		super(accessToken);
	}

	@Override
	public Album executaServiço(String id) {
		Album album_desejado = null;
		try {
			getAlbumRequest = spotifyApi.getAlbum(id).build();
			album_desejado = getAlbumRequest.execute();
			System.out.println("Álbum obtido com sucesso!");
		}
		catch (IOException | SpotifyWebApiException | ParseException exception) {
			System.out.println("Impossível de obter o álbum");
	    }
		return album_desejado;
	}

}
