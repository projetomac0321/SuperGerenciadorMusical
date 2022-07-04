import React from 'react';
import { FiltroNaBuscaDeMusicas } from '../../../Componentes/FiltroNaBuscaDeMusicas';

export function BuscarPorTonalidade(){
     return(
        <FiltroNaBuscaDeMusicas
           name="Tonalidade"
           index={6}
           min={-1}
           max={11}
        />
     );
}