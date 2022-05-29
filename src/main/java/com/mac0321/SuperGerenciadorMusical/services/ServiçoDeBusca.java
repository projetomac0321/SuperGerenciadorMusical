package com.mac0321.SuperGerenciadorMusical.services;

public abstract class ServiçoDeBusca extends ServiçosDoAplicativo {

	protected String tagDeProcura;
	protected int offset;
	
	ServiçoDeBusca(String accessToken) {
		super(accessToken);
		tagDeProcura = null;
		offset = 0;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public void setTagDeProcura(String tagDeProcura) {
		this.tagDeProcura = tagDeProcura;
	}

}
