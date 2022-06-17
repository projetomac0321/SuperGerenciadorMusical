import React from 'react';
import NullArtistImage from '../../images/NullArtistImage.png';
import { SearchData } from '../../components/SearchData';

export function SearchArtists(){
    return(
        <div>
            <SearchData
                getRoute="buscar-artista"
                getParamName="tagArtista"
                navRoute="searchartists/artist"
                text="Buscar mais artistas"
                hasImage={true}
                nullImage={NullArtistImage}
                placeholder="Busque um artista"
            />
        </div>
    );
}