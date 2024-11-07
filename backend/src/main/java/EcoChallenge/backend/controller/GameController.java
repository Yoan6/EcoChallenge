package EcoChallenge.backend.controller;

import EcoChallenge.backend.entites.Game;
import EcoChallenge.backend.entites.Player;
import EcoChallenge.backend.service.GameService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(path = "game")
public class GameController {
    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public List<Game> search() {
        return this.gameService.search();
    }
}
