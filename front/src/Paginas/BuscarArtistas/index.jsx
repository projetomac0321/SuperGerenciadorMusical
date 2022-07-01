import React from 'react';
import NullArtistImage from '../../Imagens/NullArtistImage.png';
import { BuscarDados } from '../../Componentes/BuscarDados';

export function BuscarArtistas(){
    return(
        <div>
            <BuscarDados
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