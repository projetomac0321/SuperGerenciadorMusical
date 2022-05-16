package com.mac0321.SuperGerenciadorMusical.services;

import java.util.List;
import java.util.Scanner;

import se.michaelthelin.spotify.model_objects.specification.Paging;
import se.michaelthelin.spotify.model_objects.specification.Track;

public class SelecionadorDeMúsicasPorTag {

	public boolean imprimeSeleçãoDeMúsicas(Paging<Track> novasMúsicasBuscadas, List<Track> listaDeMúsicasBuscadas){
		Scanner sc = new Scanner(System.in);
		int índiceEscolhido = 0;
		boolean háMúsicasASeremBuscadas = true;
		if(novasMúsicasBuscadas.getItems().length == 0)
			háMúsicasASeremBuscadas = false;
		else {
			this.imprimeMúsicas(novasMúsicasBuscadas);
			System.out.println("Digite na linha abaixo o número dos índices das músicas que deseja adicionar, caso não deseje adicionar nenhuma digite 5");
			while(índiceEscolhido != 5) {
				índiceEscolhido = sc.nextInt();
				if(índiceEscolhido >= 0 && índiceEscolhido < novasMúsicasBuscadas.getItems().length)
					listaDeMúsicasBuscadas.add(novasMúsicasBuscadas.getItems()[índiceEscolhido]);
			}
			índiceEscolhido = 0;
			System.out.println("Digite true, caso deseje buscar mais músicas, digite false caso contrário");
			háMúsicasASeremBuscadas = sc.nextBoolean();
		}
		return háMúsicasASeremBuscadas;
	}

	private void imprimeMúsicas(Paging<Track> listaDeMúsicas) {
		int contador;
		for(contador = 0; contador < listaDeMúsicas.getItems().length; contador ++)
			System.out.println("Índice: " + Integer.toString(contador) + " Nome: " +
					listaDeMúsicas.getItems()[contador].getName() + " Album: " + listaDeMúsicas.getItems()[contador].getAlbum().getName());
	}
	
}
