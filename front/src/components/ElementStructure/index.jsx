import React from 'react';
import './styles.css';
import { GoBack } from '../GoBack';

export function ElementStructure({elementImage, elementName, goBack}){
    return(
      <div>
                   <GoBack place={goBack}/>
        <div className="listHeader">
          <img className="image" src={elementImage} alt="" />
          <h1> {elementName} </h1>
        </div>
          <hr className="listDivider"/>
      </div>
    );
}