package EcoChallenge.backend.service;

import EcoChallenge.backend.entites.Game;
import EcoChallenge.backend.entites.Player;
import EcoChallenge.backend.repository.GameRepository;
import EcoChallenge.backend.repository.PlayerRepository;
import org.springframework.stereotype.Service;
import EcoChallenge.backend.entites.CityState;

import java.util.List;
import java.util.Optional;

@Service
public class PlayerService {

    private final PlayerRepository playerRepository;
    private final GameRepository gameRepository;

    public PlayerService(PlayerRepository playerRepository, GameRepository gameRepository) {
        this.playerRepository = playerRepository;
        this.gameRepository = gameRepository;
    }

    public void addPlayer(Player player, Integer gameId) {
        if (player.getId() != null) {
            throw new IllegalArgumentException("L'id doit être null quand on crée un joueur");
        }

        // Vérifie si cityState est parmi {healthy, degraded, bad, dying, dead}
        try {
            CityState.valueOf(player.getCity_state());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("État de ville non-valide : " + player.getCity_state());
        }

        // Vérifie l'unicité de la couleur dans la même Game
        Game game = gameRepository.findById(gameId).orElseThrow(() ->
                new IllegalArgumentException("Partie non trouvé avec l'ID : " + gameId));

        boolean colorExists = game.getPlayers().stream()
                .anyMatch(existingPlayer -> existingPlayer.getColor().equals(player.getColor()));

        if (colorExists) {
            throw new IllegalArgumentException("Un joueur avec la couleur " + player.getColor() + " existe déjà dans cette partie.");
        }

        // Associe le joueur à la game
        game.addPlayer(player);

        playerRepository.save(player);
        gameRepository.save(game);
    }


    public List<Player> search() {
        return this.playerRepository.findAll();
    }

    public Player searchPlayer(Integer playerId) {
        Optional<Player> optionnalPlayer = this.playerRepository.findById(playerId);

        if (optionnalPlayer.isEmpty()) {
            throw new IllegalArgumentException("Joueur non trouvée avec l'ID : " + playerId);
        }

        return optionnalPlayer.orElse(null);
    }

    public void setPlayer(Integer playerId, Player player) {
        Player playerInBdd = searchPlayer(playerId);

        playerInBdd.setName(player.getName());
        playerInBdd.setColor(player.getColor());
        playerInBdd.setHealth(player.getHealth());
        playerInBdd.setPollution(player.getPollution());
        playerInBdd.setHappiness(player.getHappiness());
        playerInBdd.setWater(player.getWater());
        playerInBdd.setElectricity(player.getElectricity());
        playerInBdd.setMoney(player.getMoney());

        // Vérifie si cityState est parmi {healthy, degraded, bad, dying, dead}
        try {
            CityState.valueOf(player.getCity_state());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("État de ville non-valide : " + player.getCity_state());
        }

        playerInBdd.setCity_state(player.getCity_state());

        playerRepository.save(playerInBdd);
    }

    public void deletePlayerById(Integer playerId) {
        Player playerInBdd = playerRepository.findById(playerId).orElseThrow(() ->
                new IllegalArgumentException("Joueur non trouvé avec l'ID : " + playerId));
        this.playerRepository.deleteById(playerInBdd.getId());
    }
}
