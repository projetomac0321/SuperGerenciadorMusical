package com.mac0321.SuperGerenciadorMusical.services;

import se.michaelthelin.spotify.model_objects.AbstractModelObject;

public interface ServiçoDeBusca {

	public AbstractModelObject executaServiço(String tagDeProcura, int offset);
}
