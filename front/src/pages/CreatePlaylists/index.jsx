import React, { useState } from 'react';
import './styles.css';
import axios from 'axios';
import { FiPlus } from 'react-icons/fi';

export function CreatePlaylists(){
    const [playlist, setPlaylist] = useState("");
    const [checkPrivacy, setCheckPrivacy] = useState(true);

    function handleCheckPublic() {
        if(checkPrivacy)
         setCheckPrivacy(!checkPrivacy); 
    }

    function handleCheckNotPublic() {
        if(!checkPrivacy)
         setCheckPrivacy(!checkPrivacy);
    }

    const [checkColaboration, setCheckColaboration] = useState(true);

    function handleCheckNotCollaborative() {
        if(!checkColaboration)
         setCheckColaboration(!checkColaboration); 
    }

    function handleCheckCollaborative() {
        if(checkColaboration)
         setCheckColaboration(!checkColaboration);
    }

    function createPlaylist(){
        axios.post("http://localhost:8080/playlists/criar", {
            name: playlist,
            collaborative: !checkColaboration,
            publicAccess: !checkPrivacy,
            description: "",
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
                        <input onChange={handleCheckCollaborative} checked={!checkColaboration} type="checkbox"/>
                        <h3> Sim </h3>
                        <input onChange={handleCheckNotCollaborative} checked={checkColaboration} type="checkbox"/>
                        <h3> Não </h3>
                    </div>
                </div>

                <div className="title">
                    <strong> A playlist será: </strong>
                    <div className="options">
                        <input onChange={handleCheckPublic} checked={!checkPrivacy} type="checkbox"/>
                        <h3> Pública </h3>
                        <input onChange={handleCheckNotPublic} checked={checkPrivacy} type="checkbox"/>
                        <h3> Privada </h3>
                    </div>
                </div>

                        <button onClick={createPlaylist}>
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