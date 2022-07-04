import React from 'react';
import { BuscarMusicasFiltradas } from '../BuscarMusicasFiltradas';
import { Voltar } from '../Voltar';

export function FiltroNaBuscaDeMusicas({name, min, max, index}){

    return(
        <div className="inBlock">
         <div className="inline">
            <Voltar place="/searchsongs"/>
        </div>
                <BuscarMusicasFiltradas indices={[index]}
                    name={name}
                    min={min}
                    max={max}
                />  
        </div>
    )
}