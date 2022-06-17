import React from 'react';
import { SearchData } from '../../components/SearchData';
import NullPlaylistImage from '../../images/NullPlaylistImage.png';

export function SearchAlbums(){
    return(
        <div>
            <SearchData
                getRoute="buscar-album"
                getParamName="tagAlbum"
                navRoute="searchalbums/album"
                text="Buscar mais álbuns"
                hasImage={true}
                nullImage={NullPlaylistImage}
                placeholder="Busque um álbum"
            />
        </div>
    );
}