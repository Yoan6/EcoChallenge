package EcoChallenge.backend.controller;

import EcoChallenge.backend.entites.Game;
import EcoChallenge.backend.entites.Player;
import EcoChallenge.backend.service.GameService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(path = "games")
public class GameController {
    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public List<Game> getGames() {
        return this.gameService.getGames();
    }

    @GetMapping("/{gameId}")
    public Game getGame(@PathVariable Integer gameId) {
        return gameService.findGameById(gameId);
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<String> createGame(@RequestBody Game game) {
        try {
            gameService.createGame(game);
            return ResponseEntity.status(HttpStatus.CREATED).body("Partie créé avec succès !");
        }
        catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
