import React, { useState, useEffect } from 'react';
import axios from 'axios';
import './styles.css';
import NullPlaylistImage from '../../images/NullPlaylistImage.png';
import { ElementStructure } from '../../components/ElementStructure';
import { Parameter } from '../../components/Parameter';
import { Outlet, NavLink } from 'react-router-dom';
import { FiPlus } from 'react-icons/fi';

export function Song(){
  const songId = window.location.href.split("_").pop();

  const [songName, setSongName] = useState("");
  const [songUri, setSongUri] = useState("");
  const [albumImage, setAlbumImage] = useState("");

  function GetParameters(){
        const getSongInfo = () => {
          axios.get(`http://localhost:8080/buscar-musicas/obter-musicas?idsDasMusicas=${[songId]}`).then(res => {
            setSongName(res.data[0].name);
            setSongUri(res.data[0].uri);
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
                  <NavLink
                     className="navLink addSong"
                     to={`/selectplaylist_${songUri}`}
                  >
                     <h1> Adicionar música </h1>
                     <FiPlus className="plusIcon"/>
                 </NavLink>
            </nav>
          </div>
        )
      }
      

    return(
     <div>
        <ElementStructure
           elementImage={albumImage}
           elementName={songName}
        />
               <GetParameters/>
               <Outlet/>
      </div>
    );
}