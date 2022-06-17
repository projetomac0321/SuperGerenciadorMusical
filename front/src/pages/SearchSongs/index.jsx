import React, {useState} from 'react';
import './styles.css';
import { FiSearch, FiPlus } from 'react-icons/fi';
import axios from 'axios';
import { Outlet, NavLink } from 'react-router-dom';

export function SearchSongs(){
    const [searchInput, setSearchInput] = useState("");
    const [songsIds, setSongsIds] = useState([]);
    
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
                  for(var i = 0; i < 50; i+= 1)
                  {
                      songsIds[i] = (res.data[i].id);
                  };
                  setSongsIds(songsIds);
                }).catch(err => console.log(err.message));
              setOffset(offset + 50);
              handleSearch(true);  
          }
          else 
          {
            handleSearch(false);
            setOffset(0);
            setSongsIds([]);
          }
        }

        const [parameters, setParameters] = useState([]);

        const getParameters = (songsIds) => {
            axios.get(`http://localhost:8080/buscar-musicas/obter-parametros-das-musicas?idsDasMusicas=${songsIds}`)
            .then(res => {
                setParameters(res.data);
            }).catch(err => console.log(err.message));
               parameters.sort(function ordenaPorParametro(a, b){
                  if(a.acousticness > b.acousticness)
                    return -1;
                  if(a.acousticness < b.acousticness)
                    return 1;
                  return 0;
                })
                setParameters(parameters);
               data.sort(function comparaId(a, b){
                  for(var i = 0; i < 50; i += 1)
                  {
                      if(parameters[i].id == a.id)
                        return -1;
                      if(parameters[i].id == b.id)
                        return 1;
                  }
                    return 0;  
               })
               setSongsIds(data);
               data.forEach(function(song, index, arr){
                  parameters.forEach(function(item, indice, array){
                    if(item.id == song.id)
                      song.acousticness = item.acousticness
                  })
               })
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
                                                    <p>{result.acousticness}</p>
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
                    <button onClick={e =>{e.preventDefault(); getParameters(songsIds)}}></button>
                        <Outlet/>
        </div>
    );
}