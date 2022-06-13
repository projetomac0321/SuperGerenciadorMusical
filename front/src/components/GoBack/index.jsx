import React from 'react';
import { NavLink } from 'react-router-dom';
import './styles.css';
import { FiArrowLeft } from 'react-icons/fi';

export function GoBack({ place }){
    return(
        <div className="returnIcon">
            <NavLink className="linkIcon" to={place}>
            <FiArrowLeft className="arrowLeftIcon"/>
            </NavLink>
        </div>
    );
}