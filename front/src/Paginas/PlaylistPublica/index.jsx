import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { NavLink, Outlet } from 'react-router-dom';
import { FiPlus, FiFileText, FiPlay } from 'react-icons/fi';
import NullPlaylistImage from '../../Imagens/NullPlaylistImage.png';
import { ApresentacaoDoElemento } from '../../Componentes/ApresentacaoDoElemento';
import { MapearMusica } from '../../Componentes/MapearMusica';
import { TratamentoDeErro } from '../../Componentes/TratamentoDeErro';

export function PlaylistPublica(){
  const playlistId = window.location.href.split("_").pop();

  const [playlistName, setPlaylistName] = useState("");
  const [playlistImage, setPlaylistImage] = useState("");

  function RecebeMusicas(){
        const [playlistSongs, setPlaylistSongs] = useState([]);

        const recebeInformacaoDaPlaylist = () => {
          axios.get(`http://localhost:8080/buscar-por-id/obter-playlist?idDaPlaylist=${playlistId}`).then(res => {
            setPlaylistName(res.data.name);
            setPlaylistSongs(res.data.tracks.items);
            if(res.data.images.length != 0) setPlaylistImage(res.data.images[0].url);
            else setPlaylistImage(NullPlaylistImage);
          }).catch(err => 
            {
              TratamentoDeErro(err);
            });
        }

        useEffect(() => {
          recebeInformacaoDaPlaylist();
        }, []);

        return(
          playlistSongs.map((song) => {
            return (
              <div className="elementRow" key={song.track.id}>
                <div className="inLine">
                     {song.track.isPlayable != null ? <FiPlay className="playIcon"/> : null}
                          <MapearMusica
                             link={`/song_${song.track.id}`}
                             name={song.track.name}
                             nameLength={song.track.name.length}
                             artistsLength={song.track.artists.length}
                             artists={song.track.artists}
                             releaseDate={song.track.album.releaseDate}
                             durationMs={song.track.durationMs}
                          />
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
        <ApresentacaoDoElemento
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
               <RecebeMusicas/>
            </nav>
         <Outlet/>
        </div>
      </div>
    );
}