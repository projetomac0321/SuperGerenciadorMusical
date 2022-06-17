import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { NavLink, Outlet } from 'react-router-dom';
import { FiPlus } from 'react-icons/fi';
import { ElementStructure } from '../../components/ElementStructure';
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
            if(res.data.images[0] != null) setAlbumImage(res.data.images[0].url);
            else setAlbumImage(NullPlaylistImage);
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
      <div>
          <ElementStructure
             goBack="/searchalbums"
             elementImage={albumImage}
             elementName={albumName}
          />
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