package com.mac0321.SuperGerenciadorMusical.models.services;

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
		Track[] músicasFiltradasPorTag, músicasFiltradasPorFiltros;
		músicasFiltradasPorTag = this.geradorDeArray.pagingTrackParaArray(this.buscadorDeMúsicasPorTag.executaServiço(tagDeProcura, offset));
		String[] ids_músicas = new String[músicasFiltradasPorTag.length];
		for (int i=0; i < músicasFiltradasPorTag.length; i++)
			ids_músicas[i] = músicasFiltradasPorTag[i].getId();
		músicasFiltradasPorFiltros = filtradorDeMúsicasPorIntervalo.filtra(valoresMaxMinPorFiltro, indicesDosFiltros, ids_músicas);
		return músicasFiltradasPorFiltros;
	}

}
