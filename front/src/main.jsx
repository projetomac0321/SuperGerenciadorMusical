import React from 'react';
import ReactDOM from 'react-dom/client';
import { BrowserRouter,
         Routes,
         Route } from 'react-router-dom';
import './Estilos/global.css';

import { Pagina } from './Pagina';
import { PaginaInicial } from './Paginas/PaginaInicial';
import { ListarPlaylists } from './Paginas/ListarPlaylists';
import { CriarPlaylist } from './Paginas/CriarPlaylist';
import { BuscarMusicas } from './Paginas/BuscarMusicas';
import { BuscarAlbuns } from './Paginas/BuscarAlbuns';
import { BuscarPlaylists } from './Paginas/BuscarPlaylists';
import { BuscarArtistas } from './Paginas/BuscarArtistas';

import { Playlist } from './Paginas/Playlist';
import { Album } from './Paginas/Album';
import { PlaylistPublica } from './Paginas/PlaylistPublica';
import { Artista } from './Paginas/Artista';
import { SelecionarPlaylist } from './Paginas/SelecionarPlaylist';
import { Musica } from './Paginas/Musica';
import { MusicaDaPlaylist } from './Paginas/MusicaDaPlaylist';

import { TabelaDeParametros } from './Paginas/TabelaDeParametros';
import { Acustica } from './Paginas/Parametros/Acustica';
import { Danceabilidade } from './Paginas/Parametros/Danceabilidade';
import { DuracaoMs } from './Paginas/Parametros/DuracaoMs';
import { Energia } from './Paginas/Parametros/Energia';
import { Instrumentalidade } from './Paginas/Parametros/Instrumentalidade';
import { Tonalidade } from './Paginas/Parametros/Tonalidade';
import { AoVivo } from './Paginas/Parametros/AoVivo';
import { Volume } from './Paginas/Parametros/Volume';
import { Fala } from './Paginas/Parametros/Fala';
import { Tempo } from './Paginas/Parametros/Tempo';
import { Compasso } from './Paginas/Parametros/Compasso';
import { Valencia } from './Paginas/Parametros/Valencia';

import { BuscarPorCompasso } from './Paginas/FiltragemDeBusca/BuscarPorCompasso';
import { BuscarPorModo } from './Paginas/FiltragemDeBusca/BuscarPorModo';
import { BuscarPorTonalidade } from './Paginas/FiltragemDeBusca/BuscarPorTonalidade';

import { BuscaNasPlaylists } from './Paginas/BuscaNasPlaylists';

ReactDOM.createRoot(document.getElementById('root')).render(
  <React.StrictMode>
    <BrowserRouter>
    <Routes>
      <Route path="/login" element={<PaginaInicial />}/>
      <Route path="/" element={<Pagina />}>
          <Route path="song_:songId" element={<Musica />}/>
          <Route path="searchsongs" element={<BuscarMusicas />}/>
          <Route path="searchalbums" element={<BuscarAlbuns />}/>
            <Route path="searchalbums/album_:albumId" element={<Album />}/>
          <Route path="searchplaylists" element={<BuscarPlaylists />}/>
            <Route path="searchplaylists/playlist_:playlistId" element={<PlaylistPublica />}/>
          <Route path="searchartists" element={<BuscarArtistas />}/> 
            <Route path="searchartists/artist_:artistId" element={<Artista />}/> 
          <Route path="selectplaylist_:songUri" element={<SelecionarPlaylist />}/>
          <Route path="listplaylists" element={<ListarPlaylists />}/>
            <Route path="listplaylists/playlist_:playlistId" element={<Playlist />}/>
               <Route path="listplaylists/playlistsong_:playlistId/:songId" element={<MusicaDaPlaylist />}/>
          <Route path="createplaylists" element={<CriarPlaylist />}/>
          <Route path="parametertable/playlist_:playlistId/" element={<TabelaDeParametros />}>
            <Route path="acousticness" element={<Acustica />}/>
            <Route path="danceability" element={<Danceabilidade />}/>
            <Route path="durationMs" element={<DuracaoMs />}/>
            <Route path="energy" element={<Energia />}/>
            <Route path="instrumentalness" element={<Instrumentalidade/>}/>
            <Route path="key" element={<Tonalidade />}/>
            <Route path="liveness" element={<AoVivo/>}/>
            <Route path="loudness" element={<Volume />}/>
            <Route path="speechiness" element={<Fala />}/>
            <Route path="tempo" element={<Tempo />}/>
            <Route path="timeSignature" element={<Compasso />}/>
            <Route path="valence" element={<Valencia />}/>
          </Route> 
          <Route path="searchsongs/compasso" element={<BuscarPorCompasso />}/> 
          <Route path="searchsongs/modo" element={<BuscarPorModo />}/> 
          <Route path="searchsongs/tonalidade" element={<BuscarPorTonalidade />}/> 
          <Route path="buscar-nas-playlists" element={<BuscaNasPlaylists />}/>
      </Route>
          <Route
            path="*"
            element={
              <main style={{ padding: "1rem" }}>
                <p>Erro. Rota indisponível!</p>
              </main>
            }
          />

    </Routes>
    </BrowserRouter>
  </React.StrictMode>
)
