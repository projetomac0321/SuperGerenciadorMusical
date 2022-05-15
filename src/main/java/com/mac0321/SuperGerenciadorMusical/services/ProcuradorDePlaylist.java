package com.mac0321.SuperGerenciadorMusical.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import se.michaelthelin.spotify.model_objects.specification.Paging;
import se.michaelthelin.spotify.model_objects.specification.PlaylistSimplified;


public class ProcuradorDePlaylist {
	public PlaylistSimplified procuraPlaylist(List<Paging<PlaylistSimplified>> listaDePlaylists, String nomeDaPlaylistDesejada) {
		List<PlaylistSimplified> possíveisPlaylistsDesejadas = new ArrayList<>();
		PlaylistSimplified playlistDesejada = null;
		possíveisPlaylistsDesejadas = this.procuraPossíveisPlaylistsDesejadas(listaDePlaylists, nomeDaPlaylistDesejada);
		if(possíveisPlaylistsDesejadas.size() > 0)
			playlistDesejada = this.procuraAPlaylistDesejada(possíveisPlaylistsDesejadas);
		return playlistDesejada;
	}
	
	private List<PlaylistSimplified> procuraPossíveisPlaylistsDesejadas(List<Paging<PlaylistSimplified>> listaDePlaylists, String nomeDaPlaylistDesejada){
		List<PlaylistSimplified> possíveisPlaylistsDesejadas = new ArrayList<>();
		int contador;
		for(Paging<PlaylistSimplified> pagingDePlaylist: listaDePlaylists) {
			for(contador = 0; contador < pagingDePlaylist.getTotal(); contador ++) {
				if(nomeDaPlaylistDesejada.equals(pagingDePlaylist.getItems()[contador].getName()))
					possíveisPlaylistsDesejadas.add(pagingDePlaylist.getItems()[contador]);
			}
		}
		return possíveisPlaylistsDesejadas;
	}

	private PlaylistSimplified procuraAPlaylistDesejada(List<PlaylistSimplified> possíveisPlaylistsDesejadas) {
		PlaylistSimplified playlistDesejada = null;
		Scanner sc = new Scanner(System.in);
		String playlistID = null;
		if(possíveisPlaylistsDesejadas.size() > 1) {
			System.out.println("Há mais de uma playlist com esse nome!");
			System.out.println("Digite o id da playlist desejada entre as mostradas abaixo: ");
			for(PlaylistSimplified playlist: possíveisPlaylistsDesejadas)
				System.out.println(playlist);
			while(playlistDesejada == null) {
				playlistID = sc.next();
				for(PlaylistSimplified playlist: possíveisPlaylistsDesejadas)
					if(playlist.getId().equals(playlistID))
						playlistDesejada = playlist;
				if(playlistDesejada == null)
					System.out.println("Por favor, digite um id válido");
			}
		}
		if(possíveisPlaylistsDesejadas.size() == 1) {
			playlistDesejada = possíveisPlaylistsDesejadas.get(0);
		}
		return playlistDesejada;
	}
}
