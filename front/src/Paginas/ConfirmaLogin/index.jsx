import React, { useState, useEffect } from 'react';
import { useLocation} from 'react-router-dom';
import axios from 'axios';

export function ConfirmaLogin(){
    function getCode (){    
        const routePath = useLocation().search;
        const code = new URLSearchParams(routePath).get('code');
        console.log(code);
        axios.get(`http://localhost:8080/autenticacao/finaliza?code=${[code]}`)
          .catch(err => {
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
        
        window.location.href = "http://localhost:3000/home"
    };
    getCode();
}