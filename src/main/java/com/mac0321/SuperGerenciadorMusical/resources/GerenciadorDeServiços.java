package com.mac0321.SuperGerenciadorMusical.resources;

import java.io.IOException;
import java.net.URI;
import java.util.List;

import org.apache.hc.core5.http.ParseException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.mac0321.SuperGerenciadorMusical.services.Autenticador;
import com.mac0321.SuperGerenciadorMusical.services.CriadorDePlaylist;
import com.mac0321.SuperGerenciadorMusical.services.PlaylistsDoUsuárioAtual;
import com.mac0321.SuperGerenciadorMusical.services.RemovedorDePlaylists;

import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.specification.Paging;
import se.michaelthelin.spotify.model_objects.specification.Playlist;
import se.michaelthelin.spotify.model_objects.specification.PlaylistSimplified;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping(value = "/servicos")
public class GerenciadorDeServiços {
	String acessToken;
	public Autenticador autenticador = new Autenticador();
	public CriadorDePlaylist criadorDePlaylists;
	public PlaylistsDoUsuárioAtual listadorDePlaylists;
	public RemovedorDePlaylists removedorDePlaylists;
	
	@GetMapping("/autorizacao-inicia")
	private URI autoriza() throws ParseException, SpotifyWebApiException, IOException {
		return this.autenticador.autorizacaoUsuario();
	}
	
	@GetMapping("/autentica")
	private void autenticacao(@RequestParam String code) throws ParseException, SpotifyWebApiException, IOException {
		this.autenticador.requisitaTokenAcesso(code);
		this.acessToken = this.autenticador.getTokenUsuario();
		
		criadorDePlaylists = new CriadorDePlaylist(this.acessToken);
		listadorDePlaylists = new PlaylistsDoUsuárioAtual(this.acessToken);

		return;
	}
	
	@PostMapping("/playlist-criar")
	private Playlist criarPlaylist(@RequestParam String nome, 
								   @RequestParam boolean serColaborativa, 
								   @RequestParam boolean serPublica,
								   String descricao) 
			throws ParseException, SpotifyWebApiException, IOException {
		
		Playlist playlistCriada;
		playlistCriada = this.criadorDePlaylists.criaPlaylist(nome, serColaborativa, serPublica, descricao);
		
		return playlistCriada;
	}
	/*
	@GetMapping("/playlist-adicionamusica")
	private void adicionaMusica() throws ParseException, SpotifyWebApiException, IOException {
		return playListCriada;
	}
	*/
	
	@GetMapping("/playlist-listar")
	private List<Paging<PlaylistSimplified>> listaPlaylists() throws ParseException, SpotifyWebApiException, IOException {
		return this.listadorDePlaylists.buscaListaDePlaylistsDoUsuárioAtual();
	}
	
	
	
	@PostMapping("/playlist-deletar")
	private void deletaPlaylist(@RequestParam String playlistID) throws ParseException, SpotifyWebApiException, IOException {
		this.removedorDePlaylists.removerPlaylist(playlistID);
		return;
	}
	
	
	/*
	@GetMapping("/playlist-removemusica")
	private void removeMusica() throws ParseException, SpotifyWebApiException, IOException {
		return playListCriada;
	}
	*/

}
