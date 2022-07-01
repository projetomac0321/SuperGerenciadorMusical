import React from 'react';
import { BuscarDados } from '../../Componentes/BuscarDados';
import NullPlaylistImage from '../../Imagens/NullPlaylistImage.png';

export function BuscarAlbums(){
    return(
        <div>
            <BuscarDados
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