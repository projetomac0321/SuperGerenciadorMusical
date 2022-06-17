package com.mac0321.SuperGerenciadorMusical.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mac0321.SuperGerenciadorMusical.services.Autenticador;
import com.mac0321.SuperGerenciadorMusical.services.BuscadorDeArtistas;
import com.mac0321.SuperGerenciadorMusical.services.BuscadorDeMúsicasDaPlaylist;
import com.mac0321.SuperGerenciadorMusical.services.BuscadorDeMúsicasDoÁlbum;
import com.mac0321.SuperGerenciadorMusical.services.BuscadorDeMúsicasPorTag;
import com.mac0321.SuperGerenciadorMusical.services.BuscadorDePlaylistsPúblicas;
import com.mac0321.SuperGerenciadorMusical.services.BuscadorDeÁlbuns;
import com.mac0321.SuperGerenciadorMusical.services.ProcuradorDeMúsicas;
import com.mac0321.SuperGerenciadorMusical.services.ProcuradorDeParâmetrosDeMúsicas;
import com.mac0321.SuperGerenciadorMusical.services.ProcuradorDePlaylist;
import com.mac0321.SuperGerenciadorMusical.services.ProcuradorDeTopMúsicasDoArtista;
import com.mac0321.SuperGerenciadorMusical.services.ProcuradorDeÁlbum;

import se.michaelthelin.spotify.model_objects.specification.Album;
import se.michaelthelin.spotify.model_objects.specification.AlbumSimplified;
import se.michaelthelin.spotify.model_objects.specification.Artist;
import se.michaelthelin.spotify.model_objects.specification.AudioFeatures;
import se.michaelthelin.spotify.model_objects.specification.Paging;
import se.michaelthelin.spotify.model_objects.specification.Playlist;
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
	private ProcuradorDePlaylist procuradorDePlaylist;
	private ProcuradorDeParâmetrosDeMúsicas procuradorDeParametrosDeMusicas;
	private BuscadorDeArtistas buscadorDeArtistas;
	private ProcuradorDeTopMúsicasDoArtista procuradorDeTopMusicasDoArtista;
	private ProcuradorDeMúsicas procuradorDeMusicas;

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
	
	@GetMapping("/listar-musicas-do-album")
	private ResponseEntity<TrackSimplified[]> listarMusicasDoAlbum (@RequestParam String idAlbum, @RequestParam int offset) {
		buscadorDeMusicasDoAlbum = new BuscadorDeMúsicasDoÁlbum(autenticador.getTokenUsuario());
		Paging<TrackSimplified> pagingDeTrackSimplified;
		pagingDeTrackSimplified = buscadorDeMusicasDoAlbum.executaServiço(idAlbum, offset);
		return new ResponseEntity<TrackSimplified[]>(pagingDeTrackSimplified.getItems(), HttpStatus.OK);
	}
	
	@GetMapping("/listar-musicas-da-playlist")
	private ResponseEntity<PlaylistTrack[]> listarMusicasDaPlaylist (@RequestParam String idPlaylist, @RequestParam int offset) {
		buscadorDeMusicasDaPlaylist = new BuscadorDeMúsicasDaPlaylist(autenticador.getTokenUsuario());
		Paging<PlaylistTrack> pagingDePlaylistTrack;
		pagingDePlaylistTrack = buscadorDeMusicasDaPlaylist.executaServiço(idPlaylist, offset);
		return new ResponseEntity<PlaylistTrack[]>(pagingDePlaylistTrack.getItems(), HttpStatus.OK);
	}
	
	@GetMapping("/listar-top-musicas-do-artista")
	private ResponseEntity<Track[]> listarTopMusicasDoArtista (@RequestParam String idArtista) {
		procuradorDeTopMusicasDoArtista = new ProcuradorDeTopMúsicasDoArtista (autenticador.getTokenUsuario());
		return new ResponseEntity<Track[]>(procuradorDeTopMusicasDoArtista.executaServiço(idArtista), HttpStatus.OK);
	}
	
	@GetMapping("/obter-parametros-das-musicas")
	private ResponseEntity<AudioFeatures[]> parametrosDasMusicas (@RequestParam String[] idsDasMusicas) {
		procuradorDeParametrosDeMusicas = new ProcuradorDeParâmetrosDeMúsicas(autenticador.getTokenUsuario());
		AudioFeatures[] audioFeatures = procuradorDeParametrosDeMusicas.executaServiço(idsDasMusicas);
		return new ResponseEntity<AudioFeatures[]>(audioFeatures, HttpStatus.OK);
	}
	
}
