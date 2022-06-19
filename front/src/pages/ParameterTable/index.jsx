import React, { useState, useEffect } from 'react';
import './styles.css';
import axios from 'axios';
import { ElementHeader } from '../../components/ElementHeader';
import { ParameterButton } from '../../components/ParameterButton';
import { NavLink, Outlet } from 'react-router-dom';
import NullPlaylistImage from '../../images/NullPlaylistImage.png';

export function ParameterTable(){
    const playlistId = window.location.href.split("_").pop().split("/")[0];

    const [playlistName, setPlaylistName] = useState("");
    const [playlistImage, setPlaylistImage] = useState("");
  
    function GetData(){
          const getPlaylistInfo = () => {
            axios.get(`http://localhost:8080/buscar-por-id/obter-playlist?idDaPlaylist=${playlistId}`).then(res => {
              setPlaylistName(res.data.name);
              setPlaylistImage(res.data.images[0].url);
            }).catch(err => {
              if(err.response) {
                alert("Tente novamente. Falha na resposta, erro de status code " + err.response.status); 
                window.location.href = "http://localhost:3000/";}
                else if(err.request){
                  alert("Tente novamente mais tarde. A requisição foi feita, mas nenhuma resposta foi obtida");
                  window.location.href = "http://localhost:3000/";}
                  else {
                    if(err.message == "res.data.images[0] is undefined") setPlaylistImage(NullPlaylistImage);
                    else {
                      alert("Tente novamente mais tarde. Erro na configuração da requisição.");
                      window.location.href = "http://localhost:3000/";}
                    }
            });
          }
  
          useEffect(() => {
            getPlaylistInfo();
          }, []);
        }
  
      return(
       <div>
          <ElementHeader
             elementImage={playlistImage}
             elementName={playlistName}
          />
          <GetData/>
          <div className="parametersButtons">
            <div className="firstLine">
                <NavLink 
                        className="linkTable"      
                        to={`/parametertable/playlist_${playlistId}/acousticness`}>
                  <ParameterButton name="Acousticness"/>
                </NavLink>
                <NavLink 
                        className="linkTable"      
                        to={`/parametertable/playlist_${playlistId}/danceability`}>
                  <ParameterButton name="Danceability"/>
                </NavLink>
                <NavLink 
                        className="linkTable"      
                        to={`/parametertable/playlist_${playlistId}/durationMs`}>
                  <ParameterButton name="DurationMs"/>
                </NavLink>
                <NavLink 
                        className="linkTable"      
                        to={`/parametertable/playlist_${playlistId}/energy`}>
                  <ParameterButton name="Energy"/>
                </NavLink>
                <NavLink 
                        className="linkTable"      
                        to={`/parametertable/playlist_${playlistId}/instrumentalness`}>
                  <ParameterButton name="Instrumentalness"/>
                </NavLink>
                <NavLink 
                        className="linkTable"      
                        to={`/parametertable/playlist_${playlistId}/key`}>
                  <ParameterButton name="Key"/>
                </NavLink>
                </div>
                <div className="secondLine">
                <NavLink 
                        className="linkTable"      
                        to={`/parametertable/playlist_${playlistId}/liveness`}>
                  <ParameterButton name="Liveness"/>
                </NavLink>
                <NavLink 
                        className="linkTable"      
                        to={`/parametertable/playlist_${playlistId}/loudness`}>
                  <ParameterButton name="Loudness"/>
                </NavLink>
                <NavLink 
                        className="linkTable"      
                        to={`/parametertable/playlist_${playlistId}/speechiness`}>
                  <ParameterButton name="Speechiness"/>
                </NavLink>
                <NavLink 
                        className="linkTable"      
                        to={`/parametertable/playlist_${playlistId}/timeSignature`}>
                  <ParameterButton name="TimeSignature"/>
                </NavLink>
                <NavLink 
                        className="linkTable"      
                        to={`/parametertable/playlist_${playlistId}/tempo`}>
                  <ParameterButton name="Tempo"/>
                </NavLink>
                <NavLink 
                        className="linkTable"      
                        to={`/parametertable/playlist_${playlistId}/valence`}>
                  <ParameterButton name="Valence"/>
                </NavLink>
            </div>
          </div>
          <Outlet/>
        </div>
      );
  }