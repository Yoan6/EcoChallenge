package EcoChallenge.backend.service;

import EcoChallenge.backend.entites.Game;
import EcoChallenge.backend.entites.Player;
import EcoChallenge.backend.repository.GameRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GameService {
    private final GameRepository gameRepository;

    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public List<Game> getGames() {
        return this.gameRepository.findAll();
    }

    public Game findGameById(Integer gameId) {
        Optional<Game> optionalGame = this.gameRepository.findById(gameId);
        return optionalGame.orElse(null);
    }
}
