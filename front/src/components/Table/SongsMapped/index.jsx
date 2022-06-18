import React from 'react';

export function SongsMapped({ playlistSongs }){
    return (
            <div className="columnSongs">
                <div className="tableHeader">
                                <h1 className="tableTitle">Músicas</h1>
                            </div> 
                {playlistSongs.map((song) => {
                    return (
                    <div className="songRow" key={song.id}>
                                <div className="songRowText">
                                    <h1 className="tableElementText">{song.name}</h1>
                                </div>
                    </div>
                    )
                }
                )}
            </div>
    )
}