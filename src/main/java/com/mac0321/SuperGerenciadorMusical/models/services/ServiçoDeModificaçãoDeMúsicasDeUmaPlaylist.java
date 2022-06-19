package com.mac0321.SuperGerenciadorMusical.models.services;

import se.michaelthelin.spotify.model_objects.AbstractModelObject;

public interface ServiçoDeModificaçãoDeMúsicasDeUmaPlaylist {
	
	public abstract AbstractModelObject executaServiço(String playlistID, String uris[]);
	
}
