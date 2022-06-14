import React, { useState, useEffect } from 'react';
import axios from 'axios';
import './styles.css';
import Logo from '../../images/gm.png';

export function InitialPage(){
    function GetURI() {
    
        const [uri, setUri] = useState();
        
        const fetchUri = () => {
          axios.get("http://localhost:8080/autenticacao/iniciar").then(res => {
            setUri(res.data);
          }).catch(err => console.log(err.message));
        }

        useEffect(() => {
            fetchUri();
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
                <GetURI/>
                </div>
            </div>
        </div>
    )
}