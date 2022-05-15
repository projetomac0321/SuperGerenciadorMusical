package com.mac0321.SuperGerenciadorMusical.services;

import java.util.List;

import se.michaelthelin.spotify.model_objects.specification.Paging;
import se.michaelthelin.spotify.model_objects.specification.PlaylistSimplified;


public class BuscadorDeIdDaPlaylist {
	private PlaylistsDoUsuárioAtual playlistsDoUsuárioAtual;
	private ProcuradorDePlaylist procuradorDePlaylist;
	
	BuscadorDeIdDaPlaylist(String accessToken){
		this.playlistsDoUsuárioAtual = new PlaylistsDoUsuárioAtual(accessToken);
		this.procuradorDePlaylist = new ProcuradorDePlaylist();
	}
	
	public String buscaIDdaPlaylist(String nomeDaPlaylist) {
		String playlistID = null;
		List<Paging<PlaylistSimplified>> playlistsDoUsuário;
		playlistsDoUsuário = this.playlistsDoUsuárioAtual.buscaListaDePlaylistsDoUsuárioAtual();
		try {
			playlistID = procuradorDePlaylist.procuraPlaylist(playlistsDoUsuário, nomeDaPlaylist).getId();
		}
		catch(NullPointerException exception){
			System.out.println("Error: " + exception.getMessage());
		}
		return playlistID;
	}


}
