import React, { useState, useEffect } from 'react';
import { FiFileText, FiTrash2, FiPlay, FiPlus } from 'react-icons/fi';
import axios from 'axios';
import NullPlaylistImage from '../../Imagens/NullPlaylistImage.png';
import { ApresentacaoDoElemento } from '../ApresentacaoDoElemento';
import { NavLink, Outlet } from 'react-router-dom';
import { MapearMusica } from '../MapearMusica';
import { TratamentoDeErro } from '../TratamentoDeErro';

export function BuscaDadosDaPlaylist({ goBackRoute, isOwner}){
  const playlistId = window.location.href.split("_").pop();

  const [playlistName, setPlaylistName] = useState("");
  const [playlistImage, setPlaylistImage] = useState("");

  function RecebeMusicas(){
        const [playlistSongs, setPlaylistSongs] = useState([]);

        const recebeInformacaoDaPlaylist = () => {
          axios.get(`http://localhost:8080/buscar-por-id/obter-playlist?idDaPlaylist=${playlistId}`).then(res => {
            setPlaylistName(res.data.name);
            setPlaylistSongs(res.data.tracks.items);
            {(res.data.images.length != 0)? setPlaylistImage(res.data.images[0].url) :
            setPlaylistImage(NullPlaylistImage)}
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
   
       function gerenciaRemocao(songUri) {
         removeMusica(songUri);   
       }

       const recebePreview = (songId) => {
        axios.get(`http://localhost:8080/buscar-por-id/obter-musica-com-market?idDaMusica=${songId}`).then(res => {
          {(res.data.previewUrl != null)? window.open(res.data.previewUrl) :
          alert("A música não permite preview.")}
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
                             link={
                                isOwner ? `/listplaylists/playlistsong_${playlistId}/${song.track.id}` : `/song_${song.track.id}`
                             }
                             name={song.track.name}
                             nameLength={song.track.name.length}
                             artistsLength={song.track.artists.length}
                             artists={song.track.artists}
                             releaseDate={song.track.album.releaseDate}
                             durationMs={song.track.durationMs}
                          />
                </div>
                        { isOwner ?
                          <FiTrash2 className="trashIcon" onClick={e => { e.preventDefault(); gerenciaRemocao(song.track.uri);
                        setTimeout(function(){
                          recebeInformacaoDaPlaylist();}, 1000);
                        }} 
                    /> :
                    <div className="plus">
                            <NavLink
                                    className="navLink"
                                    to={`/selectplaylist_${song.track.uri}`}
                                >
                                    <FiPlus className="plusIcon"/>
                            </NavLink>
                    </div>
                      }
              </div>
            )
          }
          )
        )
      }

    return(
     <div>
        <ApresentacaoDoElemento
           goBack={goBackRoute}
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