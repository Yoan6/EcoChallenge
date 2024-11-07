package EcoChallenge.backend.controller;

import EcoChallenge.backend.entites.Player;
import EcoChallenge.backend.service.PlayerService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.util.List;

@RestController
@RequestMapping(path = "player")
public class PlayerController {
    private final PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public void create(@RequestBody Player player) {
        this.playerService.create(player);
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public List<Player> search() {
        return this.playerService.search();
    }

    @GetMapping(path = "{id}", produces = APPLICATION_JSON_VALUE)
    public Player searchPlayer(@PathVariable Integer id) {
        return this.playerService.searchPlayer(id);
    }
}
