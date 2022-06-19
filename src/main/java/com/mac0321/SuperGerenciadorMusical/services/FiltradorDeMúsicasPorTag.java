package com.mac0321.SuperGerenciadorMusical.services;

import java.util.ArrayList;
import java.util.List;

import se.michaelthelin.spotify.model_objects.specification.Track;
import se.michaelthelin.spotify.model_objects.specification.Paging;

public class FiltradorDeMúsicasPorTag {

	BuscadorDeMúsicasPorTag buscadorDeMúsicasPorTag;
	GeradorDeArray geradorDeArray;
	
	FiltradorDeMúsicasPorTag(String accessToken) {
		 this.buscadorDeMúsicasPorTag = new BuscadorDeMúsicasPorTag(accessToken);
		 geradorDeArray = new GeradorDeArray();
	 }
	
	public Track[] filtra(String tagDeProcura) {
		List<Track[]> músicas_buscadas = new ArrayList<>();
		Paging<Track> músicas_buscadas_intermediário;
		int offset, tamanho = 0;
		try {
			músicas_buscadas_intermediário = this.buscadorDeMúsicasPorTag.executaServiço(tagDeProcura, 0);
			for(offset = 50; offset > 1000 || músicas_buscadas_intermediário.getTotal() == 0; offset = offset + 50) {
				tamanho += músicas_buscadas_intermediário.getTotal();
				músicas_buscadas.add(músicas_buscadas_intermediário.getItems());
				músicas_buscadas_intermediário = this.buscadorDeMúsicasPorTag.executaServiço(tagDeProcura, offset);
			}
		}
		catch(NullPointerException exceção) {
			System.out.println("Impossível de obter as músicas filtradas por tag!");
		}
		return this.geradorDeArray.listParaArray(músicas_buscadas, tamanho);
	}
}
