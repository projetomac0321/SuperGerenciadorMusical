package com.mac0321.SuperGerenciadorMusical.models.services;

import java.util.ArrayList;
import java.util.List;

import se.michaelthelin.spotify.model_objects.specification.Track;
import se.michaelthelin.spotify.model_objects.specification.Paging;

public class FiltradorDeMúsicasPorTag extends ServiçosDoAplicativo {

	BuscadorDeMúsicasPorTag buscadorDeMúsicasPorTag;
	GeradorDeArray geradorDeArray;
	
	public FiltradorDeMúsicasPorTag(String accessToken) {
		 this.buscadorDeMúsicasPorTag = new BuscadorDeMúsicasPorTag(accessToken);
		 geradorDeArray = new GeradorDeArray();
	 }
	
	public Track[] filtra(String tagDeProcura, int offset_min, int offset_max) {
		List<Track[]> músicas_buscadas = new ArrayList<>();
		Paging<Track> músicas_buscadas_intermediário;
		int offset = offset_min + 50, tamanho = 0;
		try {
			do {
				músicas_buscadas_intermediário = this.buscadorDeMúsicasPorTag.executaServiço(tagDeProcura, offset_min);
				músicas_buscadas.add(músicas_buscadas_intermediário.getItems());
				tamanho += músicas_buscadas_intermediário.getItems().length;
				offset = offset + 50;
			} while(offset < offset_max);
		}
		catch(NullPointerException exceção) {
			System.out.println("Impossível de obter as músicas filtradas por tag!");
		}
		return this.geradorDeArray.listTrackParaArray(músicas_buscadas, tamanho);
	}
}
