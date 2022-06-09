package com.mac0321.SuperGerenciadorMusical.services;

import java.io.IOException;

import org.apache.hc.core5.http.ParseException;

import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.specification.User;
import se.michaelthelin.spotify.requests.data.users_profile.GetCurrentUsersProfileRequest;

public class UsuárioAtual {
	 private final SpotifyApi spotifyApi;
	 private final GetCurrentUsersProfileRequest getCurrentUsersProfileRequest;

	 UsuárioAtual(String accessToken){
		 this.spotifyApi = new SpotifyApi.Builder().setAccessToken(accessToken).build();
		 this.getCurrentUsersProfileRequest = spotifyApi.getCurrentUsersProfile().build();
	 }
	 
	 public User getUsuárioAtual(){
		 User user = null;
		 try {
			 user = getCurrentUsersProfileRequest.execute();
			 System.out.println("Usuário atual obtido com sucesso!");
		 } 
		 catch (IOException | SpotifyWebApiException | ParseException exception) {
			System.out.println("Não foi possível encontrar o usuário atualmente logado");
		 }
		 return user;
	 }


}
