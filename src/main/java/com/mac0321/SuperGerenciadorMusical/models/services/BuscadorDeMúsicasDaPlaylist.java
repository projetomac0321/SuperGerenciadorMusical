package com.mac0321.SuperGerenciadorMusical.models.services;

import java.io.IOException;

import org.apache.hc.core5.http.ParseException;

import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.specification.Paging;
import se.michaelthelin.spotify.model_objects.specification.PlaylistTrack;
import se.michaelthelin.spotify.requests.data.playlists.GetPlaylistsItemsRequest;

public class BuscadorDeMúsicasDaPlaylist extends ServiçoSpotify implements ServiçoDeBusca {

	GetPlaylistsItemsRequest getPlaylistsItemsRequest;

	public BuscadorDeMúsicasDaPlaylist(String accessToken) {
		super(accessToken);
	}

	@Override
	public Paging<PlaylistTrack> executaServiço(String tagDeProcura, int offset) {
		Paging<PlaylistTrack> músicas_da_playlist = null;
		try {
			this.getPlaylistsItemsRequest = this.spotifyApi.getPlaylistsItems(tagDeProcura).limit(50).offset(offset).build();
			músicas_da_playlist = this.getPlaylistsItemsRequest.execute();
			System.out.println("Músicas da playlist buscadas com sucesso!");
		} 
		catch (NullPointerException |IOException | SpotifyWebApiException | ParseException exception) {
			System.out.println("Impossível de buscar as músicas da playlist");
		}
		return músicas_da_playlist;
	}
}
