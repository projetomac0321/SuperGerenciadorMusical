package com.mac0321.SuperGerenciadorMusical.models.services;

import java.util.List;

import se.michaelthelin.spotify.model_objects.specification.Track;

public class GeradorDeArray {

	public String[] listStringParaArray(List<String> lista) {
		String[] array = new String[lista.size()];
		int contador;
		for(contador = 0; contador < lista.size(); contador ++)
			array[contador] = lista.get(contador);
		return array;
	}
	
	public Track[] listTrackParaArray(List<Track> lista) {
		Track[] array = new Track[lista.size()];
		int contador;
		for(contador = 0; contador < lista.size(); contador ++)
			array[contador] = lista.get(contador);
		return array;
	}
	
	public Track[] listTrackParaArray(List<Track[]> lista, int tamanho) {
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
