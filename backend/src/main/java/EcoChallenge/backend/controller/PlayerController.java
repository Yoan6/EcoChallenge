package EcoChallenge.backend.controller;

import EcoChallenge.backend.entites.Player;
import EcoChallenge.backend.service.PlayerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.util.List;

@RestController
@RequestMapping(path = "players")
public class PlayerController {
    private final PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public List<Player> search() {
        return this.playerService.search();
    }

    @GetMapping(path = "{playerId}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<?> searchPlayer(@PathVariable Integer playerId) {
        try {
            Player playerInBdd = playerService.searchPlayer(playerId);
            return ResponseEntity.ok(playerInBdd);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping(path = "game/{gameId}", consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addPlayer(@PathVariable Integer gameId, @RequestBody Player player) {
        try {
            playerService.addPlayer(player, gameId);
            return ResponseEntity.status(HttpStatus.CREATED).body("Joueur créé avec succès !");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping(path = "{playerId}", consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<String> setPlayer(@PathVariable Integer playerId, @RequestBody Player player) {
        try {
            playerService.setPlayer(playerId, player);
            return ResponseEntity.status(HttpStatus.CREATED).body("Joueur modifiée avec succès !");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping(path = "{playerId}")
    public ResponseEntity<String> deletePlayer(@PathVariable Integer playerId) {
        try {
            this.playerService.deletePlayerById(playerId);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Joueur supprimé avec succès !");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
