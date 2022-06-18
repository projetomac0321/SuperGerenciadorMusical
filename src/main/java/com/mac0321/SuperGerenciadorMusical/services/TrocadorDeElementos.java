package com.mac0321.SuperGerenciadorMusical.services;

public class TrocadorDeElementos {

	 public <T extends Comparable<T>> void trocar(T[] vetor, int i, int j) {
		 T temp = vetor[i];
		 vetor[i] = vetor[j];
		 vetor[j] = temp;
	 }
}
