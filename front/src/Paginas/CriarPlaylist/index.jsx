import React, { useState } from 'react';
import './styles.css';
import axios from 'axios';
import { FiPlus } from 'react-icons/fi';
import { TratamentoDeErro } from '../../Componentes/TratamentoDeErro';

export function CriarPlaylist(){
    const [playlist, setPlaylist] = useState("");
    const [checkPrivacy, setCheckPrivacy] = useState(true);

    function gerenciaCheckPublic() {
        if(checkPrivacy)
         setCheckPrivacy(!checkPrivacy); 
    }

    function gerenciaCheckNotPublic() {
        if(!checkPrivacy)
         setCheckPrivacy(!checkPrivacy);
    }

    const [checkColaboration, setCheckColaboration] = useState(true);

    function gerenciaCheckNotCollaborative() {
        if(!checkColaboration)
         setCheckColaboration(!checkColaboration); 
    }

    function gerenciaCheckCollaborative() {
        if(checkColaboration)
         setCheckColaboration(!checkColaboration);
    }

    function criaPlaylist(){
        axios.post("http://localhost:8080/playlists/criar", {
            name: playlist,
            collaborative: !checkColaboration,
            publicAccess: !checkPrivacy,
            description: "",
          }).catch(err => {
            TratamentoDeErro(err);
          });
        setTimeout(function(){
            window.location.href = "http://localhost:3000/listplaylists";}, 1200);
           
        }

    return(
        <div className="create">
                <div className="title">
                    <strong> Nome da playlist </strong>
                </div>
                <div className="form">
                    <input
                        id="nameInput"
                        placeholder="Busque uma música/playlist"
                        type="text"
                        onChange={(e) => setPlaylist(e.target.value)}
                        />
                </div>

                <div className="title">
                    <strong> Deseja que a playlist seja colaborativa? </strong>
                    <div className="options">
                        <input onChange={gerenciaCheckCollaborative} checked={!checkColaboration} type="checkbox"/>
                        <h3> Sim </h3>
                        <input onChange={gerenciaCheckNotCollaborative} checked={checkColaboration} type="checkbox"/>
                        <h3> Não </h3>
                    </div>
                </div>

                <div className="title">
                    <strong> A playlist será: </strong>
                    <div className="options">
                        <input onChange={gerenciaCheckPublic} checked={!checkPrivacy} type="checkbox"/>
                        <h3> Pública </h3>
                        <input onChange={gerenciaCheckNotPublic} checked={checkPrivacy} type="checkbox"/>
                        <h3> Privada </h3>
                    </div>
                </div>

                        <button onClick={criaPlaylist}>
                    <div className="createPlaylist">
                            <div className="createPlaylistText">
                                <h1>Criar Playlist</h1>
                            </div>
                                    <FiPlus className="plus"/>
                    </div>
                        </button>
        </div>
    );
}