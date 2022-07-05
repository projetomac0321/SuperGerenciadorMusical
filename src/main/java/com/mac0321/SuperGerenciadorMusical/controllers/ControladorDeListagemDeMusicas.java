package com.mac0321.SuperGerenciadorMusical.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mac0321.SuperGerenciadorMusical.model.services.busca.listagem_musicas.BuscadorDeMúsicasDaPlaylist;
import com.mac0321.SuperGerenciadorMusical.model.services.busca.listagem_musicas.BuscadorDeMúsicasDoÁlbum;
import com.mac0321.SuperGerenciadorMusical.model.services.busca.listagem_musicas.ProcuradorDeTopMúsicasDoArtista;
import com.mac0321.SuperGerenciadorMusical.models.entities.Autenticador;

import se.michaelthelin.spotify.model_objects.specification.Paging;
import se.michaelthelin.spotify.model_objects.specification.PlaylistTrack;
import se.michaelthelin.spotify.model_objects.specification.Track;
import se.michaelthelin.spotify.model_objects.specification.TrackSimplified;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping(value = "/listar-musicas")
public class ControladorDeListagemDeMusicas {
	private Autenticador autenticador = Autenticador.criarAutenticador();
	private BuscadorDeMúsicasDoÁlbum buscadorDeMusicasDoAlbum;
	private BuscadorDeMúsicasDaPlaylist buscadorDeMusicasDaPlaylist;
	private ProcuradorDeTopMúsicasDoArtista procuradorDeTopMusicasDoArtista;
	
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
}
