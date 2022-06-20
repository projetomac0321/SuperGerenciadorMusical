package com.mac0321.SuperGerenciadorMusical.models.services;

import se.michaelthelin.spotify.model_objects.specification.Track;

public class FiltradorDeMúsicasDasPlaylists extends ServiçosDoAplicativo {

	 FiltradorDeMúsicasPorTag filtradorDeMúsicasPorTag;
	 GeradorDeIdsDasMúsicasDoUsuárioAtual geradorDeIdsDasMúsicasDoUsuárioAtual;
	 FiltradorDeMúsicasPorIntervalo filtradorDeMúsicasPorIntervalo;
	 ComparadorDeArraysDeMúsicas comparadorDeArraysDeMúsicas;
	 
	 public FiltradorDeMúsicasDasPlaylists(String accessToken) {
		 this.filtradorDeMúsicasPorTag = new  FiltradorDeMúsicasPorTag(accessToken);
		 this.geradorDeIdsDasMúsicasDoUsuárioAtual = new GeradorDeIdsDasMúsicasDoUsuárioAtual(accessToken);
		 this.filtradorDeMúsicasPorIntervalo = new FiltradorDeMúsicasPorIntervalo(accessToken);
		 this.comparadorDeArraysDeMúsicas= new ComparadorDeArraysDeMúsicas();
	 }
	
	public Track[] filtra(String tagDeProcura, Float[] intervalos_de_busca, int[] índices_dos_intervalos) {
		Track[] músicas_buscadas_por_tag, músicas_buscadas_por_intervalo, músicas_filtradas;
		String[] ids;
		músicas_buscadas_por_tag = this.filtradorDeMúsicasPorTag.filtra(tagDeProcura, 1000);
		ids = this.geradorDeIdsDasMúsicasDoUsuárioAtual.obtem_ids();
		músicas_buscadas_por_intervalo = this.filtradorDeMúsicasPorIntervalo.filtra(intervalos_de_busca, índices_dos_intervalos, ids);
		músicas_filtradas = this.comparadorDeArraysDeMúsicas.compara(músicas_buscadas_por_tag, músicas_buscadas_por_intervalo);
		return músicas_filtradas;
	}
}