import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { NavLink, Outlet } from 'react-router-dom';
import { FiPlus } from 'react-icons/fi';
import { ElementStructure } from '../../components/ElementStructure';
import NullArtistImage from '../../images/NullArtistImage.png';

export function Artist(){
  const artistId = window.location.href.split("_").pop();

  function GetSongs(){
        const [artistSongs, setArtistSongs] = useState([]);

        const getArtistInfo = () => {
          axios.get(`http://localhost:8080/buscar-musicas/listar-top-musicas-do-artista?idArtista=${artistId}`).then(res => {
            setArtistSongs(res.data);
            console.log(res.data);
          }).catch(err => console.log(err.message));
        }

        useEffect(() => {
          getArtistInfo();
        }, []);

        return(
          artistSongs.map((song) => {
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
            goBack="/searchartists"
            elementImage={NullArtistImage}
            elementName="Músicas do(a) Artista"
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
