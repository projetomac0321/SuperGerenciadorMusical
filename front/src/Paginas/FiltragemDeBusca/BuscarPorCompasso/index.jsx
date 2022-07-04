import React from 'react';
import { FiltroNaBuscaDeMusicas } from '../../../Componentes/FiltroNaBuscaDeMusicas';

export function BuscarPorCompasso(){
    return(
       <FiltroNaBuscaDeMusicas
          name="Compasso"
          index={12}
          min={3}
          max={7}
       />
    );
}