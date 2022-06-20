package com.mac0321.SuperGerenciadorMusical.models.services;

public class OrdenadorDeMúsicasPorParâmetro {

	private TrocadorDeElementos trocadorDeElementos = new TrocadorDeElementos();

	public <T extends Comparable<T>> String[] ordenaMúsicas(T[] parâmetro, String [] ids) {
		try {
			this.quickSort(parâmetro, 0, parâmetro.length - 1, ids);
		}
		catch(NullPointerException exceção) {
			System.out.println("Ordenação falhou.");
		}
		return ids;
	}
		
	private <T extends Comparable<T>> void quickSort(T[] parâmetro, int ini, int fim, String [] ids){
		int pivo;
		if (ini < fim){
			pivo = (fim - ini)*((int) Math.random()) + ini;
		    this.trocadorDeElementos.trocar(parâmetro, fim, pivo);
		    this.trocadorDeElementos.trocar(ids, fim, pivo);
		    pivo = separaSedgewick (parâmetro, ini, fim, ids);
		    this.quickSort(parâmetro, ini, pivo - 1, ids);
		    this.quickSort(parâmetro, pivo + 1, fim, ids);
		}
	}

	private <T extends Comparable<T>> int separaSedgewick (T[] parâmetro, int ini, int fim, String [] ids){
		T pivo = parâmetro[fim];
		int i = ini - 1;
		int j;
		for (j = ini; j <= fim; j++)
			if (parâmetro[j].compareTo(pivo) >= 0){
				i = i + 1;
				if (j != i) {
					this.trocadorDeElementos.trocar(parâmetro, i, j);
					this.trocadorDeElementos.trocar(ids, i, j);
				}
			}
		return i;
	}
}
