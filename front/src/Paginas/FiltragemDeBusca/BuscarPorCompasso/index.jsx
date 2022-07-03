import React from 'react';
import { BuscarMusicasFiltradas } from '../../../Componentes/BuscarMusicasFiltradas';

export function BuscarPorCompasso(){
    return(
       <div> 
          <h1>Hi</h1>
            <BuscarMusicasFiltradas indices={[7]}
                valoresMaxMinPorFiltro={[2, 0]}
              />  
        </div>
    );
}