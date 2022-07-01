import React from 'react';

export function MapearArtistas({ artists }){
    function Artistas(){
        return(
            artists.map((artist) => {
                return(
                <h3 className="elementInLine" key={artist.id}>
                  {artist.name}
                </h3>
            )}
        )
        );
    }    
    
    return(
            <div className="inLine">
               <Artistas/>
            </div>
          );
}