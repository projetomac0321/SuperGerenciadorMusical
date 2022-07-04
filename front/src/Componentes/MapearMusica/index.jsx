import React from 'react';
import { NavLink, Outlet } from 'react-router-dom';
import { MapearArtistas } from '../MapearArtistas';
import { InformacoesDaMusica } from '../InformacoesDaMusica';

export function MapearMusica({link, name, nameLength, artistsLength, artists, releaseDate, durationMs}){
    return(
        <div className="inBlock">
                    {link != null? 
                        <div className="elementRowTextInteract">
                                <NavLink className="navLink"
                                        to={link}>
                                    <h1>{name.substring(0,37)}
                                        {nameLength > 37 ? "..." : null}
                                    </h1>
                                    </NavLink> 
                        </div>
                        : 
                        <h1>{name.substring(0,37)}
                            {nameLength > 37 ? "..." : null}
                        </h1>
                        }
                    <div className="inLine">
                        <MapearArtistas 
                            artists={artistsLength > 3 ? [artists[0], artists[1], artists[2]] 
                                    : artists}
                        />
                        <InformacoesDaMusica
                                length={artistsLength}
                                releaseDate={releaseDate}
                                durationMs={durationMs}
                            />
                    </div>
            <Outlet/>
    </div>
    );
}