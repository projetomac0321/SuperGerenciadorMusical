import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { NavLink, Outlet } from 'react-router-dom';
import { FiPlus } from 'react-icons/fi';
import { ElementHeader } from '../../components/ElementHeader';
import NullPlaylistImage from '../../images/NullPlaylistImage.png';
import { MapArtists } from '../../components/MapArtists';

export function Album(){
  const albumId = window.location.href.split("_").pop();

  const [albumName, setAlbumName] = useState("");
  const [albumImage, setAlbumImage] = useState("");
  const [albumReleaseYear, setAlbumReleaseYear] = useState("");

  function GetSongs(){
        const [albumSongs, setAlbumSongs] = useState([]);

        const getAlbumInfo = () => {
          axios.get(`http://localhost:8080/buscar-por-id/obter-album?idDoAlbum=${albumId}`).then(res => {
            setAlbumName(res.data.name);
            setAlbumReleaseYear(res.data.releaseDate.split("-")[0]);
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
              <div className="elementRow" key={song.id}>
                          <div className="inBlock">
                              <div className="elementRowTextInteract">
                              <NavLink className="navLink"
                                      to={`/song_${song.id}`}>
                                  <h1>{song.name.substring(0,37)}
                                      {song.name.length > 37 ? "..." : null}
                                  </h1>
                                  </NavLink>
                              </div>
                              <div className="inLine">
                                    <MapArtists 
                                      artists={song.artists.length > 3 ? [song.artists[0], song.artists[1], song.artists[2]] 
                                              : song.artists}
                                    />
                                    {song.artists.length > 3 ? <h3 className="elementInLine"> ... </h3> : null}
                                    <h3> - </h3>
                                    <h3 className="elementInLine"> {(Math.floor(song.durationMs/60000)).toFixed(0)}:
                                                                  {((song.durationMs / 1000) % 60).toFixed(0) < 10 ? 0 : null} 
                                                                  {((song.durationMs / 1000) % 60).toFixed(0)}</h3>
                                  </div>
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
             goBack="/searchalbums"
             elementImage={albumImage}
             elementName={albumName}
             releaseYear={albumReleaseYear}
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