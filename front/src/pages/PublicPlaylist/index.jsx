import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { NavLink, Outlet } from 'react-router-dom';
import { FiPlus, FiFileText } from 'react-icons/fi';
import NullPlaylistImage from '../../images/NullPlaylistImage.png';
import { ElementStructure } from '../../components/ElementStructure';

export function PublicPlaylist(){
  const playlistId = window.location.href.split("_").pop();

  const [playlistName, setPlaylistName] = useState("");
  const [playlistImage, setPlaylistImage] = useState("");

  function GetSongs(){
        const [playlistSongs, setPlaylistSongs] = useState([]);

        const getPlaylistInfo = () => {
          axios.get(`http://localhost:8080/buscar-musicas/obter-playlist?idDaPlaylist=${playlistId}`).then(res => {
            setPlaylistName(res.data.name);
            setPlaylistSongs(res.data.tracks.items);
            setPlaylistImage(res.data.images[0].url);
          }).catch(err => 
            {if (err.message == "res.data.images[0] is undefined") setArtistImage(NullPlaylistImage);
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
          getPlaylistInfo();
        }, []);

        return(
          playlistSongs.map((song) => {
            return (
              <div className="songRow" key={song.track.id}>
                          <div className="songRowText">
                          <NavLink className="navLink"
                                   to={`/song_${song.track.id}`}>
                              <h1>{song.track.name}</h1>
                              </NavLink>
                          </div>
                          <div className="plus">
                                            <div className="divider">
                                            </div>
                                            <NavLink
                                                    className="navLink"
                                                    to={`/selectplaylist_${song.track.uri}`}
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
        <ElementStructure
          goBack="/searchplaylists"
          elementImage={playlistImage}
          elementName={playlistName}
        />
        <NavLink className="link containerLink"
                        to={`/parametertable/playlist_${playlistId}`}
                >
                   <FiFileText/>
                   <h3> Mostrar parâmetros</h3>
                </NavLink>
        <div className="list">
               <nav
              className="navScrollSmall"
            >
               <GetSongs/>
            </nav>
         <Outlet/>
        </div>
      </div>
    );
}