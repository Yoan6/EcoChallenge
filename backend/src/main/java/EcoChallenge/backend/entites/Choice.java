package EcoChallenge.backend.entites;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "CHOICE")
public class Choice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "EVENT_ID")
    @JsonIgnore
    private Event event;

    @Column(name = "health_impact")
    private Integer health_impact;

    @Column(name = "pollution_impact")
    private Integer pollution_impact;

    @Column(name = "happiness_impact")
    private Integer happiness_impact;

    @Column(name = "water_impact")
    private Integer water_impact;

    @Column(name = "electricity_impact")
    private Integer electricity_impact;

    @Column(name = "money_impact")
    private float money_impact;

    public Choice() {}

    public Choice(Integer id, String name, String description, Integer health_impact, Integer pollution_impact, Integer happiness_impact, Integer water_impact, Integer electricity_impact, float money_impact) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.health_impact = health_impact;
        this.pollution_impact = pollution_impact;
        this.happiness_impact = happiness_impact;
        this.water_impact = water_impact;
        this.electricity_impact = electricity_impact;
        this.money_impact = money_impact;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Event getEvent() {
        return event;
    }

    public Integer getHealth_impact() {
        return health_impact;
    }

    public Integer getPollution_impact() {
        return pollution_impact;
    }

    public Integer getHappiness_impact() {
        return happiness_impact;
    }

    public Integer getWater_impact() {
        return water_impact;
    }

    public Integer getElectricity_impact() {
        return electricity_impact;
    }

    public float getMoney_impact() {
        return money_impact;
    }
}
