package com.mac0321.SuperGerenciadorMusical.models.services;

import se.michaelthelin.spotify.model_objects.specification.Track;

public class FiltradorDeBuscasDeMúsicas extends ServiçosDoAplicativo {
	
	private BuscadorMúltiploDeMúsicasPorTag buscadorMúltiploDeMúsicasPorTag;
	private FiltradorDeMúsicasPorIntervalo filtradorDeMúsicasPorIntervalo;
	
	public FiltradorDeBuscasDeMúsicas(String accessToken) {
		filtradorDeMúsicasPorIntervalo = new FiltradorDeMúsicasPorIntervalo(accessToken);
		buscadorMúltiploDeMúsicasPorTag = new BuscadorMúltiploDeMúsicasPorTag(accessToken);
	}
	
	public Track[] executaServiço(String tagDeProcura, int offset_min, int offset_max, int[] indicesDosFiltros, Float[] valoresMaxMinPorFiltro) {
		Track[] músicasFiltradasPorTag, músicasFiltradasPorFiltros;
		músicasFiltradasPorTag = buscadorMúltiploDeMúsicasPorTag.filtra(tagDeProcura, offset_min, offset_max);
		String[] ids_músicas = new String[músicasFiltradasPorTag.length];
		for (int i=0; i < músicasFiltradasPorTag.length; i++)
			ids_músicas[i] = músicasFiltradasPorTag[i].getId();
		músicasFiltradasPorFiltros = filtradorDeMúsicasPorIntervalo.filtra(valoresMaxMinPorFiltro, indicesDosFiltros, ids_músicas);
		return músicasFiltradasPorFiltros;
	}

}
