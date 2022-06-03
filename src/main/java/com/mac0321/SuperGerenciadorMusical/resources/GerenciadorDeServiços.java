package com.mac0321.SuperGerenciadorMusical.resources;

import java.io.IOException;

import org.apache.hc.core5.http.ParseException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import com.mac0321.SuperGerenciadorMusical.services.CriadorDePlaylist;
import com.mac0321.SuperGerenciadorMusical.services.PlaylistsDoUsuárioAtual;

import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.specification.Playlist;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping(value = "/playlists")
public class GerenciadorDeServiços {
	String accessToken;
	public CriadorDePlaylist criadorDePlaylists;
	public PlaylistsDoUsuárioAtual listarPlaylists;
	
	
	/*
	@PostMapping("/inicializa")
	Pedir para o front enviar o access token e salvar como argumento do gerenciador de serviços
	criadorDePlaylists = new CriadorDePlaylist(accessToken);
	listarPlaylists = new PlaylistDoUsuárioAtual(accessToken );
	*/
	@PostMapping("/cria")
	private Playlist criarPlaylist(@RequestParam String nome, 
								   @RequestParam boolean serColaborativa, 
								   @RequestParam boolean serPublica) 
			throws ParseException, SpotifyWebApiException, IOException {
		
		Playlist playlistCriada;
		playlistCriada = this.criadorDePlaylists.criaPlaylist(nome, serColaborativa, serPublica, accessToken);
		
		return playlistCriada;
	}
	/*
	@GetMapping("/addmusica")
	private void adicionaMusica() throws ParseException, SpotifyWebApiException, IOException {
		return playListCriada;
	}
	*/
	
	/*
	@GetMapping("/lista")
	private void listaPlaylists() throws ParseException, SpotifyWebApiException, IOException {
		return playListCriada;
	}
	*/
	
	/*
	@GetMapping("/deleta")
	private void deletaPlaylist() throws ParseException, SpotifyWebApiException, IOException {
		return playListCriada;
	}
	*/
	
	/*
	@GetMapping("/removemusica")
	private void removeMusica() throws ParseException, SpotifyWebApiException, IOException {
		return playListCriada;
	}
	*/

}
