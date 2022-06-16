import React, { useState, useEffect } from 'react';
import './styles.css';
import { GoBack } from '../../components/GoBack';
import axios from 'axios';

export function Album(){
  const albumId = window.location.href.split("_").pop();

  const [albumName, setAlbumName] = useState("");
  function GetSongs(){
        const [albumSongs, setAlbumSongs] = useState([]);

        const getAlbumInfo = () => {
          axios.get(`http://localhost:8080/buscar-musicas/obter-album?idDoAlbum=${albumId}`).then(res => {
            setAlbumName(res.data.name);
            setAlbumSongs(res.data.tracks.items);
          }).catch(err => console.log(err.message));
        }

        useEffect(() => {
          getAlbumInfo();
        }, []);

        return(
          albumSongs.map((song) => {
            return (
              <div className="songRow">
                          <div className="songRowText">
                              <h1>{song.name}</h1>
                          </div>
              </div>
            )
          }
          )
        )
      }

    return(
      <div className="container">
                   <GoBack place="/searchalbums"/>
        <div className="listHeader">
          <h1> {albumName}</h1>
          <hr className="listDivider"/>
        </div>
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