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

    @PostMapping(path = "{gameId}", consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addPlayer(@PathVariable Integer gameId, @RequestBody Player player) {
        try {
            playerService.addPlayer(player, gameId);
            return ResponseEntity.status(HttpStatus.CREATED).body("Joueur créé avec succès !");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public List<Player> search() {
        return this.playerService.search();
    }

    @GetMapping(path = "{id}", produces = APPLICATION_JSON_VALUE)
    public Player searchPlayer(@PathVariable Integer id) {
        return this.playerService.searchPlayers(id);
    }
}
