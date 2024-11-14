import APIService from '../../services/APIService'
import {useEffect, useState} from "react";
import {Link} from "react-router-dom";
import './GameMode.css'

interface GameModeProps {
    closeModal: () => void;
}

function GameMode( {closeModal}: GameModeProps ) {
    
    return (
        <div className="nb_player_wrapper">
            <img onClick={() => closeModal()} id="close" className="icon-close" src="/assets/general/cross.svg" alt="fermer"/>
            <div className="nb_player">
                <h2>Choisissez le mode de jeu</h2>
                <Link to="/game" onClick={() => closeModal()} className="btn_game">
                    Solo
                </Link>
                <Link to="/" onClick={() => closeModal()} className="btn_game">
                    Multi joueur
                </Link>
            </div>
        </div>
    )
}

export default GameMode;