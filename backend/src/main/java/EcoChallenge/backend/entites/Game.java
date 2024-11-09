package EcoChallenge.backend.entites;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "GAME")
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "NB_TURN")
    private Integer nb_turn;

    @Column(name = "PLAYER_ID_ACTUAL")
    private Integer player_id_actual;

    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Player> players = new ArrayList<>();

    public Game(){}

    public Game(Integer id, String name, Integer nb_turn) {
        this.id = id;
        this.name = name;
        this.nb_turn = nb_turn;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNb_turn() {
        return nb_turn;
    }

    public void setNb_turn(Integer nb_turn) {
        this.nb_turn = nb_turn;
    }

    public Integer getPlayer_id_actual() {
        return player_id_actual;
    }

    public void setPlayer_actual(Integer player_id_actual) {
        this.player_id_actual = player_id_actual;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void addPlayer(Player player) {
        players.add(player);
        player.setGame(this);
    }
}
