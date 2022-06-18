import React, {useEffect, useState} from 'react';
import axios from 'axios';

export function ParametersMapped({ songsIds, parameterName, title }){
    const [parameters, setParameters] = useState([]);
    const [show, setShow] = useState(false);

    function ParametersSort(){
          
        const sortParameters = () => {
            axios.get(`http://localhost:8080/buscar-musicas/obter-parametros-das-musicas?idsDasMusicas=${songsIds}`)
            .then(res => {
                let length = (res.data.length <= 50 ? res.data.length : 50);
                for(var i = 0; i < length; i += 1)
                {
                  parameters[i] = res.data[i][parameterName];
                }
                setParameters(parameters);
                setShow(true);
        }).catch(err => {
            setShow(false)});
       }

        useEffect(() => {
             sortParameters();
          }, []);


        return (
                <div className={show ? "show columnParameter" : "hide columnParameter"}>
                                <div className="tableHeader">
                                    <h1 className="tableTitleParameter">{title}</h1>
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

    return(
        <ParametersSort/>
    );
}