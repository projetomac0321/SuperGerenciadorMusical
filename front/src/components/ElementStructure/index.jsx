import React from 'react';
import './styles.css';
import { GoBack } from '../GoBack';

export function ElementStructure({elementImage, elementName, goBack, comment}){
    return(
      <div>
                   <GoBack place={goBack}/>
        <div className="listHeader">
          <img className="image" src={elementImage} alt="" />
          <div className="textElement">
            <h1> {elementName} </h1>
            <h3> {comment}</h3>
          </div>
        </div>
          <hr className="listDivider"/>
      </div>
    );
}