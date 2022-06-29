package com.mac0321.SuperGerenciadorMusical.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mac0321.SuperGerenciadorMusical.models.entities.Autenticador;
import com.mac0321.SuperGerenciadorMusical.models.services.GerenciadorDePlayback;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping(value = "/playback")
public class ControladorDePlayback {
	private Autenticador autenticador = Autenticador.criarAutenticador();
	GerenciadorDePlayback gerenciadorDePlayback;
	
	@PostMapping("/criar-playback")
	private ResponseEntity<String> iniciarPlayback (@RequestBody String uriDaMusica) {
		gerenciadorDePlayback = new GerenciadorDePlayback(autenticador.getTokenUsuario());
		gerenciadorDePlayback.iniciarPlayback(uriDaMusica);
		return new ResponseEntity<String>("Playback criado com sucesso", HttpStatus.OK);
	}
	
	@PostMapping("/pausar-playback")
	private ResponseEntity<String> pausarPlayback () {
		gerenciadorDePlayback = new GerenciadorDePlayback(autenticador.getTokenUsuario());
		gerenciadorDePlayback.pausarPlayback();
		return new ResponseEntity<String>("Playback Pausado com sucesso", HttpStatus.OK);
	}
}
