package com.mac0321.SuperGerenciadorMusical.models.services;

import java.util.ArrayList;
import java.util.List;

import se.michaelthelin.spotify.model_objects.specification.Track;

public class ComparadorDeArraysDeMúsicas {

	GeradorDeArray geradorDeArray = new GeradorDeArray();
	
	public Track[] compara(Track[] músicas1, Track[] músicas2) {
		List<Track> músicas_idênticas = new ArrayList<>();
		int contador, contador2;
		try {
			for(contador = 0; contador < músicas1.length; contador ++)
				for(contador2 = 0; contador2 < músicas2.length; contador ++)
					if(músicas1[contador].getId().equals(músicas2[contador2].getId()))
						músicas_idênticas.add(músicas1[contador]);
		}
		catch(NullPointerException exceção) {
			System.out.println("Impossível de comparar as músicas");
		}
		return this.geradorDeArray.listTrackParaArray(músicas_idênticas);
	}
}
