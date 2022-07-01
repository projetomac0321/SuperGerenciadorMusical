import React from 'react';
import './styles.css';
import { Voltar } from '../Voltar';

export function ApresentacaoDoElemento({elementImage, elementName, goBack, releaseYear}){
    return(
      <div>
                   {goBack != null ? <Voltar place={goBack}/> : null}
        <div className={goBack != null ? "listHeader" : "listHeader lowerTop"}>
          <img className="image" src={elementImage} alt="" />
          <div className="textElement">
              <h1> {elementName.substring(0, 37)} 
                {elementName.length > 37 ? "..." : null}
              </h1>
              {releaseYear != null ?
              <h3 className="elementInLine"> {releaseYear} </h3> : null }
          </div>
        </div>
          <hr className="listDivider"/>
      </div>
    );
}