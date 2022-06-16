import React from 'react';
import './styles.css';
import { FiPlus } from 'react-icons/fi';
import {Outlet, NavLink} from 'react-router-dom';

export function SongRow({ songName, songUri }){
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
              <NavLink
                    className="navLink"
                    to={`/home/selectplaylist_${songUri}`}
                  >
                    <FiPlus className="plusIcon"/>
                  </NavLink>
            </div>
            <Outlet/>
        </div>
    )
}
