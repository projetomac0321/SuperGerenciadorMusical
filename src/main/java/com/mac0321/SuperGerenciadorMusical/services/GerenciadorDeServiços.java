package com.mac0321.SuperGerenciadorMusical.services;

import java.util.Scanner;

import org.springframework.stereotype.Service;


public class GerenciadorDeServiços {
	private ServiçosDoAplicativo [] serviços;
	
	public GerenciadorDeServiços(String accessToken){
		this.serviços = new ServiçosDoAplicativo[5];
		serviços[0] = new CriadorDePlaylist(accessToken);
	}
	
	public void executaGerenciador() {
		boolean sistemaOperante = true;
		int serviçoDesejado;
		Scanner sc = new Scanner(System.in);
		while(sistemaOperante) {
			System.out.println("Olá usuário! O que você deseja fazer?");
			System.out.println("Digite 0, caso deseje criar uma playlist");
			System.out.println("Digite 1, caso deseje remover uma playlist");
			System.out.println("Digite 2, caso deseje ver suas playlists");
			System.out.println("Digite 3, caso deseje adicionar músicas a uma playlist");
			System.out.println("Digite 4, caso deseje remover músicas de uma playlist");
			serviçoDesejado = sc.nextInt();
			while(serviçoDesejado < 0 || serviçoDesejado > 4) {
				System.out.println("Você digitou um número inválido, por favor digite um número de 0 a 4!");
				serviçoDesejado = sc.nextInt();
			}
			serviços[serviçoDesejado].ExecutaServiço();
			System.out.println("Caso deseje realizar mais algum serviço digite 1, caso contrário digite 0 para o sistema ser desligado.");
			sistemaOperante = sc.nextBoolean();
		}
		sc.close();
	}


}
