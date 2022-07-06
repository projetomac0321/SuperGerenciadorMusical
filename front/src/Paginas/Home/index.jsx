import {React, useRef, useState, useEffect} from 'react';
import './styles.css';
import { Outlet} from 'react-router-dom';
import Seta from '../../imagens/setaDireita.png';

export function Home() { 
    const [data, setData] = useState([]);
    const carousel = useRef(null);

    useEffect(() => {
        fetch('http://localhost:3000/dadosCarrosel.json')
        .then((response)=>response.json())
        .then(setData);
    }, []);

    if(!data || !data.length) return null;
    const handleLeftClick = (e) =>{
        e.preventDefault();
        carousel.current.scrollLeft -= carousel.current.offsetWidth;
    }
    const handleRightClick = (e) =>{
        e.preventDefault();
        carousel.current.scrollLeft += carousel.current.offsetWidth;
    }
    return (   
            <div className="body1">
                <h1>Bem Vindo ao Super Gerenciador Musical!!</h1>
                <br></br><br></br>
                
                <h2>Aqui sua imersão no mundo musical é muito maior, vem com a gente!!</h2>
                <br></br><br></br>
                
                <div className='infosContent'>
                    <div className='carousel' ref={carousel}>
                        {data.map((item) => {
                        const {id, image, text} = item;
                            return(
                                <div className='item' key={id}>
                                    <div className = 'image'>
                                        <img src = {image} ></img>
                                    </div>
                                    <div className='info'>
                                        <span className='name'>
                                        <br></br>
                                        <br></br>
                                        <br></br>
                                        <br></br>
                                        <br></br>
                                        <br></br>{text}                               
                                        </span>
                                    </div>
                                </div>
                            );
                        })}
                    </div>
                    <br></br><br></br>
                    <div className='buttons'>
                        <button onClick={handleLeftClick} alt="Scroll Left">
                        <img src={Seta} ></img>
                        </button>
                        <button onClick={handleRightClick} alt="Scroll Right">
                            <img src={Seta} ></img>
                        </button>
                    </div>
                    
                </div>   

                <Outlet />
        </div>
  )
}

