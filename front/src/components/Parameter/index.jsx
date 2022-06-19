import React from 'react';


export function Parameter({name, value}){
    return(
        <div className="elementRow">
            <div className="elementRowText">
                 <h1>{name}</h1>
                 <p>{value}</p>
            </div>
        </div>
    );
}