import React, { useState, useEffect } from 'react';
import colors from '../../utils/colors';
import GameOver from '../GameOver/GameOver';

function Game() {
    const [cityState, setCityState] = useState<string>('healthy');
    const [turnNumber, setTurnNumber] = useState<number>(1);

    useEffect(() => {
        // Charge l'état initial de la ville depuis le serveur
        // fetch('/api/game/state')
        //   .then(response => response.json())
        //   .then(data => {
        //     setCityState(data.state);
        //     setTurnNumber(data.turnNumber);
        //   });

        document.body.style.backgroundColor = getCityBackgroundColor();
        if (['bad', 'dying', 'dead'].includes(cityState)) {
            document.body.style.color = 'white';
        }

        return () => {
            document.body.style.backgroundColor = '';
            document.body.style.color = '';
        };
    }, []);

    // Récupère la couleur de fond associée à l'état de la ville
    const getCityBackgroundColor = () => {
        switch (cityState) {
            case 'healthy':
                return colors.healthy;
            case 'degraded':
                return colors.degraded;
            case 'bad':
                return colors.bad;
            case 'dying':
                return colors.dying;
            case 'dead':
                return colors.dead;
            default:
                return colors.healthy;
        }
    };

    const handleNextTurn = () => {
        // Passe au prochain tour
        fetch('/api/game/next-turn', { method: 'POST' })
            .then(response => response.json())
            .then(data => {
                setCityState(data.state);
                setTurnNumber(data.turnNumber);
            });
    };

    return (
        <>
        {cityState !== 'dead' && (
            <div>
            <h1>Tour {turnNumber}</h1>
            <p>État de la ville : {cityState}</p>
            <button onClick={handleNextTurn}>Passer au tour suivant</button>
        </div>
        )}
        
        {cityState === 'dead' && <GameOver />}
        </>
    );
}

export default Game;