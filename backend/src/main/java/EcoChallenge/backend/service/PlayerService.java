package EcoChallenge.backend.service;

import EcoChallenge.backend.entites.Player;
import EcoChallenge.backend.repository.PlayerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlayerService {

    private PlayerRepository playerRepository;

    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public void create(Player player) {
        Optional<Player> playerInBDD = this.playerRepository.findById(player.getId());
        if (playerInBDD.isEmpty()) {
            this.playerRepository.save(player);
        }
    }

    public List<Player> search() {
        return this.playerRepository.findAll();
    }

    public Player searchPlayer(Integer id) {
        Optional<Player> optionnalPlayer = this.playerRepository.findById(id);
        return optionnalPlayer.orElse(null);
    }
}
