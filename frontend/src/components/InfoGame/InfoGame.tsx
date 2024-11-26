import { useState } from "react";
import './InfoGame.css';
import { useNavigate } from "react-router-dom";
import APIService from "../../services/APIService";

interface InfoGameProps {
    nb_player: number;
    displayInfoGame: (value: boolean) => void;
    displayGameMode: (value: boolean) => void;
    setNbPlayer: (value: number) => void;
}

interface Player {
    name: string;
    color: string;
}

function InfoGame({ nb_player, displayInfoGame, displayGameMode, setNbPlayer }: InfoGameProps) {
    const [players, setPlayers] = useState<Player[]>(
        Array.from({ length: nb_player }, () => ({ name: "", color: "#000000" }))
    );
    const [playerErrors, setPlayerErrors] = useState<string[]>(Array(nb_player).fill("")); // Un état pour les erreurs par joueur
    const [gameName, setGameName] = useState("");
    const [game_name_error, setGameNameError] = useState("");

    const navigate = useNavigate();  // Pour le routing

    const handleNameChange = (index: number, name: string) => {
        const updatedPlayers = [...players];
        updatedPlayers[index].name = name;
        setPlayers(updatedPlayers);

        // Vérification immédiate pour le nom vide
        const updatedErrors = [...playerErrors];
        updatedErrors[index] = name.trim() === "" ? "Un joueur doit avoir un nom. " : "";
        setPlayerErrors(updatedErrors);
    };

    const handleColorChange = (index: number, color: string) => {
        const updatedPlayers = [...players];
        updatedPlayers[index].color = color;
        setPlayers(updatedPlayers);

        // Mise à jour dynamique des erreurs liées aux couleurs
        const updatedErrors = [...playerErrors];
        updatedPlayers.forEach((player, idx) => {
            const isDuplicate = updatedPlayers.some(
                (otherPlayer, otherIndex) =>
                    otherIndex !== idx && otherPlayer.color === player.color
            );
            updatedErrors[idx] = isDuplicate
                ? "Cette couleur est déjà utilisée par un autre joueur. "
                : "";
        });

        setPlayerErrors(updatedErrors);
    };

    const handleGameNameChange = (value: string) => {
        setGameName(value);

        // Mise à jour de l'erreur du nom de la partie
        if (value.trim() === "") {
            setGameNameError("Une partie doit avoir un nom.");
        } else {
            setGameNameError("");
        }
    };

    const backToGameMode = () => {
        displayInfoGame(false);
        displayGameMode(true);
    }

    async function StartGame() {
        let updatedErrors = Array(nb_player).fill(""); // Réinitialise toutes les erreurs avant de commencer la validation
        setGameNameError(""); // Réinitialise l'erreur du nom de la partie

        // Validation des joueurs
        players.forEach((player, index) => {
            const isDuplicate = players.some(
                (otherPlayer, otherIndex) =>
                    otherIndex !== index && otherPlayer.color === player.color
            );

            if (isDuplicate) {
                updatedErrors[index] = "Cette couleur est déjà utilisée par un autre joueur. ";
            }

            if (player.name.trim() === "") {
                updatedErrors[index] += "Un joueur doit avoir un nom. ";
            }
        });

        setPlayerErrors(updatedErrors); // Mise à jour des erreurs

        // Validation du nom de la partie
        if (gameName.trim() === "") {
            setGameNameError("Une partie doit avoir un nom.");
        }

        // Vérifie si des erreurs existent
        const hasErrors = updatedErrors.some((error) => error !== "") || game_name_error !== "";
        if (hasErrors) {
            console.error("Impossible de démarrer la partie");
            return;
        }

        const gameData = {
            name: gameName,
        };

        // Création de la partie
        try {
            await APIService.request("POST", "/games", gameData);
        } catch (error: any) {
            console.log("Erreur lors de la création d'une game : ", error.message);
            setGameNameError(error.message)
            return
        }

        let gamesInBdd: any[] = [];
        let gameInBdd: any;
        let gameId: number | undefined;

        // Récupération de l'id de la partie généré automatiquement
        const responseGames = await APIService.request("GET", "/games");
        gamesInBdd = responseGames.games;

        gamesInBdd.forEach(game => {
            if (game.name === gameName) {
                gameId = game.id;
            }
        })

        // Création des joueurs et attente de toutes les requêtes
        try {
            await Promise.all(
                players.map((player) =>
                    APIService.request("POST", `/players/game/${gameId}`, player)
                )
            );
        } catch (error: any) {
            console.log("Erreur lors de la création des joueurs :", error.message);
            return;
        }

        // Récupération de la partie
        try {
            const responseGame = await APIService.request("GET", "/games/" + gameId);
            gameInBdd = responseGame.game;
        } catch (error: any) {
            console.log("Erreur lors de la récupération de la partie :", error.message);
            return
        }

        const firstIdPlayer = gameInBdd.players[0].id;

        gameInBdd.player_id_actual = firstIdPlayer;
        gameInBdd.nb_turn = 1;

        // Définition du joueur actuel de la partie
        try {
            await APIService.request("PUT", "/games/" + gameId, gameInBdd);
        } catch (error: any) {
            console.log("Erreur lors de la modification d'une partie:", error.message);
            return
        }

        // Redirection vers la page Game
        navigate("/game/"+gameId);
    }


    return (
        <div className="info_game_wrapper">
            <img
                onClick={() => {
                    displayInfoGame(false);
                    setNbPlayer(2); // Réinitialise nb_player lors de la fermeture
                }}
                id="close"
                className="icon-close"
                src="/assets/general/cross.svg"
                alt="fermer"
            />
            <h2>Informations de la partie</h2>
            <label htmlFor={"game_name"}>Nom de la partie</label>
            <input
                id={"game_name"}
                type="text"
                value={gameName}
                onChange={(e) => handleGameNameChange(e.target.value)}
                placeholder="Quiz écologie"
            />
            {game_name_error && <p className="error-message">{game_name_error}</p>}
            <hr />
            <div className="players">
                {Array.from({ length: nb_player }, (_, i) => (
                    <div key={i} className="player_info">
                        <h3>Joueur {i + 1}</h3>
                        <label htmlFor={`name-${i}`}>Nom</label>
                        <input
                            id={`name-${i}`}
                            type="text"
                            value={players[i].name}
                            onChange={(e) => handleNameChange(i, e.target.value)}
                            placeholder="Pierre, Jean, Thomas,..."
                            maxLength={30}
                        />
                        <label htmlFor={`color-${i}`}>Couleur</label>
                        <input
                            id={`color-${i}`}
                            type="color"
                            value={players[i].color}
                            onChange={(e) => handleColorChange(i, e.target.value)}
                        />
                        {playerErrors[i] && <p className="error-message">{playerErrors[i]}</p>}
                    </div>
                ))}
            </div>
            <div className="btn_wrapper">
                <a onClick={() => StartGame()} className="btn_game">
                    Lancer la partie
                </a>
            </div>

            <a onClick={() => backToGameMode()} className="back btn_game">
                <img className="icon" src="/assets/general/back.svg" alt="retour"/>
                <span>Précédent</span>
            </a>

            <div className="breadcrumb">
                <span className="step-number inactive"></span>
                <span className="step-number">2</span>
            </div>
        </div>
    );
}

export default InfoGame;
