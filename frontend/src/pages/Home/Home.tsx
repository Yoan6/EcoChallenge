import {startNewGame} from '../../services/GameService'
import {useState} from "react";
import {Link} from "react-router-dom";
import style from './Home.module.css'

function Home() {
  const [game, setGame] = useState(null);

    const initGame = async () => {
      const newGame = await startNewGame();
      setGame(newGame);
    };

  return (
      <div className={style.home_page}>
          <div id={style.btn_container}>
              <Link className={style.btn_game} to="/" onClick={() => initGame()}>Nouvelle partie</Link>
              <img src="/assets/Home/game_name.png" alt="Nom du jeu"/>
              <Link className={style.btn_game} to="/about">A propos du jeu</Link>
          </div>
      </div>
  )
}

export default Home;
