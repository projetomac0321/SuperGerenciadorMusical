package com.mac0321.SuperGerenciadorMusical.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mac0321.SuperGerenciadorMusical.services.BuscadorDeArtistas;
import com.mac0321.SuperGerenciadorMusical.services.BuscadorDeMúsicasPorTag;
import com.mac0321.SuperGerenciadorMusical.services.BuscadorDePlaylistsPúblicas;
import com.mac0321.SuperGerenciadorMusical.services.BuscadorDeÁlbuns;

import se.michaelthelin.spotify.model_objects.specification.AlbumSimplified;
import se.michaelthelin.spotify.model_objects.specification.Artist;
import se.michaelthelin.spotify.model_objects.specification.Paging;
import se.michaelthelin.spotify.model_objects.specification.PlaylistSimplified;
import se.michaelthelin.spotify.model_objects.specification.Track;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping(value = "/buscar-por-tag")
public class ControladorDeBuscaPorTag {
	private Autenticador autenticador = Autenticador.criarAutenticador();
	private BuscadorDeMúsicasPorTag buscadorDeMusicaPorTag;
	private BuscadorDeÁlbuns buscadorDeAlbuns;
	private BuscadorDePlaylistsPúblicas buscadorDePlaylistsPublicas;
	private BuscadorDeArtistas buscadorDeArtistas;
	
	@GetMapping("/buscar-por-query")
	private ResponseEntity<Track[]> listarMusicasPorQuery(@RequestParam String query, @RequestParam int offset) {
		buscadorDeMusicaPorTag = new BuscadorDeMúsicasPorTag(autenticador.getTokenUsuario());
		Paging<Track> pagingDeMusicas;
		pagingDeMusicas = buscadorDeMusicaPorTag.executaServiço(query, offset);
		return new ResponseEntity<Track[]>(pagingDeMusicas.getItems(), HttpStatus.OK);
	}
	
	@GetMapping("/buscar-album")
	private ResponseEntity<AlbumSimplified[]> listarAlbuns (@RequestParam String tagAlbum, @RequestParam int offset) {
		buscadorDeAlbuns = new BuscadorDeÁlbuns(autenticador.getTokenUsuario());
		Paging<AlbumSimplified> pagingDeAlbuns;
		pagingDeAlbuns = buscadorDeAlbuns.executaServiço(tagAlbum, offset);
		return new ResponseEntity<AlbumSimplified[]>(pagingDeAlbuns.getItems(), HttpStatus.OK);
	}
	
	@GetMapping("/buscar-playlist-public")
	private ResponseEntity<PlaylistSimplified[]> listarPlylistsPublicas (@RequestParam String tagPlaylist, @RequestParam int offset) {
		buscadorDePlaylistsPublicas = new BuscadorDePlaylistsPúblicas(autenticador.getTokenUsuario());
		Paging<PlaylistSimplified> pagingDePlaylists;
		pagingDePlaylists = buscadorDePlaylistsPublicas.executaServiço(tagPlaylist, offset);
		return new ResponseEntity<PlaylistSimplified[]>(pagingDePlaylists.getItems(), HttpStatus.OK);
	}
	
	@GetMapping("/buscar-artista")
	private ResponseEntity<Artist[]> listarArtistas (@RequestParam String tagArtista, @RequestParam int offset) {
		buscadorDeArtistas = new BuscadorDeArtistas(autenticador.getTokenUsuario());
		Paging<Artist> pagingDeArtistas;
		pagingDeArtistas = buscadorDeArtistas.executaServiço(tagArtista, offset);
		return new ResponseEntity<Artist[]>(pagingDeArtistas.getItems(), HttpStatus.OK);
	}
}
