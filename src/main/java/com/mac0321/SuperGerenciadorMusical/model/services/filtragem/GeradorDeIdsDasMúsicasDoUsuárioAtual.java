package com.mac0321.SuperGerenciadorMusical.model.services.filtragem;

import java.util.ArrayList;
import java.util.List;

import com.mac0321.SuperGerenciadorMusical.model.services.busca_por_id.ProcuradorDePlaylist;
import com.mac0321.SuperGerenciadorMusical.model.services.playlist_usuarios.ProcuradorDePlaylistsDoUsuárioAtual;

import se.michaelthelin.spotify.model_objects.specification.Paging;
import se.michaelthelin.spotify.model_objects.specification.Playlist;
import se.michaelthelin.spotify.model_objects.specification.PlaylistSimplified;

public class GeradorDeIdsDasMúsicasDoUsuárioAtual {

	 private ProcuradorDePlaylistsDoUsuárioAtual procuradorDePlaylistsDoUsuárioAtual;
	 private ProcuradorDePlaylist procuradorDePlaylist;
	 private GeradorDeArray geradorDeArray;
	 
	 public GeradorDeIdsDasMúsicasDoUsuárioAtual(String accessToken){
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
		 for(offset = 0; paging_playlist_intermediária.getItems().length > 0 && offset < 1000; offset = offset + 50) {
			 paging_playlist_intermediária = this.procuradorDePlaylistsDoUsuárioAtual.executaServiço(offset);
			 for(contador = 0; contador < paging_playlist_intermediária.getItems().length; contador ++) {
				 playlist = this.procuradorDePlaylist.executaServiço(paging_playlist_intermediária.getItems()[contador].getId());
				 for(contador2 = 0; contador2 < playlist.getTracks().getItems().length; contador2 ++)
					 ids_das_músicas_das_playlists.add(playlist.getTracks().getItems()[contador2].getTrack().getId());
			 }
		 }
		 return this.geradorDeArray.listStringParaArray(ids_das_músicas_das_playlists);
	 }
	 
}
