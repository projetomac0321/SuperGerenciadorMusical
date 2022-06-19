package com.mac0321.SuperGerenciadorMusical.services;

import se.michaelthelin.spotify.model_objects.specification.Track;

public class FiltradorDeMúsicasDasPlaylists {

	 FiltradorDeMúsicasPorTag filtradorDeMúsicasPorTag;
	 FiltradorDeMúsicasPorIntervalo filtradorDeMúsicasPorIntervalo;
	 ComparadorDeArraysDeMúsicas comparadorDeArraysDeMúsicas;
	 
	 public FiltradorDeMúsicasDasPlaylists(String accessToken) {
		 filtradorDeMúsicasPorTag = new  FiltradorDeMúsicasPorTag(accessToken);
		 filtradorDeMúsicasPorIntervalo = new FiltradorDeMúsicasPorIntervalo(accessToken);
		 comparadorDeArraysDeMúsicas= new ComparadorDeArraysDeMúsicas();
	 }
	
	public Track[] filtra(String tagDeProcura, Float[] intervalos_de_busca, int[] índices_dos_intervalos) {
		Track[] músicas_buscadas_por_tag, músicas_buscadas_por_intervalo, músicas_filtradas;
		músicas_buscadas_por_tag = this.filtradorDeMúsicasPorTag.filtra(tagDeProcura);
		músicas_buscadas_por_intervalo = this.filtradorDeMúsicasPorIntervalo.filtra(intervalos_de_busca, índices_dos_intervalos);
		músicas_filtradas = this.comparadorDeArraysDeMúsicas.compara(músicas_buscadas_por_tag, músicas_buscadas_por_intervalo);
		return músicas_filtradas;
	}
}