import React, { useState, useEffect } from 'react';
import './styles.css';
import axios from 'axios';
import { ApresentacaoDoElemento } from '../../Componentes/ApresentacaoDoElemento';
import { BotaoDoParametro } from '../../Componentes/BotaoDoParametro';
import { NavLink, Outlet } from 'react-router-dom';
import NullPlaylistImage from '../../Imagens/NullPlaylistImage.png';
import { TratamentoDeErro } from '../../Componentes/TratamentoDeErro';

export function TabelaDeParametros(){
    const playlistId = window.location.href.split("_").pop().split("/")[0];

    const [playlistName, setPlaylistName] = useState("");
    const [playlistImage, setPlaylistImage] = useState("");
  
    function RecebeDados(){
          const recebeInformacoesDaPlaylist = () => {
            axios.get(`http://localhost:8080/buscar-por-id/obter-playlist?idDaPlaylist=${playlistId}`).then(res => {
              setPlaylistName(res.data.name);
              if(res.data.images.length != 0) setPlaylistImage(res.data.images[0].url);
              else setPlaylistImage(NullPlaylistImage);
            }).catch(err => {
              TratamentoDeErro(err);
            });
          }
  
          useEffect(() => {
            recebeInformacoesDaPlaylist();
          }, []);
        }
  
      return(
       <div>
          <ApresentacaoDoElemento
             elementImage={playlistImage}
             elementName={playlistName}
          />
          <RecebeDados/>
          <div className="parametersButtons">
            <div className="firstLine">
                <NavLink 
                        className="linkTable"      
                        to={`/parametertable/playlist_${playlistId}/acousticness`}>
                  <BotaoDoParametro name="Acousticness"/>
                </NavLink>
                <NavLink 
                        className="linkTable"      
                        to={`/parametertable/playlist_${playlistId}/danceability`}>
                  <BotaoDoParametro name="Danceability"/>
                </NavLink>
                <NavLink 
                        className="linkTable"      
                        to={`/parametertable/playlist_${playlistId}/durationMs`}>
                  <BotaoDoParametro name="DurationMs"/>
                </NavLink>
                <NavLink 
                        className="linkTable"      
                        to={`/parametertable/playlist_${playlistId}/energy`}>
                  <BotaoDoParametro name="Energy"/>
                </NavLink>
                <NavLink 
                        className="linkTable"      
                        to={`/parametertable/playlist_${playlistId}/instrumentalness`}>
                  <BotaoDoParametro name="Instrumentalness"/>
                </NavLink>
                <NavLink 
                        className="linkTable"      
                        to={`/parametertable/playlist_${playlistId}/key`}>
                  <BotaoDoParametro name="Key"/>
                </NavLink>
                </div>
                <div className="secondLine">
                <NavLink 
                        className="linkTable"      
                        to={`/parametertable/playlist_${playlistId}/liveness`}>
                  <BotaoDoParametro name="Liveness"/>
                </NavLink>
                <NavLink 
                        className="linkTable"      
                        to={`/parametertable/playlist_${playlistId}/loudness`}>
                  <BotaoDoParametro name="Loudness"/>
                </NavLink>
                <NavLink 
                        className="linkTable"      
                        to={`/parametertable/playlist_${playlistId}/speechiness`}>
                  <BotaoDoParametro name="Speechiness"/>
                </NavLink>
                <NavLink 
                        className="linkTable"      
                        to={`/parametertable/playlist_${playlistId}/timeSignature`}>
                  <BotaoDoParametro name="TimeSignature"/>
                </NavLink>
                <NavLink 
                        className="linkTable"      
                        to={`/parametertable/playlist_${playlistId}/tempo`}>
                  <BotaoDoParametro name="Tempo"/>
                </NavLink>
                <NavLink 
                        className="linkTable"      
                        to={`/parametertable/playlist_${playlistId}/valence`}>
                  <BotaoDoParametro name="Valence"/>
                </NavLink>
            </div>
          </div>
          <Outlet/>
        </div>
      );
  }