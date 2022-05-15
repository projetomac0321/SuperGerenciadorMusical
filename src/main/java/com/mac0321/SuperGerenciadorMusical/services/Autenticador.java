package com.mac0321.SuperGerenciadorMusical.services;

import java.io.IOException;
import java.net.URI;

import org.apache.hc.core5.http.ParseException;
import org.springframework.web.servlet.view.RedirectView;

import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.SpotifyHttpManager;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.credentials.AuthorizationCodeCredentials;
import se.michaelthelin.spotify.requests.authorization.authorization_code.AuthorizationCodeRequest;
import se.michaelthelin.spotify.requests.authorization.authorization_code.AuthorizationCodeUriRequest;

public class Autenticador {
	private static final URI redirectUri = SpotifyHttpManager.
			makeUri("http://localhost:8080/aceito");
	private  String tokenUsuario = ""; // token que será retornado quando o usuário aceitar a autenticação
	private  String refreshTokenUsuario = ""; // token para atualizar o token de acesso
	
	private static final SpotifyApi acessoApi = new SpotifyApi.Builder()
			.setClientId("de6b441011e14346add6404c00b1bed0")
			.setClientSecret("0d222f0e5aef494c83bd133abbb22903")
			.setRedirectUri(redirectUri)
			.build();
	 
	 private URI autorizacaoUsuario(){ // metodo que cria a URI para autorizacao 
		final AuthorizationCodeUriRequest requisicaoDePermissao = acessoApi.authorizationCodeUri()
				.scope("playlist-modify-private playlist-modify-public")	
				.show_dialog(true)
				.build();
			
		URI uriReqAutorizacao = requisicaoDePermissao.execute(); //retorna a URI pronta para a requisição
		
		
		return uriReqAutorizacao;
	}
	
	// Performa requisição externa ao spotify - deve ser chamada no controller
	public RedirectView performaRequisicaoAutorizacao() throws ParseException, SpotifyWebApiException, IOException {
		String URLAutorizacao = autorizacaoUsuario().toString();
		
		RedirectView redirectView = new RedirectView();
	    redirectView.setUrl(URLAutorizacao);
	    return redirectView;
	}
		
	public void requisitaTokenAcesso(String code) throws ParseException, SpotifyWebApiException, IOException {
		AuthorizationCodeRequest requisicaoDoCodigo = acessoApi.authorizationCode(code).build();
		AuthorizationCodeCredentials credenciaisDeAcessoProntas  = requisicaoDoCodigo.execute();
		setTokens(credenciaisDeAcessoProntas.getRefreshToken(), credenciaisDeAcessoProntas.getAccessToken());
		
		return;
	}
	
	public void setTokens(String refreshToken, String accessToken) {
		this.tokenUsuario = accessToken;
		this.refreshTokenUsuario = refreshToken;
	}

	public String getTokenUsuario() {
		return tokenUsuario;
	}

}
