import React, {useState} from 'react';
import './styles.css';
import { FiSearch } from 'react-icons/fi';
import axios from 'axios';

export function SearchData({ getRoute, getParamName, navRoute, text, hasImage, nullImage, placeholder}){
    const [searchInput, setSearchInput] = useState("");
    
    const [data, setData] = useState([]); 
    const [searching, setSearching] = useState(false);

    function handleSearch(bool){
        setSearching(bool);
    }

    const [offset, setOffset] = useState(0);
    
        const fetchSearchData = () => {
          if(searchInput != "")
          {
              axios.get(`http://localhost:8080/buscar-musicas/${getRoute}?${getParamName}=${searchInput}&offset=${offset}`).then(res => {
                  setData(res.data);
                }).catch(err => console.log(err.message));
              setOffset(offset + 50);
              handleSearch(true);  
          }
          else 
          {
            handleSearch(false);
          }
        }

    return(
        <div className="searchData">
            <div className="searchDataHeader">
                            <div className="headerInput">
                                <input
                                id="searchInput"
                                placeholder={placeholder}
                                type="text"
                                onChange={(e) => {setSearchInput(e.target.value); setOffset(0)}}
                                />
                                
                            </div>

                            <div className="buttonSearch">
                                <button onClick={fetchSearchData}>
                                    <FiSearch className="icon"/>
                                </button>
                            </div>
                </div>
                <nav className={searching ? "elementsSearch" : "hide elementsSearch"}>
                        {data.map((result) => ( 
                            <div className="elementRowSearch">
                                        <div className="elementRow">
                                            <div className="elementInfo">
                                                { hasImage? 
                                                <img className="elementImage" src={
                                                    result.images[0] != null ? result.images[0].url : nullImage
                                                } alt="" /> : <p>Teste</p>}
                                                <div className="elementRowText" onClick={e => { e.preventDefault(); setTimeout(function(){
                                            window.location.href = `http://localhost:3000/${navRoute}_${result.id}`;}, 100);
                                            }}>
                                                    <h1>{result.name}</h1>
                                                </div>
                                            </div>
                                        </div>
                            </div>
                        ))}    
                    <div className="elementCreate">
                          <div className="elementCreateText" onClick={fetchSearchData}>
                            <h1>{text}</h1>
                          </div>
                      </div>
                </nav>
        </div>
    );
}