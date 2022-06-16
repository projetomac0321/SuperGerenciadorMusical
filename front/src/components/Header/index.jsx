import React, { useState, useEffect } from 'react';
import './styles.css';
import axios from 'axios';


export function Header(){
    const [searchInput, setSearchInput] = useState("");
    const [isActive, setActive] = useState(false);
    const toggleActive = () => {
        setActive(!isActive);
    };
    
    const [songs, setSongs] = useState([]); 
    
        const fetchSearchData = () => {
            axios.get(`http://localhost:8080/buscar-musicas/query-de-procura?tagDeProcura=${searchInput}&offset=0`).then(res => {
                setSongs(res.data);
              }).catch(err => console.log(err.message));
        }

    return(
       <div className={isActive ? "header show" : "header search"}>
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

            <div className="buttonSearch">
                <button onClick={fetchSearchData}>
                    <FiSearch className="icon"/>
                </button>
            </div>
            <div className="headerRight">
                <FiSearch className="icon" onClick={toggleActive}/>
            </div>
       </div>
    )
}