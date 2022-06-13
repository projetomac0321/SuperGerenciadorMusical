package com.mac0321.SuperGerenciadorMusical.services;

import se.michaelthelin.spotify.model_objects.AbstractModelObject;

public interface ServiçoDeConfiguraçãoDePlaylist {

	public AbstractModelObject executaServiço(String nome_da_playlist, boolean serColaborativa, boolean serPública, String descrição);
	
}
