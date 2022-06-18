import React, { useState, useEffect } from 'react';
import './styles.css';
import axios from 'axios';
import { ElementStructure } from '../../components/ElementStructure';
import { ParameterButton } from '../../components/ParameterButton';
import { NavLink, Outlet } from 'react-router-dom';

export function ParameterTable(){
    const [playlistId, trash] = window.location.href.split("_").pop().split("/");

    const [playlistName, setPlaylistName] = useState("");
    const [playlistImage, setPlaylistImage] = useState("");
  
    function GetData(){
          const getPlaylistInfo = () => {
            axios.get(`http://localhost:8080/buscar-musicas/obter-playlist?idDaPlaylist=${playlistId}`).then(res => {
              setPlaylistName(res.data.name);
              if(res.data.images[0] != null)
               setPlaylistImage(res.data.images[0].url);
              else setPlaylistImage(NullPlaylistImage);
            }).catch(err => console.log(err.message));
          }
  
          useEffect(() => {
            getPlaylistInfo();
          }, []);
        }
  
      return(
       <div>
          <ElementStructure
             goBack={`/listplaylists/playlist_${playlistId}`}
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