import React from 'react';

export function MapearMusicas({ playlistSongs }){
    return (
            <div className="columnSongs">
                <div className="tableHeader">
                                <h1 className="tableTitle">Músicas</h1>
                            </div> 
                {playlistSongs.map((song) => {
                    return (
                    <div className="elementRow" key={song.id}>
                                <div className="elementRowText">
                                    <h1 className="tableElementText">{song.name.substring(0, 37)} 
                                       {song.name.length > 37 ? "..." : null}
                                    </h1>
                                </div>
                    </div>
                    )
                }
                )}
            </div>
    )
}