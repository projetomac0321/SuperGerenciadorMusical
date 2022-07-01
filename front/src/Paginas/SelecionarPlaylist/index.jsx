import React, { useState, useEffect } from 'react';
import axios from 'axios';
import './styles.css';
import { TratamentoDeErro } from '../../Componentes/TratamentoDeErro';

export function SelecionarPlaylist(){
    const songUri = window.location.href.split("_").pop();

    function RecebePlaylists() {
    
        const [playlists, setPlaylists] = useState([]);
        
        const buscarDados = () => {
          axios.get("http://localhost:8080/playlists/listar?offset=0").then(res => {
            setPlaylists(res.data);
          }).catch(err => {
            TratamentoDeErro(err);
          });
        }
        
        useEffect(() => {
          buscarDados();
        }, []);

        const adicionarNaPlaylist = (playlistId) => {
            axios.post("http://localhost:8080/playlists/adicionar-musicas", {
                 playlistID: playlistId,
                 uris: [songUri],
            }).catch(err => {
              TratamentoDeErro(err);
            });
        }
    
        function gerenciaClick(playlistId) {
          adicionarNaPlaylist(playlistId);   
        }
    
        return (
          playlists.map((playlist) => {
             return (
               <div className="elementRow"
                    key={playlist.id}>
                          <div className="elementRowTextInteract" onClick={e => { e.preventDefault(); gerenciaClick(playlist.id); 
                            setTimeout(function(){
                              window.location.href = `http://localhost:3000/listplaylists/playlist_${playlist.id}`;}, 1200);
                            }}>
                                <h1>{playlist.name.substring(0,37)}
                                    {playlist.name.length > 37 ? "..." : null}
                                </h1>
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
                <RecebePlaylists/>
              </nav>
        </div>
    )
}