import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { NavLink, Outlet } from 'react-router-dom';
import { FiPlus } from 'react-icons/fi';
import NullPlaylistImage from '../../images/NullPlaylistImage.png';
import { ElementStructure } from '../../components/ElementStructure';

export function PublicPlaylist(){
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

        return(
          playlistSongs.map((song) => {
            return (
              <div className="songRow" key={song.track.id}>
                          <div className="songRowText">
                              <h1>{song.track.name}</h1>
                          </div>
                          <div className="plus">
                                            <div className="divider">
                                            </div>
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
        <ElementStructure
          goBack="/searchplaylists"
          elementImage={playlistImage}
          elementName={playlistName}
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