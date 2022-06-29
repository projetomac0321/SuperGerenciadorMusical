import React, { useState, useEffect } from 'react';
import { FiFileText, FiPlay, FiTrash2 } from 'react-icons/fi';
import axios from 'axios';
import NullPlaylistImage from '../../images/NullPlaylistImage.png';
import { ElementHeader } from '../../components/ElementHeader';
import { NavLink, Outlet } from 'react-router-dom';
import { MapArtists } from '../../components/MapArtists';

export function Playlist(){
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
                else{
                  alert("Tente novamente mais tarde. Erro na configuração da requisição.");
                  window.location.href = "http://localhost:3000/";}
                }
            });
        }

        useEffect(() => {
          getPlaylistInfo();
        }, []);

        const removeSong = (songUri) => {
          axios.delete(`http://localhost:8080/playlists/remover-musicas?uris=${[songUri]}&playlistID=${playlistId}`,{
          })
          .catch(err => {
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
   
       function handleRemove(songUri) {
         removeSong(songUri);   
       }

       const playSong = (songUri) => {
        axios.post(`http://localhost:8080/playback/criar-playback?uriDaMusica=${songUri}`)
        .catch(err => {
          console.log(err)
        });
       }

       function handlePlay(songUri) {
         playSong(songUri);
       }

        return(
          playlistSongs.map((song) => {
            return (
              <div className="elementRow" key={song.track.id}>
                   <div className="leftSide">
                          <FiPlay className="playIcon" onClick={e => { e.preventDefault(); handlePlay(song.track.uri)}}/>
                          <div className="inBlock">
                              <div className="elementRowTextInteract">
                                <NavLink className="navLink"
                                        to={`/listplaylists/playlistsong_${playlistId}/${song.track.id}`}>
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
                   </div>
                          <FiTrash2 className="trashIcon" onClick={e => { e.preventDefault(); handleRemove(song.track.uri);
                        setTimeout(function(){
                          getPlaylistInfo();}, 1000);
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
        <ElementHeader
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
               <GetSongs/>
            </nav>
        </div>
          <Outlet/>
      </div>
    );
}