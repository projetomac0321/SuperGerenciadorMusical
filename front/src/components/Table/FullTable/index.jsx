import React from 'react';
import { SongsMapped } from '../SongsMapped';
import { ParametersMapped } from '../ParametersMapped';

export function FullTable({parameters, playlistSongs, parameterName}){
    return(
        <div className="tableNav"> 
                            <div className="tableHeader">
                                <h1 className="tableTitle">Músicas em ordem crescente (o último elemento apresenta o maior valor)</h1>
                            </div> 
              <hr className="divider"/>
              <nav className="tabela navScrollSmall">
                  <SongsMapped playlistSongs={playlistSongs}/>
              </nav>
          </div>
    )
}