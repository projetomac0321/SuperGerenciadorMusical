import React, {useState} from 'react';
import './styles.css';
import { FiSearch } from 'react-icons/fi';
import axios from 'axios';
import { TratamentoDeErro } from '../TratamentoDeErro';

export function BuscarDados({ getRoute, getParamName, navRoute, text, hasImage, nullImage, placeholder}){
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
              axios.get(`http://localhost:8080/buscar-por-tag/${getRoute}?${getParamName}=${searchInput}&offset=${offset}`).then(res => {
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
        <div className="searchData">
            <div className="searchDataHeader">
                            <div className="headerInput">
                                <input
                                id="searchInput"
                                placeholder={placeholder}
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
                            <h1>{text}</h1>
                          </div>
                      </div>
                </nav>
        </div>
    );
}