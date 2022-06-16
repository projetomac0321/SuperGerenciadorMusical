import React, {useState} from 'react';
import './styles.css';
import { FiSearch, FiPlus } from 'react-icons/fi';
import axios from 'axios';
import { Outlet, NavLink } from 'react-router-dom';

export function SearchSongs(){
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
              axios.get(`http://localhost:8080/buscar-musicas/buscar-por-query?query=${searchInput}&offset=${offset}`).then(res => {
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
                                placeholder="Busque uma música"
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
                                            <div className="plus">
                                            <div className="divider">
                                            </div>
                                            <NavLink
                                                    className="navLink"
                                                    to={`/selectplaylist_${result.uri}`}
                                                >
                                                    <FiPlus className="plusIcon"/>
                                                </NavLink>
                                            </div>
                                        </div>
                            </div>
                        ))}    
                    <div className="playlistCreate">
                          <div className="playlistCreateText" onClick={fetchSearchData}>
                            <h1>Buscar mais músicas</h1>
                          </div>
                      </div>
                </nav>
                        <Outlet/>
        </div>
    );
}