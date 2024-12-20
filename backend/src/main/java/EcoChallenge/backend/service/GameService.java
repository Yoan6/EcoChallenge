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
        List<Game> gamesInBdd = this.getGames();

        boolean nameExists = gamesInBdd.stream()
                .anyMatch(existingGame -> Objects.equals(existingGame.getName(), game.getName()));
        if (nameExists) {
            throw new IllegalArgumentException("Une partie avec le nom " + game.getName() + " existe déjà.");
        }
        gameRepository.save(game);
    }

    public void setGame(Integer gameId, Game game) {
        Game gameInBdd = this.findGameById(gameId);

        // Check si le joueur actuel changé existe en bdd
        Player playerInBdd = playerRepository.findById(game.getPlayer_id_actual()).orElseThrow(() ->
                new IllegalArgumentException("Le joueur actuel avec l'ID : " + game.getPlayer_id_actual() + " n'existe pas dans la BDD"));

        // Check si le joueur actuel changé existe dans la partie
        boolean playerInGame = gameInBdd.getPlayers().stream()
                .anyMatch(player -> Objects.equals(player.getId(), playerInBdd.getId()));

        if (!playerInGame) {
            throw new IllegalArgumentException("Le joueur avec l'ID : " + game.getPlayer_id_actual() + " ne fait pas partie des joueurs de la partie");
        }

        gameInBdd.setName(game.getName());
        gameInBdd.setNb_turn(game.getNb_turn());
        gameInBdd.setPlayer_actual(playerInBdd.getId());

        gameRepository.save(gameInBdd);
    }

    public void deleteGameById(Integer gameId) {
        Game gameInBdd = gameRepository.findById(gameId).orElseThrow(() ->
                new IllegalArgumentException("Partie non trouvé avec l'ID : " + gameId));
        this.gameRepository.deleteById(gameInBdd.getId());
    }
}
