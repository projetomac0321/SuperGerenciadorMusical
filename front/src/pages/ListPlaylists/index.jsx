import React, { useState, useEffect } from 'react';
import './styles.css';
import { FiTrash2, FiPlus } from 'react-icons/fi';
import { Outlet, NavLink } from 'react-router-dom';
import axios from 'axios';
import NullPlaylistImage from '../../images/NullPlaylistImage.png';

export function ListPlaylists(){
  
  function GetPlaylists() {
    
    const [playlists, setPlaylists] = useState([]);
    
    const fetchUserData = () => {
      axios.get("http://localhost:8080/playlists/listar?offset=0").then(res => {
        setPlaylists(res.data);
      }).catch(err => console.log(err.message));
    }
    
    useEffect(() => {
      fetchUserData();
    }, []);

     const deletePlaylist = (playlistId) => {
       axios.delete(`http://localhost:8080/playlists/remover/${playlistId}`)
       .catch(err => console.log(err.message));
    }

    function handleClick(playlist) {
      deletePlaylist(playlist);   
    }

    return (
      playlists.map((playlist) => {
         return (
           <div className="playlistRow"
                key={playlist.id}>
                  <div className="playlistInfo">
                     <img className="elementImage" src={playlist.images[0] != null ? playlist.images[0].url : NullPlaylistImage} alt="" />
                  <NavLink
                    className="navLink"
                    to={`/listplaylists/playlist_${playlist.id}`}
                  >
                        <div className="playlistRowName">
                            <h1>{playlist.name}</h1>
                        </div>
                  </NavLink>
                  </div>
                  <FiTrash2 className="trashIcon" onClick={e => { e.preventDefault(); handleClick(playlist.id);
                        setTimeout(function(){
                          fetchUserData();}, 500);
                        }}
                  />
            </div>

         )
      })
      )
    }

    return(
      <div className="container">
        <div className="listPlaylistsHeader">
          <h1> Playlists</h1>
          <hr className="listPlaylistsDivider"/>
        </div>

        <div className="listPlaylists">
              <nav
              className="navScroll"
            >
              <GetPlaylists />
              
              <div className="elementCreate">
                            <NavLink
                                      className="navLink"
                                      to="/createplaylists"
                                    >
                          <div className="elementCreateText">
                            <h1>Criar Playlist</h1>
                          </div>
                            </NavLink> 
                          <NavLink
                                        className="navLink"
                                        to="/createplaylists"
                                      >
                                  <FiPlus className="plusIcon"/>
                          </NavLink>
                      </div>
            </nav>
            <Outlet />
        </div>
      </div>
    );
}