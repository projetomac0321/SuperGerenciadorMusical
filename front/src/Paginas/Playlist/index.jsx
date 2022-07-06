import React from 'react';
import { BuscaDadosDaPlaylist } from '../../Componentes/BuscaDadosDaPlaylist';

export function Playlist(){
  return(
     <BuscaDadosDaPlaylist
          songRoute="/listplaylists"
          isOwner={true}
     />
    );
}