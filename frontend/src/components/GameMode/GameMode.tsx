import { useState } from "react";
import './GameMode.css';

interface GameModeProps {
    closeModalGameMode: () => void;
    openModalInfoGame: () => void;
    setNbPlayer: (value: number) => void; // Nouvelle prop pour mettre à jour nb_player dans Home
}

function GameMode({ closeModalGameMode, openModalInfoGame, setNbPlayer }: GameModeProps) {
    const [multi, displayMulti] = useState(false);

    function changePageToInfoGame() {
        closeModalGameMode();
        openModalInfoGame();
    }
    function changePageToInfoGameSolo() {
        setNbPlayer(1);
        closeModalGameMode();
        openModalInfoGame();
    }

    return (
        <div className="nb_player_wrapper">
            <img onClick={() => closeModalGameMode()} id="close" className="icon-close" src="/assets/general/cross.svg" alt="fermer" />
            <div className="game_mode">
                <h2>Choisissez le mode de jeu</h2>
                <a onClick={() => changePageToInfoGameSolo()} className="btn_game">
                    Solo
                </a>
                <a onClick={() => displayMulti(true)} className="btn_game">
                    Multi joueur
                </a>
                {multi && (
                    <>
                        <div id="nb_player">
                            <p>Nombre de joueur : </p>
                            <select onChange={(e) => setNbPlayer(parseInt(e.target.value))}> {/* Met à jour le nombre de joueur */}
                                {Array.from({ length: 9 }, (_, i) => (
                                    <option key={i + 2} value={i + 2}>{i + 2}</option>
                                ))}
                            </select>
                        </div>

                        <a onClick={() => changePageToInfoGame()} className="btn_game" id="define_game">
                            Définir la partie
                        </a>
                    </>
                )}
            </div>
        </div>
    );
}

export default GameMode;
