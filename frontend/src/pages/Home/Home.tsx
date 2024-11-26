import { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import GameMode from '../../components/GameMode/GameMode';
import InfoGame from '../../components/InfoGame/InfoGame';
import Games from '../../components/Games/Games';
import style from './Home.module.css';
import { useModals } from "../../utils/context/ModalContext";

const Home: React.FC = () => {
    const {
        modalGameMode,
        modalGames,
        modalInfoGame,
        setGameMode,
        setGames,
    } = useModals();
    const [nb_player, setNbPlayer] = useState<number>(2); // Nouvel état pour le nombre de joueurs

    useEffect(() => {
        document.body.classList.add('home_page');
        const btnContainer = document.getElementById(style.btn_container);
        if (modalGameMode || modalGames || modalInfoGame) {
            btnContainer?.setAttribute('inert', 'true');
            btnContainer?.style.setProperty('filter', 'blur(5px)');
        } else {
            btnContainer?.removeAttribute('inert');
            btnContainer?.style.removeProperty('filter');
        }
        return () => {
            document.body.classList.remove('home_page');
        };
    }, [modalGameMode, modalGames, modalInfoGame]);

    return (
        <div className={style.home_page}>
            <div id={style.btn_container}>
                <a className="btn_game" onClick={() => setGameMode(true)}>Nouvelle partie</a>
                <a className="btn_game" onClick={() => setGames(true)}>Continuer une partie</a>
                <Link className="btn_game" to="/about">A propos du jeu</Link>
            </div>

            {modalGameMode && (
                <GameMode
                    setNbPlayer={setNbPlayer} // Passe la fonction pour mettre à jour nb_player
                />
            )}

            {modalGames && <Games />}

            {modalInfoGame && (
                <InfoGame
                    nb_player={nb_player}
                    setNbPlayer={setNbPlayer}
                />
            )}
        </div>
    );
}

export default Home;
