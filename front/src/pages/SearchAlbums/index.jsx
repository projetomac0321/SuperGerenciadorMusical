import React, {useState} from 'react';
import './styles.css';
import { FiSearch, FiPlus } from 'react-icons/fi';
import axios from 'axios';
import { Outlet, NavLink } from 'react-router-dom';

export function SearchAlbums(){
    const [searchInput, setSearchInput] = useState("");
    
    const [data, setData] = useState([]); 
    const [searching, setSearching] = useState(false);

    function handleSearch(bool){
        setSearching(bool);
    }

    const [offset, setOffset] = useState(0);
    
        const fetchSearchData = () => {
          if(searchInput != "")
          {
              axios.get(`http://localhost:8080/buscar-musicas/buscar-album?tagAlbum=${searchInput}&offset=${offset}`).then(res => {
                  setData(res.data);
                }).catch(err => console.log(err.message));
              setOffset(offset + 50);
              handleSearch(true);  
          }
          else 
          {
            handleSearch(false);
            setOffset(0);
          }
        }

    return(
        <div className="home">
            <div className="bodyHeader">
                            <div className="header show">
                            <div className="headerMid">
                                <input
                                id="searchInput"
                                placeholder="Busque um álbum"
                                type="text"
                                onChange={(e) => setSearchInput(e.target.value)}
                                />
                                
                            </div>

                            <div className="buttonSearch">
                                <button onClick={fetchSearchData}>
                                    <FiSearch className="icon"/>
                                </button>
                            </div>
                            </div>

                </div>
                <nav className={searching ? "songsSearch" : "hide songsSearch"}>
                        {data.map((result) => ( 
                            <div className="songRowSearch">
                                        <div className="songRow">
                                            <div className="songInfo">
                                                <div className="songRowText">
                                                    <h1>{result.name}</h1>
                                                </div>
                                            </div>
                                        </div>
                            </div>
                        ))}    
                    <div className="playlistCreate">
                          <div className="playlistCreateText" onClick={fetchSearchData}>
                            <h1>Buscar mais álbuns</h1>
                          </div>
                      </div>
                </nav>
                        <Outlet/>
        </div>
    );
}