package com.mac0321.SuperGerenciadorMusical.model.services.busca_por_tag;

import se.michaelthelin.spotify.model_objects.AbstractModelObject;

public interface ServiçoDeBusca {

	public AbstractModelObject executaServiço(String tagDeProcura, int offset);
}
