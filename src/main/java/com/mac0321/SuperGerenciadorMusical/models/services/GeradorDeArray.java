package com.mac0321.SuperGerenciadorMusical.models.services;

import java.io.Serializable;
import java.util.List;

import se.michaelthelin.spotify.model_objects.specification.Track;

public class GeradorDeArray {

	@SuppressWarnings("unchecked")
	public <T extends Serializable> T[] listParaArray(List<T> lista) {
		T[] array = (T[]) new Serializable[lista.size()];
		int contador;
		for(contador = 0; contador < lista.size(); contador ++)
			array[contador] = lista.get(contador);
		return array;
	}
	
	public Track[] listParaArray(List<Track[]> lista, int tamanho) {
		Track[] array = new Track[tamanho];
		int contador, contador2 = 0;
		for(Track[] músicas: lista) {
			for(contador = 0; contador < músicas.length; contador ++) {
				array[contador2] = músicas[contador];
				contador2 ++;
			}
		}
		return array;
	}
}
