import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { NavLink, Outlet } from 'react-router-dom';
import { FiPlus } from 'react-icons/fi';
import { ElementHeader } from '../../components/ElementHeader';
import NullPlaylistImage from '../../images/NullPlaylistImage.png';

export function Album(){
  const albumId = window.location.href.split("_").pop();

  const [albumName, setAlbumName] = useState("");
  const [albumImage, setAlbumImage] = useState("");

  function GetSongs(){
        const [albumSongs, setAlbumSongs] = useState([]);

        const getAlbumInfo = () => {
          axios.get(`http://localhost:8080/buscar-musicas/obter-album?idDoAlbum=${albumId}`).then(res => {
            setAlbumName(res.data.name);
            setAlbumSongs(res.data.tracks.items);
            setAlbumImage(res.data.images[0].url);
          }).catch(err =>{ 
              if(err.response) {
                alert("Tente novamente. Falha na resposta, erro de status code " + err.response.status); 
                window.location.href = "http://localhost:3000/";}
              else if(err.request){
                alert("Tente novamente mais tarde. A requisição foi feita, mas nenhuma resposta foi obtida");
                window.location.href = "http://localhost:3000/";}
              else {
                if (err.message == "res.data.images[0] is undefined") setAlbumImage(NullPlaylistImage);
                else {
                  alert("Tente novamente mais tarde. Erro na configuração da requisição.");
                  window.location.href = "http://localhost:3000/";}
                }
            });
        }

        useEffect(() => {
          getAlbumInfo();
        }, []);

        return(
          albumSongs.map((song) => {
            return (
              <div className="songRow" key={song.id}>
                          <div className="songRowText">
                          <NavLink className="navLink"
                                  to={`/song_${song.id}`}>
                              <h1>{song.name.substring(0,37)}
                                  {song.name.length > 37 ? "..." : null}
                              </h1>
                               </NavLink>
                          </div>
                          <div className="plus">
                                            <div className="divider">
                                            </div>
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
             goBack="/searchalbums"
             elementImage={albumImage}
             elementName={albumName}
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