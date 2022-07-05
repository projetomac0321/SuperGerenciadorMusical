package com.mac0321.SuperGerenciadorMusical.model.services.filtragem;

import java.util.ArrayList;
import java.util.List;

import com.mac0321.SuperGerenciadorMusical.model.services.busca_por_tag.BuscadorDeMúsicasPorTag;
import com.mac0321.SuperGerenciadorMusical.models.services.GeradorDeArray;
import com.mac0321.SuperGerenciadorMusical.models.services.ServiçosDoAplicativo;

import se.michaelthelin.spotify.model_objects.specification.Track;

public class FiltradorDeBuscasDeMúsicas extends ServiçosDoAplicativo {
	
	private BuscadorDeMúsicasPorTag buscadorDeMúsicasPorTag;
	private FiltradorDeMúsicasPorIntervalo filtradorDeMúsicasPorIntervalo;
	private GeradorDeArray geradorDeArray;
	
	public FiltradorDeBuscasDeMúsicas(String accessToken) {
		filtradorDeMúsicasPorIntervalo = new FiltradorDeMúsicasPorIntervalo(accessToken);
		buscadorDeMúsicasPorTag = new BuscadorDeMúsicasPorTag(accessToken);
		geradorDeArray = new GeradorDeArray();
	}
	
	public Track[] executaServiço(String tagDeProcura, int offset, int[] indicesDosFiltros, Float[] valoresMaxMinPorFiltro) {
		Track[] músicasFiltradas = null;
		List<Track[]> lista_de_músicas_filtradas = new ArrayList<>();
		String[] ids;
		int contador, contador2, tamanho = 0;
			for(contador = offset; contador < offset + 100; contador = contador + 50) {
				try {
					músicasFiltradas = this.geradorDeArray.pagingTrackParaArray(this.buscadorDeMúsicasPorTag.executaServiço(tagDeProcura, contador));
					ids = new String[músicasFiltradas.length];
					for (contador2 = 0; contador2 < músicasFiltradas.length; contador2 ++)
						ids[contador2] = músicasFiltradas[contador2].getId();
					lista_de_músicas_filtradas.add(this.filtradorDeMúsicasPorIntervalo.filtra(valoresMaxMinPorFiltro, indicesDosFiltros, ids));
					tamanho += lista_de_músicas_filtradas.get((contador - offset)/50).length;
				}
				catch(NullPointerException exceção) {
					System.out.println("Impossível de filtrar as músicas por nome e parâmetro!");
				}
				músicasFiltradas = this.geradorDeArray.listTrackParaArray(lista_de_músicas_filtradas, tamanho);
			}
		return músicasFiltradas;
	}
}
