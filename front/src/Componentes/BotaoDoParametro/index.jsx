import React from 'react';
import './styles.css';

export function BotaoDoParametro({name}){
    return(
        <div className="buttonParameter">
            <button>
                 <h1>{name}</h1>
            </button>
        </div>
    );
}