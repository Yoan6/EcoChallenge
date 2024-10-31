import {startNewGame} from '../../services/GameService'
import {useEffect, useState} from "react";
import {Link} from "react-router-dom";
import style from './Home.module.css'

function Home() {
    const [game, setGame] = useState(null);
    const [modal_new_game, display_modal_new_game] = useState(false);

    useEffect(() => {
        document.body.classList.add('home_page');
        const btnContainer = document.getElementById(style.btn_container);
        if (modal_new_game) {
            btnContainer?.setAttribute('inert', 'true');
            btnContainer?.style.setProperty('filter', 'blur(5px)');
        } else {
            btnContainer?.removeAttribute('inert');
            btnContainer?.style.removeProperty('filter');
        }
        return () => {
            document.body.classList.remove('home_page');
        };
    }, [modal_new_game]);

    const initGame = async () => {
        const newGame = await startNewGame();
        setGame(newGame);
    };

    return (
        <div className={style.home_page}>
            <div id={style.btn_container}>
                <Link className={style.btn_game} to="/" onClick={() => display_modal_new_game(true)}>Nouvelle partie</Link>
                <Link className={style.btn_game} to="/about">A propos du jeu</Link>
            </div>

            {modal_new_game && (
                <div className={style.nb_player_wrapper}>
                    <img onClick={() => display_modal_new_game(false)} id={style.close} className="icon-close" src="/assets/general/cross.svg" alt="fermer"/>
                    <div className={style.nb_player}>
                        <h2>Choisissez le mode de jeu</h2>
                        <Link to="/game" onClick={() => display_modal_new_game(false)} className={style.btn_game}>
                            Solo
                        </Link>
                        <Link to="/" onClick={() => display_modal_new_game(false)} className={style.btn_game}>
                            Multi joueur
                        </Link>
                    </div>
                </div>
            )}
        </div>
    );
}

export default Home;
