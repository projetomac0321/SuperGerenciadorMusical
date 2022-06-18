import React from 'react';
import ReactDOM from 'react-dom/client';
import { BrowserRouter,
         Routes,
         Route } from 'react-router-dom';
import './styles/global.css';

import { App } from './App';
import { InitialPage } from './pages/InitialPage';
import { ListPlaylists } from './pages/ListPlaylists';
import { CreatePlaylists } from './pages/CreatePlaylists';
import { SearchSongs } from './pages/SearchSongs';
import { SearchAlbums } from './pages/SearchAlbums';
import { SearchPlaylists } from './pages/SearchPlaylists';
import { SearchArtists } from './pages/SearchArtists';

import { Playlist } from './pages/Playlist';
import { Album } from './pages/Album';
import { PublicPlaylist } from './pages/PublicPlaylist';
import { Artist } from './pages/Artist';
import { SelectPlaylist } from './pages/SelectPlaylist';
import { Song } from './pages/Song';

import { ParameterTable } from './pages/ParameterTable';
import { Acousticness } from './pages/Parameters/Acousticness';
import { Danceability } from './pages/Parameters/Danceability';
import { DurationMs } from './pages/Parameters/DurationMs';
import { Energy } from './pages/Parameters/Energy';
import { Instrumentalness } from './pages/Parameters/Instrumentalness';
import { Key } from './pages/Parameters/Key';
import { Liveness } from './pages/Parameters/Liveness';
import { Loudness } from './pages/Parameters/Loudness';
import { Speechiness } from './pages/Parameters/Speechiness';
import { Tempo } from './pages/Parameters/Tempo';
import { TimeSignature } from './pages/Parameters/TimeSignature';
import { Valence } from './pages/Parameters/Valence';

ReactDOM.createRoot(document.getElementById('root')).render(
  <React.StrictMode>
    <BrowserRouter>
    <Routes>
      <Route path="/login" element={<InitialPage />}/>
      <Route path="/" element={<App />}>
          <Route path="song_:songId" element={<Song />}/>
          <Route path="searchsongs" element={<SearchSongs />}/>
          <Route path="searchalbums" element={<SearchAlbums />}/>
            <Route path="searchalbums/album_:albumId" element={<Album />}/>
          <Route path="searchplaylists" element={<SearchPlaylists />}/>
            <Route path="searchplaylists/playlist_:playlistId" element={<PublicPlaylist />}/>
          <Route path="searchartists" element={<SearchArtists />}/> 
            <Route path="searchartists/artist_:artistId" element={<Artist />}/> 
          <Route path="selectplaylist_:songUri" element={<SelectPlaylist />}/>
          <Route path="listplaylists" element={<ListPlaylists />}/>
            <Route path="listplaylists/playlist_:playlistId" element={<Playlist />}/>
          <Route path="createplaylists" element={<CreatePlaylists />}/>
          <Route path="parametertable/playlist_:playlistId/" element={<ParameterTable />}>
            <Route path="acousticness" element={<Acousticness />}/>
            <Route path="danceability" element={<Danceability />}/>
            <Route path="durationMs" element={<DurationMs />}/>
            <Route path="energy" element={<Energy />}/>
            <Route path="instrumentalness" element={<Instrumentalness />}/>
            <Route path="key" element={<Key />}/>
            <Route path="liveness" element={<Liveness />}/>
            <Route path="loudness" element={<Loudness />}/>
            <Route path="speechiness" element={<Speechiness />}/>
            <Route path="tempo" element={<Tempo />}/>
            <Route path="timeSignature" element={<TimeSignature />}/>
            <Route path="valence" element={<Valence />}/>
          </Route>  
      </Route>
          <Route
            path="*"
            element={
              <main style={{ padding: "1rem" }}>
                <p>There's nothing here!</p>
              </main>
            }
          />

    </Routes>
    </BrowserRouter>
  </React.StrictMode>
)
