import APIService from '../../services/APIService'
import {useEffect, useState} from "react";
import {Link} from "react-router-dom";
import './Games.css'

interface GamesProps {
    closeModal: () => void;
}

function Games( {closeModal}: GamesProps ) {
    const [games, setGames] = useState<any[]>();

    useEffect(() => {
        // Charge l'état initial de la ville depuis le serveur
        fetch('http://localhost:8080/games')
            .then(response => {
                if (!response.ok) {
                    throw new Error(`HTTP error! Status: ${response.status}`);
                }
                return response.json();
            })
            .then(data => {
                console.log("Games : ", data);
                setGames(data);
            })
            .catch(error => {
                console.error("Error fetching game : ", error);
            });
    }, []);

    return (
        <div className="nb_player_wrapper">
            <img onClick={() => closeModal()} id="close" className="icon-close" src="/assets/general/cross.svg" alt="fermer"/>
            <div className="nb_player">
                <h2>Parties en cours</h2>
                {games?.map((game:any) => (
                    <Link key={game.id} to={`/game/${game.id}`} className="btn_game">
                        {game.name}
                    </Link>
                ))}
            </div>
        </div>
    )
}

export default Games;