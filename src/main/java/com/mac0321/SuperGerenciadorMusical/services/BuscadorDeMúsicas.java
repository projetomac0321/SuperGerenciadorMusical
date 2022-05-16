package com.mac0321.SuperGerenciadorMusical.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.model_objects.AbstractModelObject;
import se.michaelthelin.spotify.model_objects.specification.Paging;

public abstract class BuscadorDeMúsicas {

	protected final SpotifyApi spotifyApi;
	
	BuscadorDeMúsicas(String accessToken){
		spotifyApi = new SpotifyApi.Builder().setAccessToken(accessToken).build();
	}
	
	public String[] buscaURIsDasMúsicas(String stringDeProcura) {
		String [] uris = null;
		Scanner sc = new Scanner(System.in);
		int índiceEscolhido = 0, offset = 0;
		boolean háMúsicasASeremBuscadas = true;
		List<AbstractModelObject> listaDeMúsicasBuscadas = new ArrayList<>();
		Paging<AbstractModelObject> novasMúsicasBuscadas = null;
		while(háMúsicasASeremBuscadas){
			novasMúsicasBuscadas = this.buscaMúsicasDoSpotify(stringDeProcura, offset);
			try {
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
					offset += 5;
					System.out.println("Digite true, caso deseje buscar mais músicas, digite false caso contrário");
					háMúsicasASeremBuscadas = sc.nextBoolean();
				}
			}
			catch (NullPointerException exception) {
				háMúsicasASeremBuscadas = false;
			}
		}
		if(listaDeMúsicasBuscadas.size() == 0)
			System.out.println("Busca não retornou resultados.");
		uris = this.geraArrayDeURIs(listaDeMúsicasBuscadas);
		return uris;
	}
	
	protected abstract Paging<AbstractModelObject> buscaMúsicasDoSpotify(String stringDeProcura, int offset);

	protected abstract void imprimeMúsicas(Paging<AbstractModelObject> listaDeMúsicas);

	protected abstract String[] geraArrayDeURIs(List<AbstractModelObject> listaDeMúsicasBuscadas);
}
