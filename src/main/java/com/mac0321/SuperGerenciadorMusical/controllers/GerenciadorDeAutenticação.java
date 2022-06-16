package com.mac0321.SuperGerenciadorMusical.controllers;

import java.io.IOException;
import java.net.URI;

import org.apache.hc.core5.http.ParseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mac0321.SuperGerenciadorMusical.services.Autenticador;


import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping(value = "/autenticacao")
public class GerenciadorDeAutenticação {
	Autenticador autenticador = Autenticador.criarAutenticador();
	
	@GetMapping("/iniciar")
	private ResponseEntity<URI> autoriza() throws ParseException, SpotifyWebApiException, IOException {
		URI uriParaAutenticar = this.autenticador.autorizacaoUsuario();
		
		return new ResponseEntity<URI>(uriParaAutenticar, HttpStatus.OK);
	}
	
	@GetMapping("/finaliza")
	private void autenticacao(@RequestParam String code) throws ParseException, SpotifyWebApiException, IOException {
		this.autenticador.requisitaTokenAcesso(code);
		return;
	}

}
