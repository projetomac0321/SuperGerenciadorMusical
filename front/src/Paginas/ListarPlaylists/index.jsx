import React, { useState, useEffect } from 'react';
import './styles.css';
import { FiTrash2, FiPlus } from 'react-icons/fi';
import { Outlet, NavLink } from 'react-router-dom';
import axios from 'axios';
import NullPlaylistImage from '../../Imagens/NullPlaylistImage.png';
import { TratamentoDeErro } from '../../Componentes/TratamentoDeErro';

export function ListarPlaylists(){
  
  function RecebePlaylists() {
    
    const [playlists, setPlaylists] = useState([]);
    
    const buscarDados = () => {
      axios.get("http://localhost:8080/playlists/listar?offset=0").then(res => {
        setPlaylists(res.data);
      }).catch(err => {
        TratamentoDeErro(err);
      });
    }
    
    useEffect(() => {
      buscarDados();
    }, []);

     const deletarPlaylist = (playlistId) => {
       axios.delete(`http://localhost:8080/playlists/remover/${playlistId}`)
       .catch(err => {
        TratamentoDeErro(err);
      });
    }

    function gerenciaClick(playlist) {
      deletarPlaylist(playlist);   
    }

    return (
      playlists.map((playlist) => {
         return (
           <div className="elementRow"
                key={playlist.id}>
                  <div className="elementInfo">
                     <img className="elementImage" src={playlist.images[0] != null ? playlist.images[0].url : NullPlaylistImage} alt="" />
                  <NavLink
                    className="navLink"
                    to={`/listplaylists/playlist_${playlist.id}`}
                  >
                        <div className="elementRowTextInteract">
                        <h1>{playlist.name.substring(0,37)}
                            {playlist.name.length > 37 ? "..." : null}
                        </h1>
                        </div>
                  </NavLink>
                  </div>
                  <FiTrash2 className="trashIcon" onClick={e => { e.preventDefault(); gerenciaClick(playlist.id);
                        setTimeout(function(){
                          buscarDados();}, 500);
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
              <RecebePlaylists />
              
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