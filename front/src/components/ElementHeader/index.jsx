import React from 'react';
import './styles.css';
import { GoBack } from '../GoBack';

export function ElementHeader({elementImage, elementName, goBack}){
    return(
      <div>
                   {goBack != null ? <GoBack place={goBack}/> : null}
        <div className={goBack != null ? "listHeader" : "listHeader lowerTop"}>
          <img className="image" src={elementImage} alt="" />
          <div className="textElement">
              <h1> {elementName.substring(0, 37)} 
                {elementName.length > 37 ? "..." : null}
              </h1>
          </div>
        </div>
          <hr className="listDivider"/>
      </div>
    );
}