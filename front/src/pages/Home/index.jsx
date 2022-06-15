import React, {useState} from 'react';
import './styles.css';
import { FiSearch, FiX } from 'react-icons/fi';
import axios from 'axios';
import { SongRow } from '../../components/SongRow';

export function Home(){
    const [searchInput, setSearchInput] = useState("");
    const [isActive, setActive] = useState(false);
    const toggleActive = () => {
        setActive(!isActive);
    };
    
    // const [songs, setSongs] = useState([]); 
    // const [searching, setSearching] = useState(false);

    // function handleSearch(bool){
    //     setSearching(bool);
    // }

    // const [offset, setOffset] = useState(0);
    
    //     const fetchSearchData = () => {
    //       if(searchInput != "")
    //       {
    //           axios.get(`http://localhost:8080/buscar-musicas/buscar-por-query?query=${searchInput}&offset=${offset}`).then(res => {
    //               setSongs(res.data);
    //             }).catch(err => console.log(err.message));
    //           setOffset(offset + 50);
    //           handleSearch(true);  
    //       }
    //       else handleSearch(false);
    //     }

    const [albums, setAlbums] = useState([]); 
    const [searching, setSearching] = useState(false);

    function handleSearch(bool){
        setSearching(bool);
    }

    const [offset, setOffset] = useState(0);
    
        const fetchSearchAlbumData = () => {
          if(searchInput != "")
          {
              axios.get(`http://localhost:8080/buscar-musicas/buscar-album?tituloAlbum=${searchInput}&offset=${offset}`).then(res => {
                  setAlbums(res.data);
                }).catch(err => console.log(err.message));
            //   setOffset(offset + 50);
            handleSearch(true);  
          }
          else handleSearch(false);
        }

       
    return(
        <div className="home">
            <div className="bodyHeader">
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
                                <button onClick={fetchSearchAlbumData}>
                                    <FiSearch className="icon"/>
                                </button>
                            </div>
                            <div className="headerRight">
                                <FiSearch className="icon" onClick={toggleActive}/>
                            </div>
                            </div>

                </div>
                <nav className={searching ? "songsSearch" : "hide songsSearch"}>
                        {albums.map((album) => ( 
                            <div className="songRowSearch">
                                        <SongRow
                                           songName={album.name}
                                        />
                            </div>
                        ))}    
                    <div className="playlistCreate">
                          <div className="playlistCreateText" onClick={fetchSearchAlbumData}>
                            <h1>Buscar mais álbuns</h1>
                          </div>
                      </div>
                </nav>
        </div>
    );
}