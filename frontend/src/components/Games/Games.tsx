import APIService from '../../services/APIService'
import { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import './Games.css'
import { useModals } from '../../utils/context/ModalContext';

const Games: React.FC = () => {
    const {
        setGames
    } = useModals();
    
    const [games, changeGames] = useState<any[]>([]);

    useEffect(() => {
        // Charge l'état initial de la ville depuis le serveur via APIService
        const fetchGames = async () => {
            try {
                const data = await APIService.request('GET', '/games');
                console.log("Games : ", data.games);
                changeGames(data.games);
            } catch (error) {
                console.error("Error fetching games:", error);
            }
        };

        fetchGames();
    }, []);

    return (
        <div className="games_wrapper">
            <img onClick={() => setGames(false)} className="icon-close" src="/assets/general/cross.svg" alt="fermer" />
            <h2>Parties en cours</h2>
            <div className="games">
                {games?.map((game: any) => (
                    <Link key={game.id} to={`/game/${game.id}`} className="btn_game">
                        {game.name}
                    </Link>
                ))}
            </div>
        </div>
    )
}

export default Games;