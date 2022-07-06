package com.mac0321.SuperGerenciadorMusical.models.services.busca.busca_por_id;

import java.io.IOException;

import org.apache.hc.core5.http.ParseException;

import com.mac0321.SuperGerenciadorMusical.models.services.ServiçoSpotify;

import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.specification.Album;
import se.michaelthelin.spotify.requests.data.albums.GetAlbumRequest;

public class ProcuradorDeÁlbum extends ServiçoSpotify implements ServiçoDeProcuraÚnica {

	private GetAlbumRequest getAlbumRequest;
	
	public ProcuradorDeÁlbum(String accessToken) {
		super(accessToken);
	}

	@Override
	public Album executaServiço(String id) {
		Album album_desejado = null;
		try {
			this.getAlbumRequest = this.spotifyApi.getAlbum(id).build();
			album_desejado = this.getAlbumRequest.execute();
			System.out.println("Álbum obtido com sucesso!");
		}
		catch (NullPointerException | IOException | SpotifyWebApiException | ParseException exception) {
			System.out.println("Impossível de obter o álbum!");
	    }
		return album_desejado;
	}

}
