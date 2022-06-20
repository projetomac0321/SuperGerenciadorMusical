package com.mac0321.SuperGerenciadorMusical.models.services;

import java.io.IOException;

import org.apache.hc.core5.http.ParseException;

import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.specification.User;
import se.michaelthelin.spotify.requests.data.users_profile.GetCurrentUsersProfileRequest;

public class UsuárioAtual extends ServiçoSpotify {
	
	 private GetCurrentUsersProfileRequest getCurrentUsersProfileRequest;

	 UsuárioAtual(String accessToken){
		 super(accessToken);
	 }
	 
	 public User executaServiço(){
		 User user = null;
		 try {
			 this.getCurrentUsersProfileRequest = this.spotifyApi.getCurrentUsersProfile().build();
			 user = getCurrentUsersProfileRequest.execute();
			 System.out.println("Usuário atual obtido com sucesso!");
		 } 
		 catch (NullPointerException | IOException | SpotifyWebApiException | ParseException exception) {
			System.out.println("Não foi possível encontrar o usuário atualmente logado");
		 }
		 return user;
	 }


}
