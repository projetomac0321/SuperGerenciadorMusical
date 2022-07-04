import axios from 'axios';
import React, { useState } from 'react';
import './styles.css';
import { TratamentoDeErro } from '../../Componentes/TratamentoDeErro';
import { MapearMusica } from '../../Componentes/MapearMusica';

export function BuscaNasPlaylists(){
    const [array, setArray] = useState(["-1.0", "1.1", "-1.0", "2.0", "-0.2", "1200000", "-1.0", "1.1", "-1.0", "1.1", "-1.1", "11.1", "-1.0", "1.1", "-60.1", "-1", "-1", "1.2", "-1.0", "1.1", "-1", "10000.1", "-0.5", "7.5", "-1.0", "1.2"]);
    const [searchValue, setSearchValue] = useState("");
    const Params = ["Acústica", "Danceabilidade", "DuraçãoMs", "Energia", "Instrumentalidade", "Tonalidade", "Ao Vivo", "Volume", "Modo", "Fala", "Tempo", "Compasso", "Valência"];
    
    function InsereParametros(){
        return(
        Params.map((param) => {
          return(
            <div key={Params.indexOf(param)}>
               <h1>{param}:</h1>
               <hr/>
               <div className="inLine">
                        <input className="maxOrMinInput" placeholder="Mínimo" type="text" maxLength={5} onChange={(e) => {array[2*Params.indexOf(param)] = e.target.value; setArray(array)}} />
                        <input className="maxOrMinInput" placeholder="Máximo" type="text" maxLength={5} onChange={(e) => {array[(2*Params.indexOf(param)) + 1] = e.target.value; setArray(array)}} />               
                </div>
            </div>
          )
         })
        );
    }

    const [data, setData] = useState([]);
    const [searching, setSearching] = useState(false);

    function gerenciaPesquisa(bool){
        setSearching(bool);
    }

    function buscaMusicasFiltradas(){
        axios.get(`http://localhost:8080/filtragem/musicas-na-playlist?tagDeProcura=${searchValue}&valoresMaxMinPorFiltro=${array}&indicesDosFiltros=1,2,3,4,5,6,7,8,9,10,11,12,13`)
        .then(res => {
            if(res.data != "") setData(res.data);
            else alert("Nenhuma música foi encontrada com esses parâmetros.");
        })
        .catch(err =>
            {
                TratamentoDeErro(err);
            }
        );
        gerenciaPesquisa(true);
    }

    return(
        <div className="inBlock">
                        <input className="stringInput" placeholder="Parâmetro de busca" type="text" onChange={(e) => {setSearchValue(e.target.value)}} />
                        <button className="buttonSearchInPlaylists" onClick={e => {e.preventDefault(); setData([]);
                            setTimeout(function(){
                                buscaMusicasFiltradas();}, 1000);}}>
                                <div className="busca">
                                            <h1>Buscar</h1>
                                </div>
                        </button>
    <nav className="navScrollSmall containerSearch">
        <InsereParametros/>
    </nav>
            <nav className={searching ? "elementsSearch" : "hide elementsSearch"}>
                        {data.map((result) => ( 
                            <div className="elementRowSearch" key={result.id}>
                                        <div className="elementRow">
                                            <MapearMusica
                                                name={result.name}
                                                nameLength={result.artists.length}
                                                artistsLength={result.artists.length}
                                                artists={result.artists}
                                                releaseDate={result.album.releaseDate}
                                                durationMs={result.durationMs}
                                            />
                                        </div>
                            </div>
                        ))}    
            </nav>    
    </div>
    );
}