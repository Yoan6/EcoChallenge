import APIService from '../../services/APIService'
import {useEffect, useState} from "react";
import {Link} from "react-router-dom";
import './Games.css'

interface GamesProps {
    closeModalGames: () => void;
}

function Games( {closeModalGames}: GamesProps ) {
    const [games, setGames] = useState<any[]>([]);

    useEffect(() => {
        // Charge l'état initial de la ville depuis le serveur via APIService
        const fetchGames = async () => {
            try {
                const data = await APIService.request('GET', '/games');
                console.log("Games : ", data);
                setGames(data);
            } catch (error) {
                console.error("Error fetching games:", error);
            }
        };

        fetchGames();
    }, []);

    return (
        <div className="nb_player_wrapper">
            <img onClick={() => closeModalGames()} id="close" className="icon-close" src="/assets/general/cross.svg" alt="fermer"/>
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