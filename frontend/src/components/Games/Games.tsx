import APIService from '../../services/APIService'
import {useEffect, useState} from "react";
import {Link} from "react-router-dom";
import './Games.css'

interface GamesProps {
    displayGames: (value: boolean) => void;
}

function Games( {displayGames}: GamesProps ) {
    const [games, setGames] = useState<any[]>([]);

    useEffect(() => {
        // Charge l'état initial de la ville depuis le serveur via APIService
        const fetchGames = async () => {
            try {
                const data = await APIService.request('GET', '/games');
                console.log("Games : ", data.games);
                setGames(data.games);
            } catch (error) {
                console.error("Error fetching games:", error);
            }
        };

        fetchGames();
    }, []);

    return (
        <div className="games_wrapper">
            <img onClick={() => displayGames(false)} className="icon-close" src="/assets/general/cross.svg" alt="fermer"/>
            <div className="games">
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