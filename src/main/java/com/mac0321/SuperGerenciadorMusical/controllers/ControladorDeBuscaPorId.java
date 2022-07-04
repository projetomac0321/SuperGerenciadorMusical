package com.mac0321.SuperGerenciadorMusical.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mac0321.SuperGerenciadorMusical.models.entities.Autenticador;
import com.mac0321.SuperGerenciadorMusical.models.services.ProcuradorDeArtista;
import com.mac0321.SuperGerenciadorMusical.models.services.ProcuradorDeMúsicas;
import com.mac0321.SuperGerenciadorMusical.models.services.ProcuradorDePlaylist;
import com.mac0321.SuperGerenciadorMusical.models.services.ProcuradorDeÁlbum;

import se.michaelthelin.spotify.model_objects.specification.Album;
import se.michaelthelin.spotify.model_objects.specification.Artist;
import se.michaelthelin.spotify.model_objects.specification.Playlist;
import se.michaelthelin.spotify.model_objects.specification.Track;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping(value = "/buscar-por-id")
public class ControladorDeBuscaPorId {
	private Autenticador autenticador = Autenticador.criarAutenticador();
	private ProcuradorDeÁlbum procuradorDeAlbum;
	private ProcuradorDePlaylist procuradorDePlaylist;
	private ProcuradorDeMúsicas procuradorDeMusicas;
	private ProcuradorDeArtista procuradorDeArtista;
	
	@GetMapping("/obter-album")
	private ResponseEntity<Album> obterAlbum (@RequestParam String idDoAlbum) {
		procuradorDeAlbum = new ProcuradorDeÁlbum(autenticador.getTokenUsuario());
		Album album = procuradorDeAlbum.executaServiço(idDoAlbum);
		return new ResponseEntity<Album>(album, HttpStatus.OK);
	}
	
	@GetMapping("/obter-playlist")
	private ResponseEntity<Playlist> obterPlaylist (@RequestParam String idDaPlaylist) {
		procuradorDePlaylist = new ProcuradorDePlaylist(autenticador.getTokenUsuario());
		Playlist playlist = procuradorDePlaylist.executaServiço(idDaPlaylist);
		return new ResponseEntity<Playlist>(playlist, HttpStatus.OK);
	}
	
	@GetMapping("/obter-musicas")
	private ResponseEntity<Track[]> obterMusicas (@RequestParam String[] idsDasMusicas) {
		procuradorDeMusicas= new ProcuradorDeMúsicas(autenticador.getTokenUsuario());
		return new ResponseEntity<Track[]>(procuradorDeMusicas.executaServiço(idsDasMusicas), HttpStatus.OK);
	}
	
	@GetMapping("/obter-artista")
	private ResponseEntity<Artist> obterArtista (@RequestParam String idDoArtista) {
		procuradorDeArtista = new ProcuradorDeArtista(autenticador.getTokenUsuario());
		return new ResponseEntity<Artist>(procuradorDeArtista.executaServiço(idDoArtista), HttpStatus.OK);
	}
	
	@GetMapping("/obter-musica-com-market")
	private ResponseEntity<Track> obterMusicaComMarket (@RequestParam String idDaMusica) {
		procuradorDeMusicas= new ProcuradorDeMúsicas(autenticador.getTokenUsuario());
		return new ResponseEntity<Track>(procuradorDeMusicas.executaServiço(idDaMusica), HttpStatus.OK);
	}
}
