package EcoChallenge.backend.controller;

import EcoChallenge.backend.entites.Player;
import EcoChallenge.backend.service.PlayerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "players")
public class PlayerController {
    private final PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, Object>> search() {
        List<Player> players = this.playerService.search();
        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        response.put("players", players);
        return ResponseEntity.ok(response);
    }

    @GetMapping(path = "{playerId}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, Object>> searchPlayer(@PathVariable Integer playerId) {
        Map<String, Object> response = new HashMap<>();
        try {
            Player playerInBdd = playerService.searchPlayer(playerId);
            response.put("status", "success");
            response.put("player", playerInBdd);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            response.put("status", "error");
            response.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    @PostMapping(path = "game/{gameId}", consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, Object>> addPlayer(@PathVariable Integer gameId, @RequestBody Player player) {
        Map<String, Object> response = new HashMap<>();
        try {
            playerService.addPlayer(player, gameId);
            response.put("status", "success");
            response.put("message", "Joueur créé avec succès !");
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (IllegalArgumentException e) {
            response.put("status", "error");
            response.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PutMapping(path = "{playerId}", consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, Object>> setPlayer(@PathVariable Integer playerId, @RequestBody Player player) {
        Map<String, Object> response = new HashMap<>();
        try {
            playerService.setPlayer(playerId, player);
            response.put("status", "success");
            response.put("message", "Joueur modifié avec succès !");
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (IllegalArgumentException e) {
            response.put("status", "error");
            response.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @DeleteMapping(path = "{playerId}")
    public ResponseEntity<Map<String, Object>> deletePlayer(@PathVariable Integer playerId) {
        Map<String, Object> response = new HashMap<>();
        try {
            response.put("status", "success");
            response.put("message", "Joueur supprimé avec succès !");
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
        } catch (IllegalArgumentException e) {
            response.put("status", "error");
            response.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }
}
