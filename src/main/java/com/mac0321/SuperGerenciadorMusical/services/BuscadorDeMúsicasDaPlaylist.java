package com.mac0321.SuperGerenciadorMusical.services;

import java.io.IOException;

import org.apache.hc.core5.http.ParseException;

import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.specification.Paging;
import se.michaelthelin.spotify.model_objects.specification.PlaylistTrack;
import se.michaelthelin.spotify.requests.data.playlists.GetPlaylistsItemsRequest;

public class BuscadorDeMúsicasDaPlaylist extends ServiçoDeBusca {

	GetPlaylistsItemsRequest getPlaylistsItemsRequest;

	BuscadorDeMúsicasDaPlaylist(String accessToken) {
		super(accessToken);
	}

	@Override
	public void executaServiço() {
		Paging<PlaylistTrack> playlistTrackPaging;
		try {
			getPlaylistsItemsRequest = this.spotifyApi.getPlaylistsItems(this.tagDeProcura).limit(50).offset(this.offset).build();
			playlistTrackPaging = getPlaylistsItemsRequest.execute();
			System.out.println("Músicas da playlist buscadas com sucesso!");
		} 
		catch (IOException | SpotifyWebApiException | ParseException exception) {
			System.out.println("Impossível de buscar as músicas da playlist");
		}
	}
}
