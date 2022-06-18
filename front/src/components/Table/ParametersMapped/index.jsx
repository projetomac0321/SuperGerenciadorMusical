import React from 'react';

export function ParametersMapped({ parameters, parameterName }){
    return (
            <div className="columnParameter">
                            <div className="tableHeader">
                                <h1 className="tableTitleParameter">{parameterName}</h1>
                            </div> 
                {parameters.map((parameter) => {
                    return (
                    <div className="parameterRow">
                                <div className="parameterRowText">
                                    <h1 className="tableElementText">{parameter}</h1>
                                </div>
                    </div>
                    )
                }
                )}
            </div>
    )
}