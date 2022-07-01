import React from 'react';
import { BuscarDados } from '../../Componentes/BuscarDados';
import NullPlaylistImage from '../../Imagens/NullPlaylistImage.png';

export function BuscarPlaylists(){
   return(
    <div>
        <BuscarDados
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