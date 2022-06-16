package com.mac0321.SuperGerenciadorMusical.resources;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mac0321.SuperGerenciadorMusical.services.Autenticador;
import com.mac0321.SuperGerenciadorMusical.services.BuscadorDeMúsicasDaPlaylist;
import com.mac0321.SuperGerenciadorMusical.services.BuscadorDeMúsicasDoÁlbum;
import com.mac0321.SuperGerenciadorMusical.services.BuscadorDeMúsicasPorTag;
import com.mac0321.SuperGerenciadorMusical.services.BuscadorDePlaylistsPúblicas;
import com.mac0321.SuperGerenciadorMusical.services.BuscadorDeÁlbuns;
import com.mac0321.SuperGerenciadorMusical.services.ProcuradorDeÁlbum;

import se.michaelthelin.spotify.model_objects.specification.Album;
import se.michaelthelin.spotify.model_objects.specification.AlbumSimplified;
import se.michaelthelin.spotify.model_objects.specification.Paging;
import se.michaelthelin.spotify.model_objects.specification.PlaylistSimplified;
import se.michaelthelin.spotify.model_objects.specification.PlaylistTrack;
import se.michaelthelin.spotify.model_objects.specification.Track;
import se.michaelthelin.spotify.model_objects.specification.TrackSimplified;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping(value = "/buscar-musicas")
public class GerenciadorDeBuscasDeMúsicas {
	private Autenticador autenticador = Autenticador.criarAutenticador();
	private BuscadorDeMúsicasPorTag buscadorDeMusicaPorTag;
	private BuscadorDeÁlbuns buscadorDeAlbuns;
	private BuscadorDePlaylistsPúblicas buscadorDePlaylistsPublicas;
	private BuscadorDeMúsicasDoÁlbum buscadorDeMusicasDoAlbum;
	private BuscadorDeMúsicasDaPlaylist buscadorDeMusicasDaPlaylist;
	private ProcuradorDeÁlbum procuradorDeAlbum;

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
	
	@GetMapping("/obter-album")
	private ResponseEntity<Album> obterAlbum (@RequestParam String idDoAlbum) {
		procuradorDeAlbum = new ProcuradorDeÁlbum(autenticador.getTokenUsuario());
		Album album = procuradorDeAlbum.executaServiço(idDoAlbum);
		return new ResponseEntity<Album>(album, HttpStatus.OK);
	}
	
	@GetMapping("/buscar-playlist-public")
	private ResponseEntity<PlaylistSimplified[]> listarPlylistsPublicas (@RequestParam String tagPlaylist, @RequestParam int offset) {
		buscadorDePlaylistsPublicas = new BuscadorDePlaylistsPúblicas(autenticador.getTokenUsuario());
		Paging<PlaylistSimplified> pagingDePlaylists;
		pagingDePlaylists = buscadorDePlaylistsPublicas.executaServiço(tagPlaylist, offset);
		return new ResponseEntity<PlaylistSimplified[]>(pagingDePlaylists.getItems(), HttpStatus.OK);
	}
	
	@GetMapping("/buscar-musica-no-album")
	private ResponseEntity<TrackSimplified[]> listarMusicasDoAlbum (@RequestParam String tagMusica, @RequestParam int offset) {
		buscadorDeMusicasDoAlbum = new BuscadorDeMúsicasDoÁlbum(autenticador.getTokenUsuario());
		Paging<TrackSimplified> pagingDeTrackSimplified;
		pagingDeTrackSimplified = buscadorDeMusicasDoAlbum.executaServiço(tagMusica, offset);
		return new ResponseEntity<TrackSimplified[]>(pagingDeTrackSimplified.getItems(), HttpStatus.OK);
	}
	
	@GetMapping("/buscar-musica-na-playlist")
	private ResponseEntity<PlaylistTrack[]> listarMusicasDaPlaylist (@RequestParam String tagMusica, @RequestParam int offset) {
		buscadorDeMusicasDaPlaylist = new BuscadorDeMúsicasDaPlaylist(autenticador.getTokenUsuario());
		Paging<PlaylistTrack> pagingDePlaylistTrack;
		pagingDePlaylistTrack = buscadorDeMusicasDaPlaylist.executaServiço(tagMusica, offset);
		return new ResponseEntity<PlaylistTrack[]>(pagingDePlaylistTrack.getItems(), HttpStatus.OK);
	}
	
}
