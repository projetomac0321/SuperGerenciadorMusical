package com.mac0321.SuperGerenciadorMusical.services;

public interface ServiçoDeConfiguraçãoDePlaylist {

	public int executaServiço(String id, String nome_da_playlist, boolean serColaborativa, boolean serPública, String descrição);
	
}
