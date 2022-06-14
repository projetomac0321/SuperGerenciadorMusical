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

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping(value = "/buscar-musicas")
public class GerenciadorDeBuscasDeMúsicas {
	private Autenticador autenticador = Autenticador.criarAutenticador();
	private BuscadorDeMúsicasPorTag buscadorDeMusicaPorTag;
	private BuscadorDeÁlbuns buscadorDeAlbuns;

	@GetMapping("/query-de-procura")
	private ResponseEntity<Track[]> listarMusicasPorQuery(@RequestParam String tagDeProcura, @RequestParam int offset) {
		buscadorDeMusicaPorTag = new BuscadorDeMúsicasPorTag(autenticador.getTokenUsuario());
		Paging<Track> pagingDeMusicas;
		pagingDeMusicas = (Paging<Track>) buscadorDeMusicaPorTag.executaServiço(tagDeProcura, offset);
		return new ResponseEntity<Track[]>(pagingDeMusicas.getItems(), HttpStatus.OK);
	}

}
