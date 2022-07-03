import React, { useState } from 'react';
import axios from 'axios';
import { FiSearch } from 'react-icons/fi';
import { TratamentoDeErro } from '../TratamentoDeErro';

export function BuscarMusicasFiltradas({indices, valoresMaxMinPorFiltro}){
    const [searchInput, setSearchInput] = useState("");
    
    const [data, setData] = useState([]); 
    const [searching, setSearching] = useState(false);

    function gerenciaPesquisa(bool){
        setSearching(bool);
    }

    const [offset, setOffset] = useState(0);
    
        const buscaDadosDePesquisa = () => {
          if(searchInput != "")
          {
              axios.get(`http://localhost:8080/filtragem/musicas?tagDeProcura=${searchInput}&offset_min=${offset}&offset_max=${offset + 50}&indicesDosFiltros=${indices}&valoresMaxMinPorFiltro=${valoresMaxMinPorFiltro}`).then(res => {
                  console.log(res.data);
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
                                                { hasImage? 
                                                <img className="elementImage" src={
                                                    result.images[0] != null ? result.images[0].url : nullImage
                                                } alt="" /> : null}
                                                <div className="elementRowTextInteract" onClick={e => { e.preventDefault(); setTimeout(function(){
                                            window.location.href = `http://localhost:3000/${navRoute}_${result.id}`;}, 100);
                                            }}>
                                                    <h1>{result.name.substring(0,37)}
                                                       {result.name.length > 37 ? "..." : null}
                                                    </h1>
                                                </div>
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
        </div>
    );
}