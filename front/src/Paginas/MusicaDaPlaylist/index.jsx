import React, { useState, useEffect } from 'react';
import axios from 'axios';
import './styles.css';
import NullPlaylistImage from '../../Imagens/NullPlaylistImage.png';
import { ApresentacaoDoElemento } from '../../Componentes/ApresentacaoDoElemento';
import { Parametro } from '../../Componentes/Parametro';
import { TratamentoDeErro } from '../../Componentes/TratamentoDeErro';

export function MusicaDaPlaylist(){
  const [playlistId, songId] = window.location.href.split("_").pop().split("/");

  const [songName, setSongName] = useState("");
  const [albumImage, setAlbumImage] = useState("");

  function RecebeParametros(){
        const recebeInformacaoDaMusica = () => {
          axios.get(`http://localhost:8080/buscar-por-id/obter-musicas?idsDasMusicas=${[songId]}`).then(res => {
            setSongName(res.data[0].name);
            if(res.data[0].album == null || res.data[0].album.images.length != 0) setAlbumImage(res.data[0].album.images[0].url);
            else setAlbumImage(NullPlaylistImage);
          }).catch(err => {
             TratamentoDeErro(err);
            });
        }

        const [parameters, setParameters] = useState([]);

        const recebeParametrosDaMusica = () => {
            axios.get(`http://localhost:8080/parametros/obter-parametros-das-musicas?idsDasMusicas=${[songId]}`)
            .then(res => {
                setParameters(res.data[0]);
            }).catch(err => {
              TratamentoDeErro(err);
            });
        }


        useEffect(() => {
          recebeInformacaoDaMusica();
          recebeParametrosDaMusica();
        }, []);

        return(
          <div className="list">
             <nav className="navScroll">
                  <Parametro name="Acousticness" value={parameters.acousticness}/>
                  <Parametro name="Danceability" value={parameters.danceability}/>
                  <Parametro name="DurationMs" value={parameters.durationMs}/>
                  <Parametro name="Energy" value={parameters.energy}/>
                  <Parametro name="Instrumentalness" value={parameters.instrumentalness}/>
                  <Parametro name="Key" value={parameters.key}/>
                  <Parametro name="Liveness" value={parameters.liveness}/>
                  <Parametro name="Loudness" value={parameters.loudness}/>
                  <Parametro name="Mode" value={parameters.mode}/>
                  <Parametro name="Speechiness" value={parameters.speechiness}/>
                  <Parametro name="TimeSignature" value={parameters.timeSignature}/>
                  <Parametro name="Tempo" value={parameters.tempo}/>
                  <Parametro name="Valence" value={parameters.valence}/>
            </nav>
          </div>
        )
      }
      

    return(
     <div>
        <ApresentacaoDoElemento
           elementImage={albumImage}
           elementName={songName}
           goBack={`/listplaylists/playlist_${playlistId}`}
        />
               <RecebeParametros/>
      </div>
    );
}