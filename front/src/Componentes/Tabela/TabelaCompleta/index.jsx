import React from 'react';
import { MapearMusicas } from '../MapearMusicas';
import { MapearParametros } from '../MapearParametros';

export function TabelaCompleta({songsIds, playlistSongs, parameterName, title}){
    return(
        <div className="tableNav"> 
              <hr className="divider"/>
              <nav className="tabela navScrollSmall">
                  <MapearParametros songsIds={songsIds} parameterName={parameterName} title={title}/>
                  <MapearMusicas playlistSongs={playlistSongs}/>
              </nav>
          </div>
    )
}