package com.mac0321.SuperGerenciadorMusical.resources;

import java.io.IOException;

import org.apache.hc.core5.http.ParseException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping(value = "/playlists")
public class GerenciadorDeServiços {
	/* Criar Classes dos Serviços como atributos */
	/*
	@GetMapping("/cria")
	private Playlist autoriza() throws ParseException, SpotifyWebApiException, IOException {
		return playListCriada;
	}
	*/
	
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
