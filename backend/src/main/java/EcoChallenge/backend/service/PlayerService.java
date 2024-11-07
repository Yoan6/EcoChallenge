package EcoChallenge.backend.service;

import EcoChallenge.backend.entites.Player;
import EcoChallenge.backend.repository.PlayerRepository;
import org.springframework.stereotype.Service;
import EcoChallenge.backend.entites.CityState;

import java.util.List;
import java.util.Optional;

@Service
public class PlayerService {

    private final PlayerRepository playerRepository;

    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public void create(Player player) {
        if (player.getId() != null) {
            throw new IllegalArgumentException("L'id ne doit pas être null quand on crée un joueur");
        }
        try {
            CityState.valueOf(player.getCity_state());  // Vérifie si cityState est parmi {healthy, degraded, bad, dying, dead}
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("État de ville non-valide : " + player.getCity_state());
        }
        playerRepository.save(player);
    }


    public List<Player> search() {
        return this.playerRepository.findAll();
    }

    public Player searchPlayers(Integer id) {
        Optional<Player> optionnalPlayer = this.playerRepository.findById(id);
        return optionnalPlayer.orElse(null);
    }
}
