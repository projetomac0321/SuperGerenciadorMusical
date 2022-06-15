import React from 'react';
import './styles.css';
import { FiPlus } from 'react-icons/fi';

export function SongRow({ songName }){
    return (
        <div className="songRow">
            <div className="songInfo">
                <div className="songRowText">
                    <h1>{songName}</h1>
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
