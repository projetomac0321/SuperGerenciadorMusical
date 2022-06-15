import React from 'react';
import ReactDOM from 'react-dom/client';
import { BrowserRouter,
         Routes,
         Route } from 'react-router-dom';
import './styles/global.css';

import { App } from './App';
import { ListPlaylists } from './pages/ListPlaylists';
import { Playlist } from './pages/Playlist';
import { CreatePlaylists } from './pages/CreatePlaylists';
import { Song } from './pages/Song';
import { InitialPage } from './pages/InitialPage';
import { SelectPlaylist } from './pages/SelectPlaylist';
import { SearchAlbums } from './pages/SearchAlbums';
import { SearchPlaylists } from './pages/SearchPlaylists';
import { SearchSongs } from './pages/SearchSongs';

ReactDOM.createRoot(document.getElementById('root')).render(
  <React.StrictMode>
    <BrowserRouter>
    <Routes>
      <Route path="/login" element={<InitialPage />}/>
      <Route path="/" element={<App />}>
          <Route path="searchsongs" element={<SearchSongs />}/>
          <Route path="searchalbums" element={<SearchAlbums />}/>
          <Route path="searchplaylists" element={<SearchPlaylists />}/>
            <Route path="searchsongs/selectplaylist_:songUri" element={<SelectPlaylist />}/>
          <Route path="listplaylists" element={<ListPlaylists />}/>
            <Route path="listplaylists/playlist_:playlistId" element={<Playlist />}/>
              <Route path="listplaylists/playlist_:playlistId/musica_:songId" element={<Song />}/>
          <Route path="createplaylists" element={<CreatePlaylists />}/>
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
