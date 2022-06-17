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
import { InitialPage } from './pages/InitialPage';
import { SelectPlaylist } from './pages/SelectPlaylist';
import { SearchAlbums } from './pages/SearchAlbums';
import { SearchPlaylists } from './pages/SearchPlaylists';
import { SearchSongs } from './pages/SearchSongs';
import { Album } from './pages/Album';
import { PublicPlaylist } from './pages/PublicPlaylist';
import { SearchArtists } from './pages/SearchArtists';
import { Artist } from './pages/Artist';
import { Song } from './pages/Song';

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
