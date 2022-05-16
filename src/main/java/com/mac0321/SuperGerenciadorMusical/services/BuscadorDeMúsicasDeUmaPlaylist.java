package com.mac0321.SuperGerenciadorMusical.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.hc.core5.http.ParseException;

import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.specification.Paging;
import se.michaelthelin.spotify.model_objects.specification.PlaylistTrack;
import se.michaelthelin.spotify.model_objects.specification.Track;
import se.michaelthelin.spotify.requests.data.playlists.GetPlaylistsItemsRequest;

public class BuscadorDeMúsicasDeUmaPlaylist {

	private final SpotifyApi spotifyApi;
	private GetPlaylistsItemsRequest getPlaylistsItemsRequest;
	
	BuscadorDeMúsicasDeUmaPlaylist(String accessToken){
		spotifyApi = new SpotifyApi.Builder().setAccessToken(accessToken).build();
	}
	
	public String[] buscaMúsicasDeUmaPlaylist(String playlistID) {
		String [] uris;
		Paging<PlaylistTrack> pagingDeURI = null;
		List<PlaylistTrack> listaDeURIs = new ArrayList<>();
		int contador;
		boolean háMúsicasASeremBuscadas = true;
		Scanner sc = new Scanner(System.in);
		int índiceEscolhido = 0;
		int offset = 0;
		while(háMúsicasASeremBuscadas){
			this.getPlaylistsItemsRequest = spotifyApi.getPlaylistsItems(playlistID).limit(50).offset(offset).additionalTypes("track").build();
			try {
				pagingDeURI = this.getPlaylistsItemsRequest.execute();
			}
			catch (IOException | SpotifyWebApiException | ParseException exception) {
			      System.out.println("Não foi possível obter as músicas da playlist!");
			}
			try {
				if(pagingDeURI.getItems().length == 0)
					háMúsicasASeremBuscadas = false;
				else {
					this.imprimeMúsicas(pagingDeURI);
					System.out.println("Digite na linha abaixo o número dos índices das músicas que deseja deletar, caso não deseje adicionar nenhuma digite 5");
					while(índiceEscolhido != 5) {
						índiceEscolhido = sc.nextInt();
						if(índiceEscolhido >= 0 && índiceEscolhido < 5)
							listaDeURIs.add(pagingDeURI.getItems()[índiceEscolhido]);
					}
					índiceEscolhido = 0;
					offset += 5;
					System.out.println("Digite true, caso deseje buscar mais músicas, digite false caso contrário");
					háMúsicasASeremBuscadas = sc.nextBoolean();
				}
			}
			catch (NullPointerException exception) {
				háMúsicasASeremBuscadas = false;
			}
		}
		if(listaDeURIs.size() == 0)
			System.out.println("Busca não retornou resultados.");
		uris = new String[listaDeURIs.size()];
		for(contador = 0; contador < listaDeURIs.size(); contador ++)
			uris[contador] = listaDeURIs.get(contador).getTrack().getUri();
		return uris;
	}
	
	private void imprimeMúsicas(Paging<PlaylistTrack> pagingDeMúsicas) {
		int contador;
		for(contador = 0; contador < pagingDeMúsicas.getItems().length; contador ++)
			System.out.println("Índice: " + Integer.toString(contador) + " Nome: " +
					pagingDeMúsicas.getItems()[contador].getTrack().getName());
	}
}
