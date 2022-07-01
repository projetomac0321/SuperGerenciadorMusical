import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { NavLink, Outlet } from 'react-router-dom';
import { FiPlus } from 'react-icons/fi';
import { ApresentacaoDoElemento } from '../../Componentes/ApresentacaoDoElemento';
import NullPlaylistImage from '../../Imagens/NullPlaylistImage.png';
import { MapearMusica } from '../../Componentes/MapearMusica';
import { TratamentoDeErro } from '../../Componentes/TratamentoDeErro';

export function Album(){
  const albumId = window.location.href.split("_").pop();

  const [albumName, setAlbumName] = useState("");
  const [albumImage, setAlbumImage] = useState("");
  const [albumReleaseYear, setAlbumReleaseYear] = useState("");

  function ReceberMusicas(){
        const [albumSongs, setAlbumSongs] = useState([]);

        const receberInformacoesDoAlbum = () => {
          axios.get(`http://localhost:8080/buscar-por-id/obter-album?idDoAlbum=${albumId}`).then(res => {
            setAlbumName(res.data.name);
            setAlbumReleaseYear(res.data.releaseDate.split("-")[0]);
            setAlbumSongs(res.data.tracks.items);
            if(res.data.images.length != 0) setAlbumImage(res.data.images[0].url);
            else setAlbumImage(NullPlaylistImage);
          }).catch(err =>{ 
              TratamentoDeErro(err);
            });
        }

        useEffect(() => {
          receberInformacoesDoAlbum();
        }, []);

        return(
          albumSongs.map((song) => {
            return (
              <div className="elementRow" key={song.id}>
                          <MapearMusica
                             link={`/song_${song.id}`}
                             name={song.name}
                             nameLength={song.name.length}
                             artistsLength={song.artists.length}
                             artists={song.artists}
                             durationMs={song.durationMs}
                          />
                          <div className="plus">
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
          <ApresentacaoDoElemento
             goBack="/searchalbums"
             elementImage={albumImage}
             elementName={albumName}
             releaseYear={albumReleaseYear}
          />
        <div className="list">
               <nav
              className="navScroll"
            >
               <ReceberMusicas/>
            </nav>
         <Outlet/>
        </div>
      </div>
    );
}