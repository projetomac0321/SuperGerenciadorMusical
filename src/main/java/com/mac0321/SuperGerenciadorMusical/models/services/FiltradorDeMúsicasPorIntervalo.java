package com.mac0321.SuperGerenciadorMusical.models.services;

import java.util.ArrayList;
import java.util.List;

import se.michaelthelin.spotify.model_objects.specification.AudioFeatures;
import se.michaelthelin.spotify.model_objects.specification.Track;

public class FiltradorDeMúsicasPorIntervalo {

	 private ProcuradorDeParâmetrosDeMúsicas procuradorDeParâmetrosDeMúsicas;
	 private GeradorDeArray geradorDeArray;
	 private MapeamentoDosParâmetros mapeamentoDosParâmetros;
	 private ProcuradorDeMúsicas procuradorDeMúsicas;
	
	 public FiltradorDeMúsicasPorIntervalo(String accessToken) {
		 this.procuradorDeParâmetrosDeMúsicas = new ProcuradorDeParâmetrosDeMúsicas(accessToken);
		 this.procuradorDeMúsicas = new ProcuradorDeMúsicas(accessToken);
		 this.geradorDeArray = new GeradorDeArray();
		 this.mapeamentoDosParâmetros = new MapeamentoDosParâmetros();
	 }
	 
	public Track[] filtra(Float[] intervalos_de_busca, int[] índices_dos_intervalos, String[] ids){
		 AudioFeatures[] parâmetros;
		 Float[] parâmetro_desejado;
		 Track[] músicas_filtradas = null;
		 int contador, contador2;
		 try {		
			 for(contador = 0; contador < intervalos_de_busca.length; contador = contador + 2) {
				 parâmetros = this.procuradorDeParâmetrosDeMúsicas.executaServiço(ids);
				 parâmetro_desejado = new Float[parâmetros.length];
				 for(contador2 = 0; contador2 < parâmetros.length; contador2 ++) {
					 this.mapeamentoDosParâmetros.atualiza_mapa(parâmetros[contador2]);
					 parâmetro_desejado[contador2] = this.mapeamentoDosParâmetros.valor_do_mapa(índices_dos_intervalos[contador/2]);
				 }
				 ids = this.filtra_por_intervalo(ids, intervalos_de_busca[contador], intervalos_de_busca[contador + 1], parâmetro_desejado);
			 }
			 músicas_filtradas = this.procuradorDeMúsicas.executaServiço(ids);
		 }
		 catch(NullPointerException | StringIndexOutOfBoundsException exceção) {
			 System.out.println("Não foi possível obter as músicas filtradas por intervalos");
		 }
		 return músicas_filtradas;
	 }
	 
	 private String[] filtra_por_intervalo(String[] ids, Float mínimo, Float máximo, Float[] parâmetro_das_músicas) {
		 List<String> ids_filtrados = new ArrayList<>();
		 int contador;
		 for(contador = 0; contador < parâmetro_das_músicas.length; contador ++)
			 if(parâmetro_das_músicas[contador].compareTo(mínimo) >= 0 && parâmetro_das_músicas[contador].compareTo(máximo) <= 0)
				 ids_filtrados.add(ids[contador]);
		 return this.geradorDeArray.listStringParaArray(ids_filtrados);
	 }
	 
	 
}
