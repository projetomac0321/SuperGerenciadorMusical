import React, { useState } from 'react';
import axios from 'axios';
import { FiSearch } from 'react-icons/fi';
import { TratamentoDeErro } from '../TratamentoDeErro';
import { NavLink, Outlet } from 'react-router-dom';

export function BuscarMusicasFiltradas({indices, name, min, max}){
    const [searchInput, setSearchInput] = useState("");
    const [minimo, setMinimo] = useState(min - 0.2);
    const [maximo, setMaximo] = useState(max + 0.2);
    
    const [data, setData] = useState([]); 
    const [searching, setSearching] = useState(false);

    function gerenciaPesquisa(bool){
        setSearching(bool);
    }

    const [offset, setOffset] = useState(0);
    
        const buscaDadosDePesquisa = () => {
          if((minimo < min - 0.2 || maximo > max + 0.2) || ((minimo + 0.2) != Math.floor(minimo + 0.2))) 
          {
              alert("Valor de filtragem inválido.");
              window.location.href = "http://localhost:3000/";
          }

          {(searchInput != "") ?
              [axios.get(`http://localhost:8080/filtragem/musicas?tagDeProcura=${searchInput}&offset=${offset}&indicesDosFiltros=${indices}&valoresMinMaxPorFiltro=${minimo},${maximo}`).then(res => {
                  if(res.data[0] != null) setData(res.data);
                  if(res.data.length == 0 || res.data[0] == null) {
                    {offset != 0 ? alert("Nenhum novo elemento encontrado. Todos os elementos foram passados para esse parâmetro de busca.")
                    : alert("Nenhum elemento encontrado. Por favor tente novamente com outro parâmetro de busca.");
                    window.location.href = "http://localhost:3000/";}
                }
                }).catch(err => {
                    TratamentoDeErro(err);
                  }),
              setOffset(offset + 100),
              gerenciaPesquisa(true)]
          : 
            gerenciaPesquisa(false);}
        }

    return(
        <div className="inBlock">
            <div className="inLine centralizeFilters">
                <h1> {name}: </h1>
                <input className="maxOrMinInput" placeholder="Valor" type="number" maxLength={5} onChange={(e) => {e.preventDefault(); setMinimo(parseFloat(e.target.value) - 0.2); setMaximo(parseFloat(e.target.value) + 0.2); setOffset(0)}} />
            </div> 
        <div className="searchData">
            <div className="searchDataHeader">
                            <div className="headerInput">
                                <input
                                id="searchInput"
                                placeholder="Busque uma música"
                                type="text"
                                onChange={(e) => {setSearchInput(e.target.value); setOffset(0)}}
                                />
                                
                            </div>

                            <div className="buttonSearch">
                                <button onClick={buscaDadosDePesquisa}>
                                    <FiSearch className="icon"/>
                                </button>
                            </div>
                </div>
                <nav className={searching ? "elementsSearch" : "hide elementsSearch"}>
                        {data.map((result) => ( 
                            <div className="elementRowSearch" key={result.id}>
                                        <div className="elementRow">
                                            <div className="elementInfo">
                                                <NavLink className="elementRowTextInteract" to={`/song_${result.id}`}>
                                                    <h1>{result.name.substring(0,37)}
                                                       {result.name.length > 37 ? "..." : null}
                                                    </h1>
                                                </NavLink>
                                            </div>
                                        </div>
                            </div>
                        ))}    
                    <div className="elementCreate">
                          <div className="elementCreateText" onClick={buscaDadosDePesquisa}>
                            <h1>Buscar mais músicas</h1>
                          </div>
                      </div>
                </nav>
                 <Outlet/>
        </div>
        </div>
    );
}