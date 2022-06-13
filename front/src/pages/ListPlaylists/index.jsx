import React, { useState, useEffect } from 'react';
import './styles.css';
import { FiTrash2, FiPlus } from 'react-icons/fi';
import { Outlet, NavLink } from 'react-router-dom';
import axios from 'axios';

export function ListPlaylists(){
  
  function GetPlaylists() {
    
    const [playlists, setPlaylists] = useState([]);
    
    const fetchUserData = () => {
      axios.get("http://localhost:8080/servicos/playlists-listar").then(res => {
        setPlaylists(res.data[0].items);
      }).catch(err => console.log(err.message));
    }
    
    useEffect(() => {
      fetchUserData();
    }, []);

    return (
      playlists.map((playlist) => {
         return (
           <div className="playlistRow"
                key={playlist.id}>
                  <NavLink
                    className="navLink"
                    to={`/listplaylists/playlist_${playlist.id}`}
                  >
                        <div className="playlistRowText">
                            <h1>{playlist.name}</h1>
                        </div>
                  </NavLink>
                  <FiTrash2 className="trashIcon"
                  />
            </div>

         )
      })
      )
    }

    return(
      <div className="container">
        <div className="listHeader">
          <h1> Playlists</h1>
          <hr className="listDivider"/>
        </div>

        <div className="list">
              <nav
              className="bodyPlaylists"
            >
              <GetPlaylists />
              
              <div className="playlistCreate">
                            <NavLink
                                      className="navLink"
                                      to="/createplaylists"
                                    >
                          <div className="playlistCreateText">
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