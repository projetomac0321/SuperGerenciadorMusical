import React, { useState, useEffect } from 'react';
import axios from 'axios';
import './styles.css';
import Logo from '../../Imagens/gm.png';
import { TratamentoDeErro } from '../../Componentes/TratamentoDeErro'; 

export function PaginaInicial(){
    function RecebeURI() {
    
        const [uri, setUri] = useState();
        
        const buscaUri = () => {
          axios.get("http://localhost:8080/autenticacao/iniciar").then(res => {
            setUri(res.data);
          }).catch(err => {
            TratamentoDeErro(err);
          });
        }

        useEffect(() => {
            buscaUri();
          }, []);
        
        return (
            <div className="redirectButton">
                      <a
                        href={uri}
                      >
                        <h1 id="redirect"> Entrar </h1>
                      </a>    
            </div>
          )
     }
     
    return(
        <div className="background">
            <div className="bodyLogin">
                <h1>Super gerenciador do Spotify</h1>
                <img className="logo"
                    src={Logo} 
                    alt="logo" />
                <div className="login">
                <RecebeURI/>
                </div>
            </div>
        </div>
    )
}