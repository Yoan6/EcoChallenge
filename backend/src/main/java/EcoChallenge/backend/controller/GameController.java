package EcoChallenge.backend.controller;

import EcoChallenge.backend.entites.Game;
import EcoChallenge.backend.service.GameService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(path = "games")
public class GameController {
    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, Object>> getGames() {
        List<Game> games = this.gameService.getGames();
        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        response.put("games", games);
        return ResponseEntity.ok(response);
    }

    @GetMapping(path = "{gameId}")
    public ResponseEntity<Map<String, Object>> getGame(@PathVariable Integer gameId) {
        Map<String, Object> response = new HashMap<>();
        try {
            Game gameInBdd = gameService.findGameById(gameId);
            response.put("status", "success");
            response.put("game", gameInBdd);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            response.put("status", "error");
            response.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, Object>> createGame(@RequestBody Game game) {
        Map<String, Object> response = new HashMap<>();
        try {
            gameService.createGame(game);
            response.put("status", "success");
            response.put("message", "Partie créée avec succès !");
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (IllegalArgumentException e) {
            response.put("status", "error");
            response.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PutMapping(path = "{gameId}", consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, Object>> setGame(@PathVariable Integer gameId, @RequestBody Game game) {
        Map<String, Object> response = new HashMap<>();
        try {
            gameService.setGame(gameId, game);
            response.put("status", "success");
            response.put("message", "Partie modifiée avec succès !");
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (IllegalArgumentException e) {
            response.put("status", "error");
            response.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @DeleteMapping(path = "{gameId}")
    public ResponseEntity<Map<String, Object>> deleteGame(@PathVariable Integer gameId) {
        Map<String, Object> response = new HashMap<>();
        try {
            this.gameService.deleteGameById(gameId);
            response.put("status", "success");
            response.put("message", "Partie supprimée avec succès !");
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
        } catch (IllegalArgumentException e) {
            response.put("status", "error");
            response.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }
}
