import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { TabelaCompleta } from '../../../Componentes/Tabela/TabelaCompleta';
import { TratamentoDeErro } from '../../TratamentoDeErro';

export function Buscar({ parameterName, title }){
    const playlistId = window.location.href.split("_").pop().split("/")[0];

    const [songsIds, setSongsIds] = useState([]);
  function ReceberMusicas(){
        const [playlistSongs, setPlaylistSongs] = useState([]);
        const [parameters, setParameters] = useState([]);

        const receberInformacoesDaPlaylist = () => {
          axios.get(`http://localhost:8080/buscar-por-id/obter-playlist?idDaPlaylist=${playlistId}`).then(res => {
            let length = (res.data.tracks.total <= 50 ? res.data.tracks.total : 50);
            for(var i = 0; i < length; i += 1)
              {
                songsIds[i] = res.data.tracks.items[i].track.id;
              }
              setSongsIds(songsIds);
              axios.get(`http://localhost:8080/parametros/obter-parametros-das-musicas?idsDasMusicas=${songsIds}`)
              .then(res => {
                  let length = (res.data.length <= 50 ? res.data.length : 50);
                  for(var i = 0; i < length; i += 1)
                  {
                    parameters[i] = res.data[i][parameterName];
                  }
                  setParameters(parameters);
                  axios.get(`http://localhost:8080/parametros/ordenar-por-parametro?parametro=${parameters}&ids=${songsIds}`)
                  .then(res => {
                      setPlaylistSongs(res.data);
                    let length = (res.data.length <= 50 ? res.data.length : 50);
                    for(var i = 0; i < length; i += 1)
                      {
                        songsIds[i] = res.data[i].id;
                      }
                      setSongsIds(songsIds);
                  }).catch(err => {
                    TratamentoDeErro(err);
                  });
              }).catch(err => {
                TratamentoDeErro(err);
              });
          }).catch(err => {
            TratamentoDeErro(err);
          });
        }
         

        useEffect(() => {
          receberInformacoesDaPlaylist();
        }, []);

        return(
          <TabelaCompleta
             parameterName={parameterName}
             title={title}
             songsIds={songsIds}
             playlistSongs={playlistSongs}
          />
        )
      }

    return(
      <ReceberMusicas/>
    );
}