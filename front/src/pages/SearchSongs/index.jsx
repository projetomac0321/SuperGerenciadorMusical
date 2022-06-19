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
              axios.get(`http://localhost:8080/buscar-por-tag/buscar-por-query?query=${searchInput}&offset=${offset}`).then(res => {
                  setData(res.data);
                  if(res.data.length == 0) {
                    if(offset != 0) alert("Nenhum novo elemento encontrado. Todos os elementos foram passados para esse parâmetro de busca.");
                    else alert("Nenhum elemento encontrado. Por favor tente novamente com outro parâmetro de busca.");
                    window.location.href = "http://localhost:3000/";
                }
                }).catch(err => {
                    if(err.response) {
                      alert("Tente novamente. Falha na resposta, erro de status code " + err.response.status); 
                      window.location.href = "http://localhost:3000/";}
                    else if(err.request){
                      alert("Tente novamente mais tarde. A requisição foi feita, mas nenhuma resposta foi obtida");
                      window.location.href = "http://localhost:3000/";}
                    else {
                      alert("Tente novamente mais tarde. Erro na configuração da requisição.");
                      window.location.href = "http://localhost:3000/";}
                  });
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
                                placeholder="Busque uma música"
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
                <nav className={searching ? "songsSearch" : "hide songsSearch"}>
                        {data.map((result) => ( 
                            <div className="songRowSearch" key={result.id}>
                                        <div className="songRow">
                                            <div className="songInfo">
                                                <div className="songRowText">
                                                    <NavLink className="navLink"
                                                             to={`/song_${result.id}`}>
                                                    <h1>{result.name}</h1>
                                                             </NavLink>
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
                    <div className="elementCreate">
                          <div className="elementCreateText" onClick={fetchSearchData}>
                            <h1>Buscar mais músicas</h1>
                          </div>
                      </div>
                </nav>
                        <Outlet/>
        </div>
    );
}