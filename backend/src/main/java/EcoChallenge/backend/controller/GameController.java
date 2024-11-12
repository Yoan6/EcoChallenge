package EcoChallenge.backend.controller;

import EcoChallenge.backend.entites.Game;
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

    @GetMapping(path = "{gameId}")
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

    @PutMapping(path = "{gameId}", consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<String> setGame(@PathVariable Integer gameId, @RequestBody Game game) {
        try {
            gameService.setGame(gameId, game);
            return ResponseEntity.status(HttpStatus.CREATED).body("Partie modifiée avec succès !");
        }
        catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping(path = "{gameId}")
    public ResponseEntity<String> deleteGame(@PathVariable Integer gameId) {
        try {
            this.gameService.deleteGameById(gameId);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Partie supprimée avec succès !");
        }
        catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
