package com.mac0321.SuperGerenciadorMusical.models.services;

import se.michaelthelin.spotify.model_objects.specification.Track;

public class FiltradorDeMúsicasDasPlaylists extends ServiçosDoAplicativo {

	 GeradorDeIdsDasMúsicasDoUsuárioAtual geradorDeIdsDasMúsicasDoUsuárioAtual;
	 FiltradorDeMúsicasPorIntervalo filtradorDeMúsicasPorIntervalo;
	 FiltradorDeMúsicasPorNome filtradorDeMúsicasPorNome;
	 
	 public FiltradorDeMúsicasDasPlaylists(String accessToken) {
		 this.geradorDeIdsDasMúsicasDoUsuárioAtual = new GeradorDeIdsDasMúsicasDoUsuárioAtual(accessToken);
		 this.filtradorDeMúsicasPorIntervalo = new FiltradorDeMúsicasPorIntervalo(accessToken);
		 this.filtradorDeMúsicasPorNome = new FiltradorDeMúsicasPorNome(accessToken);
	 }
	
	public Track[] filtra(String tagDeProcura, Float[] intervalos_de_busca, int[] índices_dos_intervalos) {
		Track[] músicas_filtradas_por_nome, músicas_filtradas = null;
		String[] ids;
		int contador;
		try{
			ids = this.geradorDeIdsDasMúsicasDoUsuárioAtual.obtem_ids();
			músicas_filtradas_por_nome = this.filtradorDeMúsicasPorNome.filtra(ids, tagDeProcura);
			ids = new String[músicas_filtradas_por_nome.length];
			for(contador = 0; contador < músicas_filtradas_por_nome.length; contador ++)
				ids[contador] = músicas_filtradas_por_nome[contador].getId();
			músicas_filtradas = this.filtradorDeMúsicasPorIntervalo.filtra(intervalos_de_busca, índices_dos_intervalos, ids);
		}
		catch(NullPointerException exceção) {
			System.out.println("Filtragem das músicas das playlists falhou!");
		}
		return músicas_filtradas;
	}
}