package com.mac0321.SuperGerenciadorMusical.services;

import se.michaelthelin.spotify.model_objects.AbstractModelObject;

public interface ServiçoDeModificaçãoDeMúsicasDeUmaPlaylist {
	
	public abstract AbstractModelObject executaServiço(String playlistID, String uris[]);
	
}
