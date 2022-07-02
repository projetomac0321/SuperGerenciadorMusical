package com.mac0321.SuperGerenciadorMusical.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mac0321.SuperGerenciadorMusical.models.entities.Autenticador;
import com.mac0321.SuperGerenciadorMusical.models.services.FiltradorDeMúsicasDasPlaylists;
import com.mac0321.SuperGerenciadorMusical.models.services.FiltradorDeMúsicasPorIntervalo;
import com.mac0321.SuperGerenciadorMusical.models.services.FiltradorDeMúsicasPorTag;

import se.michaelthelin.spotify.model_objects.specification.Track;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping(value = "/filtragem")
public class ControladorDeFiltragem {
	private Autenticador autenticador = Autenticador.criarAutenticador();
	private FiltradorDeMúsicasPorTag filtradorDeMúsicasPorTag;
	private FiltradorDeMúsicasPorIntervalo filtradorDeMúsicasPorIntervalo;
	private FiltradorDeMúsicasDasPlaylists filtradorDeMúsicasDasPlaylists;
	
	@GetMapping("/musicas")
	private ResponseEntity<Track[]> FiltrarMúsicas (@RequestParam String tagDeProcura, @RequestParam int offset_min, 
													@RequestParam int offset_max, @RequestParam int[] ídicesDosFiltros, 
													@RequestParam Float[] valoresMaxMinPorFiltro) {
		Track[] músicasFiltradasPorTag;
		Track[] músicasFiltradasPorFiltros;
		filtradorDeMúsicasPorIntervalo = new FiltradorDeMúsicasPorIntervalo(autenticador.getTokenUsuario());
		filtradorDeMúsicasPorTag = new FiltradorDeMúsicasPorTag(autenticador.getTokenUsuario());
		músicasFiltradasPorTag = filtradorDeMúsicasPorTag.filtra(tagDeProcura, offset_min, offset_max);
		String[] ids_músicas = new String[músicasFiltradasPorTag.length];
		for (int i=0; i < músicasFiltradasPorTag.length; i++)
			ids_músicas[i] = músicasFiltradasPorTag[i].getId();
		músicasFiltradasPorFiltros = filtradorDeMúsicasPorIntervalo.filtra(valoresMaxMinPorFiltro, ídicesDosFiltros, ids_músicas);
		return new ResponseEntity<Track[]>(músicasFiltradasPorFiltros, HttpStatus.OK);
	}
	
	@GetMapping("/musicas-na-playlist")
	private ResponseEntity<Track[]> filtrarMúsicasNaPlaylist (@RequestParam String tagDeProcura, 
															  @RequestParam Float[] valoresMaxMinPorFiltro,
															  @RequestParam int[] ídicesDosFiltros) {
		Track[] músicas;
		filtradorDeMúsicasDasPlaylists = new FiltradorDeMúsicasDasPlaylists(autenticador.getTokenUsuario());
		músicas = filtradorDeMúsicasDasPlaylists.filtra(tagDeProcura, valoresMaxMinPorFiltro, ídicesDosFiltros);
		return new ResponseEntity<Track[]>(músicas, HttpStatus.OK);
	}
	
}
