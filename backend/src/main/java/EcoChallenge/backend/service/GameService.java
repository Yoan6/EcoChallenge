package EcoChallenge.backend.service;

import EcoChallenge.backend.entites.Game;
import EcoChallenge.backend.entites.Player;
import EcoChallenge.backend.repository.GameRepository;
import EcoChallenge.backend.repository.PlayerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class GameService {
    private final GameRepository gameRepository;
    private final PlayerRepository playerRepository;

    public GameService(GameRepository gameRepository, PlayerRepository playerRepository) {
        this.gameRepository = gameRepository;
        this.playerRepository = playerRepository;
    }

    public List<Game> getGames() {
        return this.gameRepository.findAll();
    }

    public Game findGameById(Integer gameId) {
        Optional<Game> gameInBdd = this.gameRepository.findById(gameId);

        if (gameInBdd.isEmpty()) {
            throw new IllegalArgumentException("Partie non trouvée avec l'ID : " + gameId);
        }

        return gameInBdd.get();
    }

    public void createGame(Game game) {
        gameRepository.save(game);
    }

    public void setGame(Integer gameId, Game game) {
        Game gameInBdd = this.findGameById(gameId);

        Player playerInBdd = playerRepository.findById(game.getPlayer_id_actual()).orElseThrow(() ->
                new IllegalArgumentException("Le joueur actuel avec l'ID : " + game.getPlayer_id_actual() + " n'existe pas dans la bdd"));

        gameInBdd.setName(game.getName());
        gameInBdd.setNb_turn(game.getNb_turn());
        gameInBdd.setPlayer_actual(playerInBdd.getId());

        gameRepository.save(gameInBdd);
    }

    public void deleteGameById(Integer gameId) {
        this.gameRepository.deleteById(gameId);
    }
}
