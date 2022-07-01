import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { NavLink, Outlet } from 'react-router-dom';
import { FiPlus } from 'react-icons/fi';
import { ApresentacaoDoElemento } from '../../Componentes/ApresentacaoDoElemento';
import NullArtistImage from '../../Imagens/NullArtistImage.png';
import { MapearMusica } from '../../Componentes/MapearMusica';
import { TratamentoDeErro } from '../../Componentes/TratamentoDeErro';

export function Artista(){
  const artistId = window.location.href.split("_").pop();

  const [artistName, setArtistName] = useState("");
  const [artistImage, setArtistImage] = useState("");

  function ReceberMusicas(){
        const [artistSongs, setArtistSongs] = useState([]);

        const receberMusicasDoArtista = () => {
          axios.get(`http://localhost:8080/listar-musicas/listar-top-musicas-do-artista?idArtista=${artistId}`).then(res => {
            setArtistSongs(res.data);
          }).catch(err => 
            { 
              TratamentoDeErro(err);
            });
        }

        const receberInformacoesDoArtista = () => {
          axios.get(`http://localhost:8080/buscar-por-id/obter-artista?idDoArtista=${artistId}`).then(res => {
            setArtistName(res.data.name);
            if(res.data.images.length != 0) setArtistImage(res.data.images[0].url);
            else setArtistImage(NullArtistImage);
          }).catch(err => 
            { 
              TratamentoDeErro(err);
            });
        }

        useEffect(() => {
          receberMusicasDoArtista();
          receberInformacoesDoArtista();
        }, []);

        return(
          artistSongs.map((song) => {
            return (
              <div className="elementRow" key={song.id}>
                          <MapearMusica
                             link={`/song_${song.id}`}
                             name={song.name}
                             nameLength={song.name.length}
                             artistsLength={song.artists.length}
                             artists={song.artists}
                             releaseDate={song.album.releaseDate}
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
            goBack="/searchartists"
            elementImage={artistImage}
            elementName={artistName}
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
