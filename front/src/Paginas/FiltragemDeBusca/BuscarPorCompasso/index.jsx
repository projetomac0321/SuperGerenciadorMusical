import React from 'react';
import { BuscarMusicasFiltradas } from '../../../Componentes/BuscarMusicasFiltradas';

export function BuscarPorCompasso(){
    return(
       <div> 
            <BuscarMusicasFiltradas indices={[7]}
                valoresMaxMinPorFiltro={[0, 2]}
              />  
        </div>
    );
}