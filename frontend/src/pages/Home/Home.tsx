import {startNewGame} from '../../services/GameService'
import Games from '../../components/Games/Games';
import {useEffect, useState} from "react";
import {Link} from "react-router-dom";
import style from './Home.module.css'
import GameMode from '../../components/GameMode/GameMode';

function Home() {
    const [game, setGame] = useState(null);
    const [modal_game_mode, display_modal_game_mode] = useState(false);
    const [modal_games, display_modal_games] = useState(false);

    useEffect(() => {
        document.body.classList.add('home_page');
        const btnContainer = document.getElementById(style.btn_container);
        if (modal_game_mode || modal_games) {
            btnContainer?.setAttribute('inert', 'true');
            btnContainer?.style.setProperty('filter', 'blur(5px)');
        } else {
            btnContainer?.removeAttribute('inert');
            btnContainer?.style.removeProperty('filter');
        }
        return () => {
            document.body.classList.remove('home_page');
        };
    }, [modal_game_mode, modal_games]);

    const initGame = async () => {
        const newGame = await startNewGame();
        setGame(newGame);
    };

    const closeGameMode = () => display_modal_game_mode(false);
    const closeGames = () => display_modal_games(false);

    return (
        <div className={style.home_page}>
            <div id={style.btn_container}>
                <a className={style.btn_game} onClick={() => display_modal_game_mode(true)}>Nouvelle partie</a>
                <a className={style.btn_game} onClick={() => display_modal_games(true)}>Continuer une partie</a>
                <Link className={style.btn_game} to="/about">A propos du jeu</Link>
            </div>

            {modal_game_mode && <GameMode closeModal={closeGameMode} />}

            {modal_games && <Games closeModal={closeGames} />}
        </div>
    );
}

export default Home;
