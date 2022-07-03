import React, {useState} from 'react';
import './styles.css';
import { FiSearch, FiPlus } from 'react-icons/fi';
import axios from 'axios';
import { Outlet, NavLink } from 'react-router-dom';
import { TratamentoDeErro } from '../../Componentes/TratamentoDeErro';

export function BuscarMusicas(){
    const [searchInput, setSearchInput] = useState("");
    
    const [data, setData] = useState([]); 
    const [searching, setSearching] = useState(false);

    function gerenciaPesquisa(bool){
        setSearching(bool);
    }

    const [offset, setOffset] = useState(0);
    
        const buscarDadosDaPesquisa = () => {
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
                    TratamentoDeErro(err);
                  });
              setOffset(offset + 50);
              gerenciaPesquisa(true);  
          }
          else 
          {
            gerenciaPesquisa(false);
          }
        }

    return(
        <div className="home">
            <div className="bodyHeader">
                                <div className="filter">
                                    <h1> Filtros: </h1>
                                      <NavLink className="navLink filterButton"
                                                             to={"/searchsongs/tonalidade"}>
                                         <h2>Tonalidade</h2>
                                       </NavLink>
                                       <NavLink className="navLink filterButton"
                                                             to={"/searchsongs/modo"}>
                                         <h2>Modo</h2>
                                       </NavLink>
                                       <NavLink className="navLink filterButton"
                                                             to={"/searchsongs/compasso"}>
                                         <h2>Compasso</h2>
                                       </NavLink>
                                </div>
                            <div className="header">
                            <div className="headerMid">
                                <input
                                id="searchInput"
                                placeholder="Busque uma música"
                                type="text"
                                onChange={(e) => {setSearchInput(e.target.value); setOffset(0)}}
                                />
                            </div>

                                <div className="buttonSearch">
                                    <button onClick={buscarDadosDaPesquisa}>
                                        <FiSearch className="icon"/>
                                    </button>
                                </div>

                            </div>
                </div>
                <nav className={searching ? "elementsSearch" : "hide elementsSearch"}>
                        {data.map((result) => ( 
                            <div className="elementRowSearch" key={result.id}>
                                        <div className="elementRow">
                                            <div className="elementInfo">
                                                <div className="elementRowTextInteract">
                                                    <NavLink className="navLink"
                                                             to={`/song_${result.id}`}>
                                                    <h1>{result.name.substring(0,37)}
                                                       {result.name.length > 37 ? "..." : null}
                                                    </h1>
                                                             </NavLink>
                                                </div>
                                            </div>
                                            <div className="plus">
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
                          <div className="elementCreateText" onClick={buscarDadosDaPesquisa}>
                            <h1>Buscar mais músicas</h1>
                          </div>
                      </div>
                </nav>    
                    <Outlet/>
        </div>
    );
}