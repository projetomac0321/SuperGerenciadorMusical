import React, { useState, useEffect } from 'react';
import { FiFileText, FiTrash2, FiPlay } from 'react-icons/fi';
import axios from 'axios';
import NullPlaylistImage from '../../Imagens/NullPlaylistImage.png';
import { ApresentacaoDoElemento } from '../../Componentes/ApresentacaoDoElemento';
import { NavLink, Outlet } from 'react-router-dom';
import { MapearMusica } from '../../Componentes/MapearMusica';
import { TratamentoDeErro } from '../../Componentes/TratamentoDeErro';

export function Playlist(){
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

        const removeMusica = (songUri) => {
          axios.delete(`http://localhost:8080/playlists/remover-musicas?uris=${[songUri]}&playlistID=${playlistId}`,{
          })
          .catch(err => {
            TratamentoDeErro(err);
          });
       }
   
       function gerenciaClick(songUri) {
         removeMusica(songUri);   
       }

       const recebePreview = (songId) => {
        axios.get(`http://localhost:8080/buscar-por-id/obter-musica-com-market?idDaMusica=${songId}`).then(res => {
          if(res.data.previewUrl != null) window.open(res.data.previewUrl);
          else alert("A música não permite preview.");
        }).catch(err => 
          { 
            TratamentoDeErro(err);
          });
      }

       function gerenciaPreview(songId) {
          recebePreview(songId);
       }

        return(
          playlistSongs.map((song) => {
            return (
              <div className="elementRow" key={song.track.id}>
                <div className="inLine">
                         <FiPlay className="playIcon" onClick={e => { e.preventDefault(); gerenciaPreview(song.track.id)}}/>
                          <MapearMusica
                             link={`/listplaylists/playlistsong_${playlistId}/${song.track.id}`}
                             name={song.track.name}
                             nameLength={song.track.name.length}
                             artistsLength={song.track.artists.length}
                             artists={song.track.artists}
                             releaseDate={song.track.album.releaseDate}
                             durationMs={song.track.durationMs}
                          />
                </div>
                          <FiTrash2 className="trashIcon" onClick={e => { e.preventDefault(); gerenciaClick(song.track.uri);
                        setTimeout(function(){
                          recebeInformacaoDaPlaylist();}, 1000);
                        }}
                    />
              </div>
            )
          }
          )
        )
      }

    return(
     <div>
        <ApresentacaoDoElemento
           goBack="/listplaylists"
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
        </div>
          <Outlet/>
      </div>
    );
}