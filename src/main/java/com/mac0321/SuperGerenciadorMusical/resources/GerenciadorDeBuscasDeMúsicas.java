package com.mac0321.SuperGerenciadorMusical.resources;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mac0321.SuperGerenciadorMusical.services.Autenticador;
import com.mac0321.SuperGerenciadorMusical.services.BuscadorDeMúsicasPorTag;
import com.mac0321.SuperGerenciadorMusical.services.BuscadorDeÁlbuns;

import se.michaelthelin.spotify.model_objects.specification.AlbumSimplified;
import se.michaelthelin.spotify.model_objects.specification.Paging;
import se.michaelthelin.spotify.model_objects.specification.Track;
import se.michaelthelin.spotify.model_objects.specification.TrackSimplified;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping(value = "/buscar-musicas")
public class GerenciadorDeBuscasDeMúsicas {
	private Autenticador autenticador = Autenticador.criarAutenticador();
	private BuscadorDeMúsicasPorTag buscadorDeMusicaPorTag;
	private BuscadorDeÁlbuns buscadorDeAlbuns;

	@GetMapping("/buscar-por-query")
	private ResponseEntity<Track[]> listarMusicasPorQuery(@RequestParam String query, @RequestParam int offset) {
		buscadorDeMusicaPorTag = new BuscadorDeMúsicasPorTag(autenticador.getTokenUsuario());
		Paging<Track> pagingDeMusicas;
		pagingDeMusicas = buscadorDeMusicaPorTag.executaServiço(query, offset);
		return new ResponseEntity<Track[]>(pagingDeMusicas.getItems(), HttpStatus.OK);
	}
	
	@GetMapping("/buscar-album")
	private ResponseEntity<AlbumSimplified[]> listarAlbuns (@RequestParam String tituloAlbum, @RequestParam int offset) {
		buscadorDeAlbuns = new BuscadorDeÁlbuns(autenticador.getTokenUsuario());
		Paging<AlbumSimplified> pagingDeAlbuns;
		pagingDeAlbuns = buscadorDeAlbuns.executaServiço(tituloAlbum, offset);
		return new ResponseEntity<AlbumSimplified[]>(pagingDeAlbuns.getItems(), HttpStatus.OK);
	}

	
}
