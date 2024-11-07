package EcoChallenge.backend.service;

import EcoChallenge.backend.entites.Game;
import EcoChallenge.backend.repository.GameRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameService {
    private final GameRepository gameRepository;

    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public List<Game> search() {
        return this.gameRepository.findAll();
    }
}
