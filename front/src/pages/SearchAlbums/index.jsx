import React, {useState} from 'react';
import './styles.css';
import { FiSearch } from 'react-icons/fi';
import axios from 'axios';

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
                                onChange={(e) => {setSearchInput(e.target.value); setOffset(0)}}
                                />
                                
                            </div>

                            <div className="buttonSearch">
                                <button onClick={fetchSearchData}>
                                    <FiSearch className="icon"/>
                                </button>
                            </div>
                            </div>

                </div>
                <nav className={searching ? "playlistsSearch" : "hide playlistsSearch"}>
                        {data.map((result) => ( 
                            <div className="playlistRowSearch">
                                        <div className="playlistRow">
                                            <div className="playlistInfo">
                                                <img className="elementImage" src={result.images[0].url} alt="" />
                                                <div className="playlistRowText" onClick={e => { e.preventDefault(); setTimeout(function(){
                                            window.location.href = `http://localhost:3000/searchalbums/album_${result.id}`;}, 100);
                                            }}>
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
        </div>
    );
}