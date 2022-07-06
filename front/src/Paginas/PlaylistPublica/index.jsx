import React from 'react';
import { BuscaDadosDaPlaylist } from '../../Componentes/BuscaDadosDaPlaylist';

export function PlaylistPublica(){
  return(
     <BuscaDadosDaPlaylist
          songRoute="/searchplaylists"
          isOwner={false}
     />
    );
}