package com.mac0321.SuperGerenciadorMusical.resources;

import java.io.IOException;

import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;

import org.apache.hc.core5.http.ParseException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import com.mac0321.SuperGerenciadorMusical.services.Autenticador;
import com.mac0321.SuperGerenciadorMusical.services.GerenciadorDeServiços;


@RestController
@RequestMapping(value = "/")
public class GerenciadorHTTP{
	public Autenticador autenticador = new Autenticador();
	public GerenciadorDeServiços gerenciador;
	public String accessToken;
	
	@GetMapping("/autoriza")
	private RedirectView autoriza() throws ParseException, SpotifyWebApiException, IOException {
		return this.autenticador.performaRequisicaoAutorizacao();
	}
	
	@GetMapping("/playlists")
	private String integra() throws ParseException, SpotifyWebApiException, IOException {
		return "Hello, world";
	}
		
	@GetMapping("/aceito")
	private String requisitaTokenAcesso(@RequestParam String code) throws ParseException, SpotifyWebApiException, IOException{
		this.autenticador.requisitaTokenAcesso(code);
		gerenciador = new GerenciadorDeServiços(this.autenticador.getTokenUsuario());
		gerenciador.executaGerenciador();
		return "Fim da aplicação.";
	}
	
	
}



