import {React} from 'react';
import './styles.css';
import { Outlet, NavLink } from 'react-router-dom';
import Logo from '../Imagens/gm.png';
import { FiList, FiPlusSquare, 
         FiMusic, FiBook, 
         FiBookOpen, FiEdit3
       } from 'react-icons/fi';
export function Pagina() { 
        
    return (
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
                    <Outlet />
            </div>
        </div>
  )
}

