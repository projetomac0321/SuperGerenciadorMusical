import React from 'react';
import './styles.css';
import { FiPlus } from 'react-icons/fi';

export function SongRow({ songName, artist, album, Image }){
    return (
        <div className="songRow">
            <div className="songInfo">
                <img src={Image} alt="" />
                <div className="songRowText">
                    <h1>{songName}</h1>
                    <p>{artist} - {album}</p>
                </div>
            </div>
            <div className="plus">
              <div className="divider">
              </div>
                <FiPlus className="plusIcon"/>
            </div>
        </div>
    )
}
