import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { FullTable } from '../../../components/Table/FullTable';

export function Instrumentalness(){
    const [playlistId, trash] = window.location.href.split("_").pop().split("/");

    const [songsIds, setSongsIds] = useState([]);
  function GetSongs(){
        const [playlistSongs, setPlaylistSongs] = useState([]);

        const getPlaylistInfo = () => {
          axios.get(`http://localhost:8080/buscar-musicas/obter-playlist?idDaPlaylist=${playlistId}`).then(res => {
            let length = (res.data.tracks.total <= 50 ? res.data.tracks.total : 50);
            for(var i = 0; i < length; i += 1)
              {
                songsIds[i] = res.data.tracks.items[i].track.id;
              }
              setSongsIds(songsIds);
          }).catch(err => console.log(err.message));
            setTimeout(function(){
              getParameters(songsIds)}, 1200);
        }

        const [parameters, setParameters] = useState([]);

        const getParameters = (songsIds) => {
            axios.get(`http://localhost:8080/buscar-musicas/obter-parametros-das-musicas?idsDasMusicas=${songsIds}`)
            .then(res => {
                let length = (res.data.length <= 50 ? res.data.length : 50);
                for(var i = 0; i < length; i += 1)
                {
                  parameters[i] = res.data[i].instrumentalness;
                }
                setParameters(parameters);
            }).catch(err => console.log(err.message));
            setTimeout(function(){
              getSorted(songsIds, parameters)}, 1200);
          }

        const getSorted = (songsIds, parameters) => {
          axios.get(`http://localhost:8080/tabela-da-playlist/ordenar-por-parametro?parametro=${parameters}&ids=${songsIds}`)
          .then(res => {
              setPlaylistSongs(res.data);
            let length = (res.data.length <= 50 ? res.data.length : 50);
            for(var i = 0; i < length; i += 1)
              {
                songsIds[i] = res.data[i].id;
              }
              setSongsIds(songsIds);
          }).catch(err => console.log(err.message));
          setTimeout(function(){
            getParameters2(songsIds)}, 1200);
        }

        const getParameters2 = (songsIds) => {
          axios.get(`http://localhost:8080/buscar-musicas/obter-parametros-das-musicas?idsDasMusicas=${songsIds}`)
          .then(res => {
              let length = (res.data.length <= 50 ? res.data.length : 50);
              for(var i = 0; i < length; i += 1)
              {
                parameters[i] = res.data[i].instrumentalness;
              }
              setParameters(parameters);
          }).catch(err => console.log(err.message));
        }

        useEffect(() => {
          getPlaylistInfo();
        }, []);

        return(
          <FullTable
             parameterName="Instrumentalness"
             parameters={parameters}
             playlistSongs={playlistSongs}
          />
        )
      }

    return(
      <GetSongs/>
    );
}