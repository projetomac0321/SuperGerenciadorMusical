package com.mac0321.SuperGerenciadorMusical.models.services.busca;

import se.michaelthelin.spotify.model_objects.AbstractModelObject;

public interface ServiçoDeBusca {

	public AbstractModelObject executaServiço(String tagDeProcura, int offset);
}
