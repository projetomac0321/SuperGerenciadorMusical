import React from 'react';
import './styles.css';
import { Outlet, NavLink } from 'react-router-dom';
import Logo from '../Imagens/gm.png';
import { FiList, FiPlusSquare, 
         FiMusic, FiBook, 
         FiBookOpen, FiEdit3,
         FiFilter
       } from 'react-icons/fi';

export function Pagina() { 
    return (
        <div className="page">
               <div className="sidebar">
                  <img 
                      className="sidebarLogo"
                      src={Logo} 
                      alt="logo"
                    />
                    <hr className="divider"/>

                <nav className="links">                      
                    <NavLink className="link"      
                             to="/searchsongs">
                        <FiMusic className="symbol"/> 
                        Procurar Músicas
                    </NavLink>

                    <NavLink className="link"      
                             to="/searchalbums">
                        <FiBook className="symbol"/> 
                        Procurar Álbuns
                    </NavLink>

                    <NavLink className="link"      
                             to="/searchplaylists">
                        <FiBookOpen className="symbol"/> 
                        Procurar Playlists
                    </NavLink>

                    <NavLink className="link"      
                             to="/searchartists">
                        <FiEdit3 className="symbol"/> 
                        Procurar Artistas
                    </NavLink>

                    <NavLink className="link" 
                             to="/listplaylists">
                        <FiList className="symbol"/> 
                        Listar Playlists
                    </NavLink>
 
                    <NavLink className="link" 
                             to="/createplaylists">
                        <FiPlusSquare className="symbol"/>   
                        Criar Playlists
                    </NavLink>

                    <NavLink className="link" 
                             to="/buscar-nas-playlists">
                        <FiFilter className="symbol"/>   
                        Filtragem nas Playlists
                    </NavLink>

                 </nav>
            </div>
                
            <div className="body">
                <Outlet />
            </div>
        </div>
  )
}
