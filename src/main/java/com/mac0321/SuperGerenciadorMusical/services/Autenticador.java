package com.mac0321.SuperGerenciadorMusical.services;

import java.io.IOException;
import java.net.URI;

import org.apache.hc.core5.http.ParseException;

import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.SpotifyHttpManager;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.credentials.AuthorizationCodeCredentials;
import se.michaelthelin.spotify.requests.authorization.authorization_code.AuthorizationCodeRequest;
import se.michaelthelin.spotify.requests.authorization.authorization_code.AuthorizationCodeUriRequest;

public class Autenticador {
	
	private static Autenticador autenticador;
	
	private static URI redirectUri;
	private  String tokenUsuario;
	private  String refreshTokenUsuario; 
	private static SpotifyApi acessoApi;
	
	private Autenticador() {
		redirectUri = SpotifyHttpManager.
				makeUri("http://localhost:8080/autenticacao/finaliza");
		acessoApi = new SpotifyApi.Builder()
				.setClientId("de6b441011e14346add6404c00b1bed0")
				.setClientSecret("0d222f0e5aef494c83bd133abbb22903")
				.setRedirectUri(redirectUri)
				.build();
	}
	
	public static Autenticador criarAutenticador() {
		if(autenticador == null) {
			autenticador = new Autenticador();
		}
		return autenticador;
	}
	 
	 public URI autorizacaoUsuario(){
		final AuthorizationCodeUriRequest requisicaoDePermissao = acessoApi.authorizationCodeUri()
				.scope("playlist-modify-private playlist-modify-public")	
				.show_dialog(true)
				.build();
			
		URI uriReqAutorizacao = requisicaoDePermissao.execute(); 
		return uriReqAutorizacao;
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