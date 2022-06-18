import React from 'react';
import { SongsMapped } from '../SongsMapped';
import { ParametersMapped } from '../ParametersMapped';

export function FullTable({songsIds, playlistSongs, parameterName, title}){
    return(
        <div className="tableNav"> 
              <hr className="divider"/>
              <nav className="tabela navScrollSmall">
                  <ParametersMapped songsIds={songsIds} parameterName={parameterName} title={title}/>
                  <SongsMapped playlistSongs={playlistSongs}/>
              </nav>
          </div>
    )
}