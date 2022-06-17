import React from 'react';


export function Parameter({name, value}){
    return(
        <div className="songRow">
            <div className="songRowText">
                 <h1>{name}</h1>
                 <p>{value}</p>
            </div>
        </div>
    );
}