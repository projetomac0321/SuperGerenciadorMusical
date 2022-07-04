import {React, useRef, useState, useEffect} from 'react';
import './styles.css';
import { Outlet, NavLink, useLocation} from 'react-router-dom';
import Seta from '../../images/setaDireita.png';
import Logo from '../../images/gm.png';
import { FiList, FiPlusSquare, 
         FiMusic, FiBook, 
         FiBookOpen, FiEdit3
       } from 'react-icons/fi';

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
    
    /*function getCode (){
        const routePath = useLocation().search;
        const code = new URLSearchParams(routePath).get('code');
        console.log(routePath);

        axios.get(`http://localhost:8080/autenticacao/finaliza?uris=${[code]}`,{
          })
          .catch(err => {
            if(err.response) {
              alert("Tente novamente. Falha na resposta, erro de status code " + err.response.status); 
              window.location.href = "http://localhost:3000/";}
            else if(err.request){
              alert("Tente novamente mais tarde. A requisição foi feita, mas nenhuma resposta foi obtida");
              window.location.href = "http://localhost:3000/";}
            else {
              alert("Tente novamente mais tarde. Erro na configuração da requisição.");
              window.location.href = "http://localhost:3000/";}
          });
    };*/
    return (   
        //getCode(),
        <div className="page">
            <div className="sidebar">
                <nav className='toMenu'>
                    <NavLink id='menuLink'
                             className="link"
                             to="/home">
                        <img 
                      className="sidebarLogo"
                      src={Logo} 
                      alt="logo"
                    />
                    
                    </NavLink>
                    <hr className="divider"/>
                </nav>
                <nav className="links">                      
                    <NavLink id="searchSongsLink"
                             className="link"      
                             to="/searchsongs">
                        <FiMusic className="symbol"/> 
                        Procurar Músicas
                    </NavLink>

                    <NavLink id="searchAlbumsLink"
                             className="link"      
                             to="/searchalbums">
                        <FiBook className="symbol"/> 
                        Procurar Álbuns
                    </NavLink>

                    <NavLink id="searchPlaylistsLink"
                             className="link"      
                             to="/searchplaylists">
                        <FiBookOpen className="symbol"/> 
                        Procurar Playlists
                    </NavLink>

                    <NavLink id="searchArtistsLink"
                             className="link"      
                             to="/searchartists">
                        <FiEdit3 className="symbol"/> 
                        Procurar Artistas
                    </NavLink>

                    <NavLink id="listLink"
                             className="link" 
                             to="/listplaylists">
                        <FiList className="symbol"/> 
                        Listar Playlists
                    </NavLink>
 
                    <NavLink id="createLink"
                             className="link" 
                             to="/createplaylists">
                        <FiPlusSquare className="symbol"/>   
                        Criar Playlists
                    </NavLink>

                 </nav>
            
            </div>
            <div className="body">
                <h1>Bem Vindo ao Super Gerenciador Musical!!</h1>
                <br></br><br></br>
                
                <h2>Aqui sua imersao no mundo musical eh muito maior, vem com a gente!!</h2>
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
                                        <br></br>{text}                               
                                        </span>
                                    </div>
                                </div>
                            );
                        })}
                    </div>

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
        </div>
  )
}

