import React from 'react';
import ReactDOM from 'react-dom/client';
import { BrowserRouter,
         Routes,
         Route } from 'react-router-dom';
import './styles/global.css';

import { App } from './App';
import { Home } from './pages/Home';
import { ListPlaylists } from './pages/ListPlaylists';
import { Playlist } from './pages/Playlist';
import { CreatePlaylists } from './pages/CreatePlaylists';
import { Song } from './pages/Song';
import { InitialPage } from './pages/InitialPage';

ReactDOM.createRoot(document.getElementById('root')).render(
  <React.StrictMode>
    <BrowserRouter>
    <Routes>
      <Route path="/login" element={<InitialPage />}/>
      <Route path="/" element={<App />}>
          <Route path="home" element={<Home />}/>
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
