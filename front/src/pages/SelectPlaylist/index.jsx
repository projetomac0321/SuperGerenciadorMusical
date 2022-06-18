import React, { useState, useEffect } from 'react';
import axios from 'axios';
import './styles.css';

export function SelectPlaylist(){
    const songUri = window.location.href.split("_").pop();

    function GetPlaylists() {
    
        const [playlists, setPlaylists] = useState([]);
        
        const fetchUserData = () => {
          axios.get("http://localhost:8080/playlists/listar?offset=0").then(res => {
            setPlaylists(res.data);
          }).catch(err => {
            if(err.response) {
              alert("Tente novamente. Falha na resposta, erro de status code " + err.response.status); 
              window.location.href = "http://localhost:3000/";}
            else if(err.request){
              alert("Tente novamente mais tarde. A requisição foi feita, mas nenhuma resposta foi obtida");
              window.location.href = "http://localhost:3000/";}
            else {
              alert("Tente novamente mais tarde. Erro na configuração da requisição.");
              window.location.href = "http://localhost:3000/";}
          });
        }
        
        useEffect(() => {
          fetchUserData();
        }, []);

        const addToPlaylist = (playlistId) => {
            axios.post("http://localhost:8080/playlists/adicionar-musicas", {
                 playlistID: playlistId,
                 uris: [songUri],
            }).catch(err => {
              if(err.response) {
                alert("Tente novamente. Falha na resposta, erro de status code " + err.response.status); 
                window.location.href = "http://localhost:3000/";}
              else if(err.request){
                alert("Tente novamente mais tarde. A requisição foi feita, mas nenhuma resposta foi obtida");
                window.location.href = "http://localhost:3000/";}
              else {
                alert("Tente novamente mais tarde. Erro na configuração da requisição.");
                window.location.href = "http://localhost:3000/";}
            });
        }
    
        function handleClick(playlistId) {
          addToPlaylist(playlistId);   
        }
    
        return (
          playlists.map((playlist) => {
             return (
               <div className="playlistRow"
                    key={playlist.id}>
                          <div className="playlistRowName" onClick={e => { e.preventDefault(); handleClick(playlist.id); 
                            setTimeout(function(){
                              window.location.href = `http://localhost:3000/listplaylists/playlist_${playlist.id}`;}, 1200);
                            }}>
                                <h1>{playlist.name}</h1>
                            </div>
                </div>
    
             )
          })
          )
        }

    return(
      <div className="container">
              <div className="selectlistHeader">
              <h1> Selecione a playlist </h1>
              <hr className="selectlistDivider"/>
              </div>
              <nav className="navScroll selectPlaylists">
                <GetPlaylists/>
              </nav>
        </div>
    )
}