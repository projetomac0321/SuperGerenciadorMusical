package com.mac0321.SuperGerenciadorMusical.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mac0321.SuperGerenciadorMusical.models.entities.Autenticador;
import com.mac0321.SuperGerenciadorMusical.models.services.OrdenadorDeMúsicasPorParâmetro;
import com.mac0321.SuperGerenciadorMusical.models.services.ProcuradorDeMúsicas;

import se.michaelthelin.spotify.model_objects.specification.Track;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping(value = "/tabela-da-playlist")
public class GerenciadorDaTabelaDaPlaylist {
	private OrdenadorDeMúsicasPorParâmetro ordenadorDeMusicasPorParametro;
	private ProcuradorDeMúsicas procuradorDeMusicas;
	private Autenticador autenticador = Autenticador.criarAutenticador();
	
	@GetMapping("/ordenar-por-parametro")
	private ResponseEntity<Track[]> listarMusicasOrdenadasPorParametro(Float[] parametro, String[] ids) {
		Track[] listaDeMusicasOrdenada;
		ordenadorDeMusicasPorParametro = new OrdenadorDeMúsicasPorParâmetro();
		procuradorDeMusicas = new ProcuradorDeMúsicas(autenticador.getTokenUsuario());
		listaDeMusicasOrdenada = procuradorDeMusicas.executaServiço(ordenadorDeMusicasPorParametro.ordenaMúsicas(parametro, ids));
		return new ResponseEntity<Track[]>(listaDeMusicasOrdenada, HttpStatus.OK);
	}
}
