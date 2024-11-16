package EcoChallenge.backend.entites;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.annotations.DynamicInsert;

import java.math.BigDecimal;

@Entity
@DynamicInsert
@Table(name = "PLAYER")
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "COLOR", nullable = false)
    private String color;

    @Column(name = "HEALTH")
    private Integer health;

    @Column(name = "POLLUTION")
    private Integer pollution;

    @Column(name = "HAPPINESS")
    private Integer happiness;

    @Column(name = "WATER")
    private Integer water;

    @Column(name = "ELECTRICITY")
    private Integer electricity;

    @Column(name = "MONEY")
    private BigDecimal money;

    @Column(name = "CITY_STATE")
    private String city_state;

    @ManyToOne
    @JoinColumn(name = "GAME_ID")
    @JsonIgnore
    private Game game;

    public Player() {}

    public Player(String name, String color) {
        this.name = name;
        this.color = color;
    }

    public Player(String name, String color, Integer health, Integer pollution, Integer happiness, Integer water, Integer electricity, BigDecimal money, String city_state) {
        this.name = name;
        this.color = color;
        this.health = health;
        this.pollution = pollution;
        this.happiness = happiness;
        this.water = water;
        this.electricity = electricity;
        this.money = money;
        this.city_state = city_state;
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

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public Integer getPollution() {
        return pollution;
    }

    public void setPollution(int pollution) {
        this.pollution = pollution;
    }

    public Integer getHappiness() {
        return happiness;
    }

    public void setHappiness(int happiness) {
        this.happiness = happiness;
    }

    public Integer getWater() {
        return water;
    }

    public void setWater(int water) {
        this.water = water;
    }

    public Integer getElectricity() {
        return electricity;
    }

    public void setElectricity(int electricity) {
        this.electricity = electricity;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public String getCity_state() {
        return city_state;
    }

    public void setCity_state(String city_state) {
        this.city_state = city_state;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }
}
