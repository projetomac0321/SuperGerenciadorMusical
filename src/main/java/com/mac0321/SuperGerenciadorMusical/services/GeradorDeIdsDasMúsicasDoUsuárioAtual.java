package com.mac0321.SuperGerenciadorMusical.services;

import java.util.ArrayList;
import java.util.List;

import se.michaelthelin.spotify.model_objects.specification.Paging;
import se.michaelthelin.spotify.model_objects.specification.Playlist;
import se.michaelthelin.spotify.model_objects.specification.PlaylistSimplified;

public class GeradorDeIdsDasMúsicasDoUsuárioAtual {

	 ProcuradorDePlaylistsDoUsuárioAtual procuradorDePlaylistsDoUsuárioAtual;
	 ProcuradorDePlaylist procuradorDePlaylist;
	 GeradorDeArray geradorDeArray;
	 
	 GeradorDeIdsDasMúsicasDoUsuárioAtual(String accessToken){
		 this.procuradorDePlaylistsDoUsuárioAtual = new ProcuradorDePlaylistsDoUsuárioAtual(accessToken);
		 this.procuradorDePlaylist = new ProcuradorDePlaylist(accessToken);
		 geradorDeArray = new GeradorDeArray();
	 }
	 
	 public String[] obtem_ids(){
		 List<String> ids_das_músicas_das_playlists = new ArrayList<>();
		 Paging<PlaylistSimplified> paging_playlist_intermediária;
		 Playlist playlist;
		 int offset, contador, contador2;
		 paging_playlist_intermediária = this.procuradorDePlaylistsDoUsuárioAtual.executaServiço(0);
		 for(offset = 50; offset > 1000 || paging_playlist_intermediária.getTotal() == 0; offset = offset + 50) {
			 paging_playlist_intermediária = this.procuradorDePlaylistsDoUsuárioAtual.executaServiço(offset);
			 for(contador = 0; contador < paging_playlist_intermediária.getTotal(); contador ++) {
				 playlist = this.procuradorDePlaylist.executaServiço(paging_playlist_intermediária.getItems()[contador].getId());
				 for(contador2 = 0; contador2 < playlist.getTracks().getTotal(); contador2 ++)
					 ids_das_músicas_das_playlists.add(playlist.getTracks().getItems()[contador2].getTrack().getId());
			 }
		 }
		 return this.geradorDeArray.listParaArray(ids_das_músicas_das_playlists);
	 }
	 
}
