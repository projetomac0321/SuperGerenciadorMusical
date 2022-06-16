import React, { useState, useEffect } from 'react';
import './styles.css';
import { GoBack } from '../../components/GoBack';
import axios from 'axios';
import { NavLink, Outlet } from 'react-router-dom';
import { FiPlus } from 'react-icons/fi';

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
      <div className="container">
                   <GoBack place="/searchalbums"/>
        <div className="listHeader">
          <img className="image" src={albumImage} alt="album image" />
          <h1> {albumName} </h1>
        </div>
          <hr className="listDivider"/>
        <div className="list">
               <nav
              className="bodySongs"
            >
               <GetSongs/>
            </nav>
         <Outlet/>
        </div>
      </div>
    );
}