package EcoChallenge.backend.entites;

import jakarta.persistence.*;

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

    @OneToOne
    @JoinColumn(name = "player_id_actual")
    private Player player_actual;

    public Game(){}

    public Game(Integer id, String name, Integer nb_turn, Player player_actual) {
        this.id = id;
        this.name = name;
        this.nb_turn = nb_turn;
        this.player_actual = player_actual;
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

    public Player getPlayer_actual() {
        return player_actual;
    }

    public void setPlayer_actual(Player player_actual) {
        this.player_actual = player_actual;
    }
}
