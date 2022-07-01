import React from 'react';

export function InformacoesDaMusica({length, releaseDate, durationMs}){
   return(
      <div className="inLine">
        {length > 3 ? <h3 className="elementInLine"> ... </h3> : null}
                                    <h3> - </h3>
                                {releaseDate != null ?
                                <div className="inLine"> 
                                    <h3 className="elementInLine"> {releaseDate.split("-")[0]}</h3>
                                    <h3> - </h3>
                                </div>    
                                     : null }
                                    <h3 className="elementInLine"> {(Math.floor(durationMs /60000)).toFixed(0)}:
                                                                  {((durationMs / 1000) % 60).toFixed(0) < 10 ? 0 : null} 
                                                                  {((durationMs / 1000) % 60).toFixed(0)}</h3>
      </div>
   );
}