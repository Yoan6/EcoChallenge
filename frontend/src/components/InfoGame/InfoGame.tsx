import APIService from '../../services/APIService'
import {useEffect, useState} from "react";
import {Link} from "react-router-dom";
import './InfoGame.css'

interface InfoGameProps {
    nb_player: number;
}

function InfoGame({ nb_player }: InfoGameProps) {
    return (
        <div>
            <h2>Informations sur la partie</h2>
            <p>Nombre de joueurs : {nb_player}</p>
        </div>
    );
}

export default InfoGame;
