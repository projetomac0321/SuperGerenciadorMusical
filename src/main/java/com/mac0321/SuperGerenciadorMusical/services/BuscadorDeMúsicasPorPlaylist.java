package com.mac0321.SuperGerenciadorMusical.services;

import java.io.IOException;

import org.apache.hc.core5.http.ParseException;

import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.specification.Paging;
import se.michaelthelin.spotify.model_objects.specification.PlaylistTrack;
import se.michaelthelin.spotify.requests.data.playlists.GetPlaylistsItemsRequest;

public class BuscadorDeMúsicasPorPlaylist extends ServiçoDeBusca {

	private GetPlaylistsItemsRequest getPlaylistsItemsRequest;
	
	BuscadorDeMúsicasPorPlaylist(String accessToken){
		super(accessToken);
	}

	@Override
	public void executaServiço() {
		Paging<PlaylistTrack> músicasBuscadas = null;
		try {
			this.getPlaylistsItemsRequest = spotifyApi.getPlaylistsItems(this.tagDeProcura).limit(50).offset(this.offset).additionalTypes("track").build();
			músicasBuscadas = this.getPlaylistsItemsRequest.execute();
			System.out.println("Músicas buscadas com sucesso!");
		}
		catch (IOException | SpotifyWebApiException | ParseException exception) {
		      System.out.println("Não foi possível obter as músicas da playlist!");
		}
	}
}
