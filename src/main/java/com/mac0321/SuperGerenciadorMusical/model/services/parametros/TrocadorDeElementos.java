package com.mac0321.SuperGerenciadorMusical.model.services.parametros;

public class TrocadorDeElementos {

	 public <T extends Comparable<T>> void trocar(T[] vetor, int i, int j) {
		 T temp = vetor[i];
		 vetor[i] = vetor[j];
		 vetor[j] = temp;
	 }
}
