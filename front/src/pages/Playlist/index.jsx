import React from 'react';
import { NavLink, Outlet } from 'react-router-dom';
import './styles.css';
import { FiTrash2 } from 'react-icons/fi';
import { GoBack } from '../../components/GoBack';

export function Playlist(){
  let playlistId = window.location.href.split("_").pop();

    return(
      <div className="container">
                   <GoBack place="/listplaylists"/>
        <div className="listHeader">
          <h1> playlist.name</h1>
          <hr className="listDivider"/>
        </div>

        <div className="list">
              <p>Duração : playlist.duration</p>
              <p>Data de criação : playlist.creationDate</p>

              {/* <nav
              className="bodySongs"
            >
                   {playlist.songs.map((song) => (
                    <div className="songRow">
                          <NavLink
                            className="navLink"
                            to={`/listplaylists/playlist_${playlist.id}/musica_${song.songId}`}
                            key={song.songId}
                          >
                                <div className="songRowText">
                                    <h1>{song.songName}</h1>
                                </div>
                          </NavLink>
                          <FiTrash2 className="trashIcon"/>
                    </div>
                  ))}
            </nav> */}
            <Outlet />
        </div>
      </div>
    );
}