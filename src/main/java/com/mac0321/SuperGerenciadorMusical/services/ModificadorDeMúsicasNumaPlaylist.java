package com.mac0321.SuperGerenciadorMusical.services;

public abstract class ModificadorDeMúsicasNumaPlaylist extends ServiçosDoAplicativo {

	protected String playlistID;
	protected String [] uris;
	
	ModificadorDeMúsicasNumaPlaylist(String accessToken) {
		super(accessToken);
		this.playlistID = null;
		this.uris = null;
	}

	public void setPlaylistID(String playlistID) {
		this.playlistID = playlistID;
	}

	public void setUris(String[] uris) {
		this.uris = uris;
	}
	
}
