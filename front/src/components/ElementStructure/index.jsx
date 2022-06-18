import React from 'react';
import './styles.css';
import { GoBack } from '../GoBack';

export function ElementStructure({elementImage, elementName, goBack, comment}){
    return(
      <div>
                   {goBack != null ? <GoBack place={goBack}/> : null}
        <div className={goBack != null ? "listHeader" : "listHeader lowerTop"}>
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