package com.mac0321.SuperGerenciadorMusical.model.services.playlist_usuarios;

import se.michaelthelin.spotify.model_objects.AbstractModelObject;

public interface ServiçoDeModificaçãoDeMúsicasDeUmaPlaylist {
	
	public abstract AbstractModelObject executaServiço(String playlistID, String uris[]);
	
}
