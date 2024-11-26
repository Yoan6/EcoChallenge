import { useState } from "react";
import './GameMode.css';
import { useModals } from "../../utils/context/ModalContext";

interface GameModeProps {
    setNbPlayer: (value: number) => void; // Nouvelle prop pour mettre à jour nb_player dans Home
}

const GameMode: React.FC<GameModeProps> = ({ setNbPlayer }) => {
    const {
        setGameMode,
        setInfoGame,
    } = useModals();
    
    const [multi, displayMulti] = useState(false);

    function changePageToInfoGame() {
        setGameMode(false);
        setInfoGame(true);
    }
    function changePageToInfoGameSolo() {
        setNbPlayer(1);
        setGameMode(false);
        setInfoGame(true);
    }

    return (
        <div className="game_mode_wrapper">
            <img onClick={() => setGameMode(false)} className="icon-close" src="/assets/general/cross.svg" alt="fermer" />
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

            <div className="breadcrumb">
                <span className="step-number">1</span>
                <span className="step-number inactive"></span>
            </div>
        </div>
    );
}

export default GameMode;
