package com.mac0321.SuperGerenciadorMusical.resources;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.hc.core5.http.ParseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mac0321.SuperGerenciadorMusical.services.AdicionadorDeMúsicasNumaPlaylist;
import com.mac0321.SuperGerenciadorMusical.services.Autenticador;
import com.mac0321.SuperGerenciadorMusical.services.CriadorDePlaylist;
import com.mac0321.SuperGerenciadorMusical.services.ModeloDeRequisiçãoPlaylists;
import com.mac0321.SuperGerenciadorMusical.services.ProcuradorDePlaylist;
import com.mac0321.SuperGerenciadorMusical.services.ProcuradorDePlaylistsDoUsuárioAtual;
import com.mac0321.SuperGerenciadorMusical.services.RemovedorDeMúsicasNumaPlaylist;
import com.mac0321.SuperGerenciadorMusical.services.RemovedorDePlaylists;

import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.specification.Playlist;
import se.michaelthelin.spotify.model_objects.specification.PlaylistSimplified;
import se.michaelthelin.spotify.model_objects.special.SnapshotResult;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping(value = "/playlists")
public class ServiçosComPlaylists {
	private Autenticador autenticador = Autenticador.criarAutenticador();
	private CriadorDePlaylist criadorDePlaylists;
	private ProcuradorDePlaylistsDoUsuárioAtual listadorDePlaylistsUsuarioAtual;
	private RemovedorDePlaylists removedorDePlaylists;
	private AdicionadorDeMúsicasNumaPlaylist adicionadorDeMusicas;
	private RemovedorDeMúsicasNumaPlaylist removedorDeMusicas;
	private ProcuradorDePlaylist convertePlaylist;
	
	@PostMapping("/criar")
	private ResponseEntity<Playlist> criarPlaylist(@RequestBody Playlist playlist) 
			throws ParseException, SpotifyWebApiException, IOException {
		
		criadorDePlaylists = new CriadorDePlaylist(autenticador.getTokenUsuario());
		Playlist playlistCriada;
		playlistCriada = (Playlist) this.criadorDePlaylists.executaServiço(playlist.getName(), 
																playlist.getIsCollaborative(), 
															    playlist.getIsPublicAccess(), playlist.getDescription());
		
		if(playlistCriada == null) {
			return new ResponseEntity<Playlist>(playlistCriada, HttpStatus.BAD_GATEWAY);
		}
		
		return new ResponseEntity<Playlist>(playlistCriada, HttpStatus.OK);
	}
	
	@DeleteMapping("/remover/{id}")
	private ResponseEntity<Integer> deletarPlaylist(@PathVariable("id") String id){
		removedorDePlaylists = new RemovedorDePlaylists(autenticador.getTokenUsuario());
		int resposta = removedorDePlaylists.executaServiço(id);
		
		if(resposta == 0) {
			return new ResponseEntity<Integer>(resposta, HttpStatus.BAD_GATEWAY);
		}
		
		return new ResponseEntity<Integer>(resposta, HttpStatus.OK);
	}
	
	@GetMapping("/listar")
	private ResponseEntity<ArrayList<Playlist>> listarPlaylists(@RequestParam int offset) 
			throws ParseException, SpotifyWebApiException, IOException {
		ArrayList<Playlist> playlistsUsuario = new ArrayList<>();
		PlaylistSimplified[] respostaAPI;
		
		convertePlaylist = new ProcuradorDePlaylist(autenticador.getTokenUsuario());
		listadorDePlaylistsUsuarioAtual = new ProcuradorDePlaylistsDoUsuárioAtual(autenticador.getTokenUsuario());
		
		respostaAPI = listadorDePlaylistsUsuarioAtual.executaServiço(offset).getItems();
		
		for(int i = 0; i < respostaAPI.length; i++) {
			playlistsUsuario.add(convertePlaylist.executaServiço(respostaAPI[i].getId()));
		}
		
		if(playlistsUsuario.isEmpty()) {
			return new ResponseEntity<ArrayList<Playlist>>(playlistsUsuario, HttpStatus.BAD_GATEWAY);
		}
		
		return new ResponseEntity<ArrayList<Playlist>>(playlistsUsuario, HttpStatus.OK);
	}
	
	@PostMapping("/adicionar-musicas")
	private ResponseEntity<String> adicionarMusicasNumaPlaylist(@RequestBody ModeloDeRequisiçãoPlaylists musicas){
		SnapshotResult musicasAdicionadas;
		
		adicionadorDeMusicas = new AdicionadorDeMúsicasNumaPlaylist(autenticador.getTokenUsuario());
		musicasAdicionadas = (SnapshotResult) adicionadorDeMusicas.executaServiço(musicas.getPlaylistID(), musicas.getUris());
		
		if(musicasAdicionadas == null) {
			return new ResponseEntity<String>("Não foi possível adicionar músicas", HttpStatus.BAD_GATEWAY);
		}
		return new ResponseEntity<String>("Músicas adicionadas com sucesso", HttpStatus.OK);
	}
	
	@DeleteMapping("/remover-musicas")
	private ResponseEntity<String> removerMusicasNumaPlaylist(@RequestBody ModeloDeRequisiçãoPlaylists musicas){
		SnapshotResult musicasRemovidas;
		
		removedorDeMusicas = new RemovedorDeMúsicasNumaPlaylist(autenticador.getTokenUsuario());
		musicasRemovidas = (SnapshotResult) removedorDeMusicas.executaServiço(musicas.getPlaylistID(), musicas.getUris());
		
		if(musicasRemovidas == null) {
			return new ResponseEntity<String>("Não foi possível remover músicas", HttpStatus.BAD_GATEWAY);
		}
		return new ResponseEntity<String>("Músicas removidas com sucesso", HttpStatus.OK);
	}
}
