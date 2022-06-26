import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { NavLink, Outlet } from 'react-router-dom';
import { FiPlus, FiFileText } from 'react-icons/fi';
import NullPlaylistImage from '../../images/NullPlaylistImage.png';
import { ElementHeader } from '../../components/ElementHeader';
import { MapArtists } from '../../components/MapArtists';

export function PublicPlaylist(){
  const playlistId = window.location.href.split("_").pop();

  const [playlistName, setPlaylistName] = useState("");
  const [playlistImage, setPlaylistImage] = useState("");

  function GetSongs(){
        const [playlistSongs, setPlaylistSongs] = useState([]);

        const getPlaylistInfo = () => {
          axios.get(`http://localhost:8080/buscar-por-id/obter-playlist?idDaPlaylist=${playlistId}`).then(res => {
            setPlaylistName(res.data.name);
            setPlaylistSongs(res.data.tracks.items);
            setPlaylistImage(res.data.images[0].url);
          }).catch(err => 
            {
              if(err.response) {
                alert("Tente novamente. Falha na resposta, erro de status code " + err.response.status); 
                window.location.href = "http://localhost:3000/";}
              else if(err.request){
                alert("Tente novamente mais tarde. A requisição foi feita, mas nenhuma resposta foi obtida");
                window.location.href = "http://localhost:3000/";}
              else {
                if (err.message == "res.data.images[0] is undefined") setPlaylistImage(NullPlaylistImage);
                else {
                  alert("Tente novamente mais tarde. Erro na configuração da requisição.");
                  window.location.href = "http://localhost:3000/";}
                }
            });
        }

        useEffect(() => {
          getPlaylistInfo();
        }, []);

        return(
          playlistSongs.map((song) => {
            return (
              <div className="elementRow" key={song.track.id}>
                          <div className="inBlock">
                              <div className="elementRowTextInteract">
                              <NavLink className="navLink"
                                      to={`/song_${song.track.id}`}>
                                  <h1>{song.track.name.substring(0,37)}
                                      {song.track.name.length > 37 ? "..." : null}
                                  </h1>
                                  </NavLink>
                              </div>
                              <div className="inLine">
                                <MapArtists 
                                   artists={song.track.artists.length > 3 ? [song.track.artists[0], song.track.artists[1], song.track.artists[2]] 
                                          : song.track.artists}
                                />
                                {song.track.artists.length > 3 ? <h3 className="elementInLine"> ... </h3> : null}
                                <h3> - </h3>
                                <h3 className="elementInLine"> {song.track.album.releaseDate.split("-")[0]}</h3>
                                <h3> - </h3>
                                <h3 className="elementInLine"> {(Math.floor(song.track.durationMs/60000)).toFixed(0)}:
                                                               {((song.track.durationMs / 1000) % 60).toFixed(0) < 10 ? 0 : null} 
                                                               {((song.track.durationMs / 1000) % 60).toFixed(0)}</h3>
                              </div>
                          </div>
                          <div className="plus">
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
        <ElementHeader
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