import React from 'react';
import './styles.css';
import { Outlet, NavLink } from 'react-router-dom';
import Logo from '../images/gm.png';
import { FiList, FiPlusSquare, FiSearch } from 'react-icons/fi';

export function App() { 
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
                    <NavLink id="searchSongsLink"
                             className="link"      
                             to="/searchsongs">
                        <FiSearch className="symbol"/> 
                        Procurar Músicas
                    </NavLink>

                    <NavLink id="searchAlbumsLink"
                             className="link"      
                             to="/searchalbums">
                        <FiSearch className="symbol"/> 
                        Procurar Álbuns
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
