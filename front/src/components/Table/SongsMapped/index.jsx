import React from 'react';

export function SongsMapped({ playlistSongs }){
    return (
            <div className="columnSongs">
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