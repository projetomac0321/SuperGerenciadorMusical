package com.mac0321.SuperGerenciadorMusical.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.hc.core5.http.ParseException;

import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.specification.Paging;
import se.michaelthelin.spotify.model_objects.specification.PlaylistTrack;
import se.michaelthelin.spotify.requests.data.playlists.GetPlaylistsItemsRequest;

public class BuscadorDeMúsicasPorPlaylist {

	private final SpotifyApi spotifyApi;
	private GetPlaylistsItemsRequest getPlaylistsItemsRequest;
	private SelecionadorDeMúsicasPorPlaylist selecionadorDeMúsicasPorPlaylist;
	
	BuscadorDeMúsicasPorPlaylist(String accessToken){
		spotifyApi = new SpotifyApi.Builder().setAccessToken(accessToken).build();
		selecionadorDeMúsicasPorPlaylist = new SelecionadorDeMúsicasPorPlaylist();
	}
	
	public String[] buscaURIsDasMúsicas(String playlistID) {
		String [] uris = null;
		List<PlaylistTrack> listaDeMúsicasBuscadas = new ArrayList<>();
		Paging<PlaylistTrack> novasMúsicasBuscadas = null;
		boolean háMúsicasASeremBuscadas = true;
		int offset = 0;
		while(háMúsicasASeremBuscadas){
			novasMúsicasBuscadas = this.buscaMúsicasDoSpotify(playlistID, offset);
			try {
				háMúsicasASeremBuscadas = this.selecionadorDeMúsicasPorPlaylist.imprimeSeleçãoDeMúsicas(novasMúsicasBuscadas, listaDeMúsicasBuscadas);
				offset += 5;
			}
			catch (NullPointerException exception) {
				háMúsicasASeremBuscadas = false;
			}
		}
		if(listaDeMúsicasBuscadas.size() == 0)
			System.out.println("Busca não retornou resultados.");
		else
			uris = this.geraArrayDeURIs(listaDeMúsicasBuscadas);
		return uris;
	}
	
	private Paging<PlaylistTrack> buscaMúsicasDoSpotify(String playlistID, int offset) {
		Paging<PlaylistTrack> músicasBuscadas = null;
		try {
			this.getPlaylistsItemsRequest = spotifyApi.getPlaylistsItems(playlistID).limit(50).offset(offset).additionalTypes("track").build();
			músicasBuscadas = this.getPlaylistsItemsRequest.execute();
		}
		catch (IOException | SpotifyWebApiException | ParseException exception) {
		      System.out.println("Não foi possível obter as músicas da playlist!");
		}
		return músicasBuscadas;
	}
	
	private String[] geraArrayDeURIs(List<PlaylistTrack> listaDeMúsicasBuscadas) {
		String [] arrayDeURIs = new String[listaDeMúsicasBuscadas.size()];
		int contador;
		for (contador = 0; contador < listaDeMúsicasBuscadas.size(); contador ++) 
			arrayDeURIs[contador] = listaDeMúsicasBuscadas.get(contador).getTrack().getUri();
		return arrayDeURIs;
	}
}
