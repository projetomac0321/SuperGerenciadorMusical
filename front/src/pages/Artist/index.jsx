import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { NavLink, Outlet } from 'react-router-dom';
import { FiPlus } from 'react-icons/fi';
import { ElementHeader } from '../../components/ElementHeader';
import NullArtistImage from '../../images/NullArtistImage.png';

export function Artist(){
  const artistId = window.location.href.split("_").pop();

  const [artistName, setArtistName] = useState("");
  const [artistImage, setArtistImage] = useState("");

  function GetSongs(){
        const [artistSongs, setArtistSongs] = useState([]);

        const getArtistSongs = () => {
          axios.get(`http://localhost:8080/listar-musicas/listar-top-musicas-do-artista?idArtista=${artistId}`).then(res => {
            setArtistSongs(res.data);
          }).catch(err => 
            { 
              if(err.response) {
                alert("Tente novamente. Falha na resposta, erro de status code " + err.response.status); 
                window.location.href = "http://localhost:3000/";}
                else if(err.request){
                  alert("Tente novamente mais tarde. A requisição foi feita, mas nenhuma resposta foi obtida");
                  window.location.href = "http://localhost:3000/";}
                  else {
                    if (err.message == "res.data.images[0] is undefined") setArtistImage(NullArtistImage);
                    else {
                      alert("Tente novamente mais tarde. Erro na configuração da requisição.");
                      window.location.href = "http://localhost:3000/";}
                    }
            });
        }

        const getArtistInfo = () => {
          axios.get(`http://localhost:8080/buscar-por-id/obter-artista?idDoArtista=${artistId}`).then(res => {
            setArtistName(res.data.name);
            setArtistImage(res.data.images[0].url);
          }).catch(err => 
            { 
              if(err.response) {
                alert("Tente novamente. Falha na resposta, erro de status code " + err.response.status); 
                window.location.href = "http://localhost:3000/";}
                else if(err.request){
                  alert("Tente novamente mais tarde. A requisição foi feita, mas nenhuma resposta foi obtida");
                  window.location.href = "http://localhost:3000/";}
                  else {
                    if (err.message == "res.data.images[0] is undefined") setArtistImage(NullArtistImage);
                    else {
                      alert("Tente novamente mais tarde. Erro na configuração da requisição.");
                      window.location.href = "http://localhost:3000/";}
                    }
            });
        }

        useEffect(() => {
          getArtistSongs();
          getArtistInfo();
        }, []);

        return(
          artistSongs.map((song) => {
            return (
              <div className="elementRow" key={song.id}>
                          <div className="elementRowTextInteract">
                          <NavLink className="navLink"
                                  to={`/song_${song.id}`}>
                              <h1>{song.name.substring(0,37)}
                                  {song.name.length > 37 ? "..." : null}
                              </h1>
                              </NavLink>
                          </div>
                          <div className="plus">
                                            <NavLink
                                                    className="navLink"
                                                    to={`/selectplaylist_${song.uri}`}
                                                >
                                                    <FiPlus className="plusIcon"/>
                                                </NavLink>
                          </div>
              </div>
            )
          }
          )
        )
      }

    return(
      <div>
        <ElementHeader
            goBack="/searchartists"
            elementImage={artistImage}
            elementName={artistName}
        />
        <div className="list">
               <nav
              className="navScroll"
            >
               <GetSongs/>
            </nav>
         <Outlet/>
        </div>
      </div>
    );
}
