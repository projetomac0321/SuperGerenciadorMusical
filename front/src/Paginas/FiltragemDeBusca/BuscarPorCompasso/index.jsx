import React from 'react';
import { BuscarMusicasFiltradas } from '../../../Componentes/BuscarMusicasFiltradas';

export function BuscarPorCompasso(){
    return(
       <div> 
            <BuscarMusicasFiltradas indices={[12]}
                valoresMaxMinPorFiltro={[3, 7]}
              />  
        </div>
    );
}