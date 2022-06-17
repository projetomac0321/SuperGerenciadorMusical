import React from 'react';
import { SearchData } from '../../components/SearchData';
import NullPlaylistImage from '../../images/NullPlaylistImage.png';

export function SearchPlaylists(){
   return(
    <div>
        <SearchData
                getRoute="buscar-playlist-public"
                getParamName="tagPlaylist"
                navRoute="searchplaylists/playlist"
                text="Buscar mais playlists"
                hasImage={true}
                nullImage={NullPlaylistImage}
                placeholder="Busque uma playlist pública"
            />
    </div>
   )
}