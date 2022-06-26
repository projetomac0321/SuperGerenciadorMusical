import React from 'react';

export function MapArtists({ artists }){
    function Artists(){
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
               <Artists/>
            </div>
          );
}