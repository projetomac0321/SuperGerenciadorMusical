import React from 'react';
import './styles.css';
import { Outlet, NavLink } from 'react-router-dom';
import Logo from '../images/gm.png';
import { FiList, FiPlusSquare } from 'react-icons/fi';
import { AiFillHome } from "react-icons/ai";

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
                    <NavLink id="homeLink"
                             className="link"      
                             to="/home">
                        <AiFillHome className="symbol"/> 
                        Home
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
