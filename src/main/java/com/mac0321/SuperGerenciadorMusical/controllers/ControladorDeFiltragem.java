package com.mac0321.SuperGerenciadorMusical.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mac0321.SuperGerenciadorMusical.model.services.filtragem.FiltradorDeBuscasDeMúsicas;
import com.mac0321.SuperGerenciadorMusical.model.services.filtragem.FiltradorDeMúsicasDasPlaylists;
import com.mac0321.SuperGerenciadorMusical.models.entities.Autenticador;

import se.michaelthelin.spotify.model_objects.specification.Track;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping(value = "/filtragem")
public class ControladorDeFiltragem {
	private Autenticador autenticador = Autenticador.criarAutenticador();
	private FiltradorDeBuscasDeMúsicas filtradorDeBuscasDeMúsicas;
	private FiltradorDeMúsicasDasPlaylists filtradorDeMúsicasDasPlaylists;
	
	@GetMapping("/musicas")
	private ResponseEntity<Track[]> FiltrarMúsicas (@RequestParam String tagDeProcura, @RequestParam int offset,
													@RequestParam int[] indicesDosFiltros, 
													@RequestParam Float[] valoresMaxMinPorFiltro) {
		Track[] músicas;
		filtradorDeBuscasDeMúsicas = new FiltradorDeBuscasDeMúsicas(autenticador.getTokenUsuario());
		músicas = filtradorDeBuscasDeMúsicas.executaServiço(tagDeProcura, offset, indicesDosFiltros, valoresMaxMinPorFiltro);
		return new ResponseEntity<Track[]>(músicas, HttpStatus.OK);
	}
	
	@GetMapping("/musicas-na-playlist")
	private ResponseEntity<Track[]> filtrarMúsicasNaPlaylist (@RequestParam String tagDeProcura, 
															  @RequestParam Float[] valoresMaxMinPorFiltro,
															  @RequestParam int[] indicesDosFiltros) {
		Track[] músicas;
		filtradorDeMúsicasDasPlaylists = new FiltradorDeMúsicasDasPlaylists(autenticador.getTokenUsuario());
		músicas = filtradorDeMúsicasDasPlaylists.filtra(tagDeProcura, valoresMaxMinPorFiltro, indicesDosFiltros);
		return new ResponseEntity<Track[]>(músicas, HttpStatus.OK);
	}
	
}
