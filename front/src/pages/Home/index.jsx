import React from 'react';
import './styles.css';
import { Header } from '../../components/Header';

export function Home(){

    return(
        <div className="home">
            <div className="bodyHeader">
                  <Header/>
                </div>
        </div>
    );
}