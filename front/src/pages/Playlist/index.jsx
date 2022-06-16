import React, { useState, useEffect } from 'react';
import './styles.css';
import { FiTrash2 } from 'react-icons/fi';
import { GoBack } from '../../components/GoBack';
import axios from 'axios';
import NullPlaylistImage from '../../images/NullPlaylistImage.png';

export function Playlist(){
  const playlistId = window.location.href.split("_").pop();

  const [playlistName, setPlaylistName] = useState("");
  const [playlistImage, setPlaylistImage] = useState("");

  function GetSongs(){
        const [playlistSongs, setPlaylistSongs] = useState([]);

        const getPlaylistInfo = () => {
          axios.get(`http://localhost:8080/buscar-musicas/obter-playlist?idDaPlaylist=${playlistId}`).then(res => {
            setPlaylistName(res.data.name);
            setPlaylistSongs(res.data.tracks.items);
            if(res.data.images[0] != null)
             setPlaylistImage(res.data.images[0].url);
            else setPlaylistImage(NullPlaylistImage);
          }).catch(err => console.log(err.message));
        }

        useEffect(() => {
          getPlaylistInfo();
        }, []);

        const removeSong = (songUri) => {
          axios.delete(`http://localhost:8080/playlists/remover-musicas?uris=${[songUri]}&playlistID=${playlistId}`,{
          })
          .catch(err => console.log(err.message));
       }
   
       function handleClick(songUri) {
         removeSong(songUri);   
       }

        return(
          playlistSongs.map((song) => {
            return (
              <div className="songRow">
                          <div className="songRowText">
                              <h1>{song.track.name}</h1>
                          </div>
                          <FiTrash2 className="trashIcon" onClick={e => { e.preventDefault(); handleClick(song.track.uri);
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
       <div className="container">
                   <GoBack place="/listplaylists"/>
        <div className="listHeader">
          <img className="image" src={playlistImage} alt="playlist image" />
          <h1> {playlistName} </h1>
        </div>
          <hr className="listDivider"/>
        <div className="list">
               <nav
              className="bodySongs"
            >
               <GetSongs/>
            </nav>
        </div>
      </div>
    );
}