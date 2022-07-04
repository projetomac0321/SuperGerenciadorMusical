import React from 'react';
import { FiltroNaBuscaDeMusicas } from '../../../Componentes/FiltroNaBuscaDeMusicas';

export function BuscarPorModo(){
    return(
        <FiltroNaBuscaDeMusicas
           name="Modo"
           index={9}
           min={0}
           max={1}
        />
     );
}