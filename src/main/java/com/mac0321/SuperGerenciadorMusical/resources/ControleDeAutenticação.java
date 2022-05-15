package com.mac0321.SuperGerenciadorMusical.resources;

import java.io.IOException;

import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;

import org.apache.hc.core5.http.ParseException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import com.mac0321.SuperGerenciadorMusical.services.Autenticador;
import com.mac0321.SuperGerenciadorMusical.services.GerenciadorDeServiços;


@RestController
@RequestMapping(value = "/")
public class ControleDeAutenticação{
	public Autenticador autenticador = new Autenticador();
	public GerenciadorDeServiços gerenciador;
	public String accessToken;
		
	@GetMapping("/")
	private RedirectView teste() throws ParseException, SpotifyWebApiException, IOException {
		return this.autenticador.performaRequisicaoAutorizacao();
	}
		
	@GetMapping("/aceito")
	private String requisitaTokenAcesso(@RequestParam String code) throws ParseException, SpotifyWebApiException, IOException{
		this.autenticador.requisitaTokenAcesso(code);
		gerenciador = new GerenciadorDeServiços(this.autenticador.getTokenUsuario());
		gerenciador.executaGerenciador();
		return "Prosseguir via linha de comando";
	}
	
}



