import axios from 'axios';
import React, { useState } from 'react';
import './styles.css';
import { TratamentoDeErro } from '../../Componentes/TratamentoDeErro';

export function BuscaNasPlaylists(){
    const [array, setArray] = useState(["-1.0", "1.1", "-1.0", "2.0", "-0.2", "1200000", "-1.0", "1.1", "-1.0", "1.1", "-1.1", "11.1", "-1.0", "1.1", "-60.1", "-1", "-1", "1.2", "-1.0", "1.1", "-1", "10000.1", "-0.5", "7.5", "-1.0", "1.2"]);
    const [searchValue, setSearchValue] = useState("");

    function gerenciaBusca(){
        axios.get(`http://localhost:8080/filtragem/musicas-na-playlist?tagDeProcura=${searchValue}&valoresMaxMinPorFiltro=${array}&indicesDosFiltros=1,2,3,4,5,6,7,8,9,10,11,12,13`)
        .then(res => {
           console.log(res.data);
        })
        .catch(err =>
            {
                TratamentoDeErro(err);
            }
        );
    }

    return(
        <div className="inBlock">
                        <input className="stringInput" placeholder="Parâmetro de busca" type="text" onChange={(e) => {setSearchValue(e.target.value)}} />
                        <button className="buttonSearchInPlaylists" onClick={e => {e.preventDefault(); gerenciaBusca()}}>
                                <div className="busca">
                                            <h1>Buscar</h1>
                                </div>
                        </button>
    <nav className="navScrollSmall containerSearch">
                        <h1>Acústica </h1>
                        <hr/>
                    <div className="inLine">
                        <input className="maxOrMinInput" placeholder="Mínimo" type="text" maxLength={5} onChange={(e) => {array[1] = e.target.value; setArray(array)}} />
                        <input className="maxOrMinInput" placeholder="Máximo" type="text" maxLength={5} onChange={(e) => {array[0] = e.target.value; setArray(array)}} />               
                    </div>
                        <h1>Danceabilidade: </h1>
                        <hr/>
                    <div className="inLine">
                        <input className="maxOrMinInput" placeholder="Mínimo" type="text" maxLength={5} onChange={(e) => {array[3] = e.target.value; setArray(array)}} />
                        <input className="maxOrMinInput" placeholder="Máximo" type="text" maxLength={5} onChange={(e) => {array[2] = e.target.value; setArray(array)}} />
                    </div>
                        <h1>DuraçãoMs: </h1>
                        <hr/>
                    <div className="inLine">
                        <input className="maxOrMinInput" placeholder="Mínimo" type="text" maxLength={5} onChange={(e) => {array[5] = e.target.value; setArray(array)}} />
                        <input className="maxOrMinInput" placeholder="Máximo" type="text" maxLength={5} onChange={(e) => {array[4] = e.target.value; setArray(array)}} />
                    </div>
                        <h1>Energia: </h1>
                        <hr/>
                    <div className="inLine">
                        <input className="maxOrMinInput" placeholder="Mínimo" type="text" maxLength={5} onChange={(e) => {array[7] = e.target.value; setArray(array)}} />
                        <input className="maxOrMinInput" placeholder="Máximo" type="text" maxLength={5} onChange={(e) => {array[6] = e.target.value; setArray(array)}} />
                    </div>
                        <h1>Instrumentalidade: </h1>
                        <hr/>
                    <div className="inLine">
                        <input className="maxOrMinInput" placeholder="Mínimo" type="text" maxLength={5} onChange={(e) => {array[9] = e.target.value; setArray(array)}} />
                        <input className="maxOrMinInput" placeholder="Máximo" type="text" maxLength={5} onChange={(e) => {array[8] = e.target.value; setArray(array)}} />
                    </div>
                        <h1>Tonalidade: </h1>
                        <hr/>
                    <div className="inLine">
                        <input className="maxOrMinInput" placeholder="Mínimo" type="text" maxLength={5} onChange={(e) => {array[11] = e.target.value; setArray(array)}} />
                        <input className="maxOrMinInput" placeholder="Máximo" type="text" maxLength={5} onChange={(e) => {array[10] = e.target.value; setArray(array)}} />
                    </div>
                        <h1>Ao Vivo: </h1>
                        <hr/>
                    <div className="inLine">
                        <input className="maxOrMinInput" placeholder="Mínimo" type="text" maxLength={5} onChange={(e) => {array[13] = e.target.value; setArray(array)}} />
                        <input className="maxOrMinInput" placeholder="Máximo" type="text" maxLength={5} onChange={(e) => {array[12] = e.target.value; setArray(array)}} />
                    </div>
                        <h1>Volume: </h1>
                        <hr/>
                    <div className="inLine">
                        <input className="maxOrMinInput" placeholder="Mínimo" type="text" maxLength={5} onChange={(e) => {array[15] = e.target.value; setArray(array)}} />
                        <input className="maxOrMinInput" placeholder="Máximo" type="text" maxLength={5} onChange={(e) => {array[14] = e.target.value; setArray(array)}} />
                    </div>
                        <h1>Modo: </h1>
                        <hr/>
                    <div className="inLine">
                        <input className="maxOrMinInput" placeholder="Mínimo" type="text" maxLength={5} onChange={(e) => {array[17] = e.target.value; setArray(array)}} />
                        <input className="maxOrMinInput" placeholder="Máximo" type="text" maxLength={5} onChange={(e) => {array[16] = e.target.value; setArray(array)}} />
                    </div>
                        <h1>Fala: </h1>
                        <hr/>
                    <div className="inLine">
                        <input className="maxOrMinInput" placeholder="Mínimo" type="text" maxLength={5} onChange={(e) => {array[19] = e.target.value; setArray(array)}} />
                        <input className="maxOrMinInput" placeholder="Máximo" type="text" maxLength={5} onChange={(e) => {array[18] = e.target.value; setArray(array)}} />
                    </div>
                        <h1>Tempo: </h1>
                        <hr/>
                    <div className="inLine">
                        <input className="maxOrMinInput" placeholder="Mínimo" type="text" maxLength={5} onChange={(e) => {array[21] = e.target.value; setArray(array)}} />
                        <input className="maxOrMinInput" placeholder="Máximo" type="text" maxLength={5} onChange={(e) => {array[20] = e.target.value; setArray(array)}} />
                    </div>
                        <h1>Compasso: </h1>
                        <hr/>
                    <div className="inLine">
                        <input className="maxOrMinInput" placeholder="Mínimo" type="text" maxLength={5} onChange={(e) => {array[23] = e.target.value; setArray(array)}} />
                        <input className="maxOrMinInput" placeholder="Máximo" type="text" maxLength={5} onChange={(e) => {array[22] = e.target.value; setArray(array)}} />
                    </div>
                        <h1>Valência: </h1>
                        <hr/>
                    <div className="inLine">
                        <input className="maxOrMinInput" placeholder="Mínimo" type="text" maxLength={5} onChange={(e) => {array[25] = e.target.value; setArray(array)}} />
                        <input className="maxOrMinInput" placeholder="Máximo" type="text" maxLength={5} onChange={(e) => {array[24] = e.target.value; setArray(array)}} />
                    </div>

    </nav>
    </div>
    );
}