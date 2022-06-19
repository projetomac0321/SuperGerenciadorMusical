import React, { useState, useEffect } from 'react';
import axios from 'axios';
import './styles.css';
import NullPlaylistImage from '../../images/NullPlaylistImage.png';
import { ElementHeader } from '../../components/ElementHeader';
import { Parameter } from '../../components/Parameter';

export function SongFromPlaylist(){
  const [playlistId, songId] = window.location.href.split("_").pop().split("/");

  const [songName, setSongName] = useState("");
  const [albumImage, setAlbumImage] = useState("");

  function GetParameters(){
        const getSongInfo = () => {
          axios.get(`http://localhost:8080/buscar-musicas/obter-musicas?idsDasMusicas=${[songId]}`).then(res => {
            setSongName(res.data[0].name);
            setAlbumImage(res.data[0].album.images[0].url);
          }).catch(err => {
            if(err.response) {
              alert("Tente novamente. Falha na resposta, erro de status code " + err.response.status); 
              window.location.href = "http://localhost:3000/";}
            else if(err.request){
              alert("Tente novamente mais tarde. A requisição foi feita, mas nenhuma resposta foi obtida");
              window.location.href = "http://localhost:3000/";}
            else {
              if(err.message == "res.data[0].album is undefined" ||
              err.message == "res.data[0].album.images[0] is undefined") setAlbumImage(NullPlaylistImage);
              else {
                alert("Tente novamente mais tarde. Erro na configuração da requisição.");
                window.location.href = "http://localhost:3000/";
              }
              }
            });
        }

        const [parameters, setParameters] = useState([]);

        const getSongParameters = () => {
            axios.get(`http://localhost:8080/buscar-musicas/obter-parametros-das-musicas?idsDasMusicas=${[songId]}`)
            .then(res => {
                setParameters(res.data[0]);
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
          getSongInfo();
          getSongParameters();
        }, []);

        return(
          <div className="list">
             <nav className="navScroll">
                  <Parameter name="Acousticness" value={parameters.acousticness}/>
                  <Parameter name="Danceability" value={parameters.danceability}/>
                  <Parameter name="DurationMs" value={parameters.durationMs}/>
                  <Parameter name="Energy" value={parameters.energy}/>
                  <Parameter name="Instrumentalness" value={parameters.instrumentalness}/>
                  <Parameter name="Key" value={parameters.key}/>
                  <Parameter name="Liveness" value={parameters.liveness}/>
                  <Parameter name="Loudness" value={parameters.loudness}/>
                  <Parameter name="Mode" value={parameters.mode}/>
                  <Parameter name="Speechiness" value={parameters.speechiness}/>
                  <Parameter name="TimeSignature" value={parameters.timeSignature}/>
                  <Parameter name="Tempo" value={parameters.tempo}/>
                  <Parameter name="Valence" value={parameters.valence}/>
            </nav>
          </div>
        )
      }
      

    return(
     <div>
        <ElementHeader
           elementImage={albumImage}
           elementName={songName}
           goBack={`/listplaylists/playlist_${playlistId}`}
        />
               <GetParameters/>
      </div>
    );
}