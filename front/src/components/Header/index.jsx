import React, { useState } from 'react';
import './styles.css';
import { FiX } from 'react-icons/fi';
import { FiSearch } from 'react-icons/fi';

export function Header(){
    const [isActive, setActive] = useState(false);
    const toggleActive = () => {
         setActive(!isActive);
    };

    const [searchInput, setSearchInput] = useState("");

    return(
       <div className={isActive ? "header show" : "header"}>
            <div className="headerLeft">
                <FiX className="icon" onClick={toggleActive}/>
            </div>

            <div className="headerMid">
                <input
                  id="searchInput"
                  placeholder="Busque uma música/playlist"
                  type="text"
                  onChange={(e) => setSearchInput(e.target.value)}
                />
                
            </div>
            
            <div className="headerRight">
                <FiSearch className="icon" onClick={toggleActive}/>
            </div>
       </div>
    )
}