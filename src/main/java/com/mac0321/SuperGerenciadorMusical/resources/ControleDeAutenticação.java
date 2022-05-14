package com.mac0321.SuperGerenciadorMusical.resources;

import java.net.URI;
import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.SpotifyHttpManager;
import se.michaelthelin.spotify.requests.authorization.authorization_code.AuthorizationCodeUriRequest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/")
public class ControleDeAutenticação{
	
	private static final URI redirectUri = SpotifyHttpManager.makeUri("http://localhost:8080/aceito");
	private String tokenUsuario = ""; // token que será retornado quando o usuário aceitar a autentiação
	
	private static final SpotifyApi acessoApi = new SpotifyApi.Builder()
			.setClientId("de6b441011e14346add6404c00b1bed0")
			.setClientSecret("0d222f0e5aef494c83bd133abbb22903")
			.setRedirectUri(redirectUri)
			.build();
	
	@GetMapping("/")
	private static void  autorizacaoUsuario() {
		final AuthorizationCodeUriRequest requisicaoDePermissao = acessoApi.authorizationCodeUri()
				.scope("playlist-modify-private")
				.show_dialog(true)
				.build();
		
		final URI uriReqAutorizacao = requisicaoDePermissao.execute(); //retorna a URI pronta para a requisição
		
	}
		
	@RequestMapping("/aceito")
	String hello() {
		return "Requisicao aceita pelo usuário";
	}
}
