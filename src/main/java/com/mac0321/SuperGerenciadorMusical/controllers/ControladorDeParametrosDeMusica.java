package com.mac0321.SuperGerenciadorMusical.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mac0321.SuperGerenciadorMusical.models.entities.Autenticador;
import com.mac0321.SuperGerenciadorMusical.models.services.busca.busca_por_id.ProcuradorDeMúsicas;
import com.mac0321.SuperGerenciadorMusical.models.services.parametros.OrdenadorDeMúsicasPorParâmetro;
import com.mac0321.SuperGerenciadorMusical.models.services.parametros.ProcuradorDeParâmetrosDeMúsicas;

import se.michaelthelin.spotify.model_objects.specification.AudioFeatures;
import se.michaelthelin.spotify.model_objects.specification.Track;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping(value = "/parametros")
public class ControladorDeParametrosDeMusica {
	private Autenticador autenticador = Autenticador.criarAutenticador();
	private OrdenadorDeMúsicasPorParâmetro ordenadorDeMusicasPorParametro;
	private ProcuradorDeMúsicas procuradorDeMusicas;
	private ProcuradorDeParâmetrosDeMúsicas procuradorDeParametrosDeMusicas;
	
	@GetMapping("/obter-parametros-das-musicas")
	private ResponseEntity<AudioFeatures[]> parametrosDasMusicas (@RequestParam String[] idsDasMusicas) {
		procuradorDeParametrosDeMusicas = new ProcuradorDeParâmetrosDeMúsicas(autenticador.getTokenUsuario());
		AudioFeatures[] audioFeatures = procuradorDeParametrosDeMusicas.executaServiço(idsDasMusicas);
		return new ResponseEntity<AudioFeatures[]>(audioFeatures, HttpStatus.OK);
	}
	
	@GetMapping("/ordenar-por-parametro")
	private ResponseEntity<Track[]> listarMusicasOrdenadasPorParametro(Float[] parametro, String[] ids) {
		Track[] listaDeMusicasOrdenada;
		ordenadorDeMusicasPorParametro = new OrdenadorDeMúsicasPorParâmetro();
		procuradorDeMusicas = new ProcuradorDeMúsicas(autenticador.getTokenUsuario());
		listaDeMusicasOrdenada = procuradorDeMusicas.executaServiço(ordenadorDeMusicasPorParametro.ordenaMúsicas(parametro, ids));
		return new ResponseEntity<Track[]>(listaDeMusicasOrdenada, HttpStatus.OK);
	}
}