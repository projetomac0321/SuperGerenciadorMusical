package com.mac0321.SuperGerenciadorMusical.model.services.filtragem;

import java.util.HashMap;

import se.michaelthelin.spotify.model_objects.specification.AudioFeatures;

public class MapeamentoDosParâmetros {

	private HashMap<Integer, Float> mapa = new HashMap<Integer, Float>();
	
	public void atualiza_mapa(AudioFeatures parâmetros_da_música) {
		this.mapa.put(1, parâmetros_da_música.getAcousticness());
		this.mapa.put(2, parâmetros_da_música.getDanceability());
		this.mapa.put(3, (float) parâmetros_da_música.getDurationMs());
		this.mapa.put(4, parâmetros_da_música.getEnergy());
		this.mapa.put(5, parâmetros_da_música.getInstrumentalness());
		this.mapa.put(6, (float) parâmetros_da_música.getKey());
		this.mapa.put(7, parâmetros_da_música.getLiveness());
		this.mapa.put(8, parâmetros_da_música.getLoudness());
		this.mapa.put(9, (float) parâmetros_da_música.getMode().getType());
		this.mapa.put(10, parâmetros_da_música.getSpeechiness());
		this.mapa.put(11, parâmetros_da_música.getTempo());
		this.mapa.put(12, (float) parâmetros_da_música.getTimeSignature());
		this.mapa.put(13, parâmetros_da_música.getValence());	
	}
	
	public float valor_do_mapa(int índice) {
		if(índice > 0 && índice < 14)
			return this.mapa.get(índice);
		return -1;
	}
}
