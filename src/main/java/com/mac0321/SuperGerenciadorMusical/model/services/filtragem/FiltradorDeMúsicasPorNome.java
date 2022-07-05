package com.mac0321.SuperGerenciadorMusical.model.services.filtragem;

import java.util.ArrayList;
import java.util.List;

import com.mac0321.SuperGerenciadorMusical.model.services.busca_por_id.ProcuradorDeMúsicas;

import se.michaelthelin.spotify.model_objects.specification.Track;

public class FiltradorDeMúsicasPorNome {

	 private ProcuradorDeMúsicas procuradorDeMúsicas;
	 private GeradorDeArray geradorDeArray;
	
	 public FiltradorDeMúsicasPorNome(String accessToken){
		 this.procuradorDeMúsicas = new ProcuradorDeMúsicas(accessToken);
		 this.geradorDeArray = new GeradorDeArray();
	 }
	 
	 public Track[] filtra(String[] ids, String nome) {
		 List<Track> músicas_filtradas = new ArrayList<>();
		 Track[] músicas;
		 int contador;
		 try {
			 músicas = this.procuradorDeMúsicas.executaServiço(ids);
			 for(contador = 0; contador < músicas.length; contador ++)
				 if(músicas[contador].getName().toLowerCase().contains(nome.toLowerCase()))
					 músicas_filtradas.add(músicas[contador]);
		}
		catch(NullPointerException exceção) {
			System.out.println("Impossível de filtrar por nome!");
		}
		return this.geradorDeArray.listTrackParaArray(músicas_filtradas);
	}
}
