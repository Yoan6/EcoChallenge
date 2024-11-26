import { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import GameMode from '../../components/GameMode/GameMode';
import InfoGame from '../../components/InfoGame/InfoGame';
import Games from '../../components/Games/Games';
import style from './Home.module.css';

function Home() {
    const [modal_game_mode, display_game_mode] = useState(false);
    const [modal_games, display_games] = useState(false);
    const [modal_info_game, display_info_game] = useState(false);
    const [nb_player, setNbPlayer] = useState<number>(2); // Nouvel état pour le nombre de joueurs

    useEffect(() => {
        document.body.classList.add('home_page');
        const btnContainer = document.getElementById(style.btn_container);
        if (modal_game_mode || modal_games || modal_info_game) {
            btnContainer?.setAttribute('inert', 'true');
            btnContainer?.style.setProperty('filter', 'blur(5px)');
        } else {
            btnContainer?.removeAttribute('inert');
            btnContainer?.style.removeProperty('filter');
        }
        return () => {
            document.body.classList.remove('home_page');
        };
    }, [modal_game_mode, modal_games, modal_info_game]);

    return (
        <div className={style.home_page}>
            <div id={style.btn_container}>
                <a className="btn_game" onClick={() => display_game_mode(true)}>Nouvelle partie</a>
                <a className="btn_game" onClick={() => display_games(true)}>Continuer une partie</a>
                <Link className="btn_game" to="/about">A propos du jeu</Link>
            </div>

            {modal_game_mode && (
                <GameMode
                    displayGameMode={display_game_mode}
                    displayInfoGame={display_info_game}
                    setNbPlayer={setNbPlayer} // Passe la fonction pour mettre à jour nb_player
                />
            )}

            {modal_games && <Games displayGames={display_games} />}

            {modal_info_game && (
                <InfoGame 
                    nb_player={nb_player} 
                    displayInfoGame={display_info_game}
                    displayGameMode={display_game_mode}
                    setNbPlayer={setNbPlayer} 
                />
            )}
        </div>
    );
}

export default Home;
