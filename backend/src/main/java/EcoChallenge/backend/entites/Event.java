package EcoChallenge.backend.entites;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "EVENT")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "RISK_CATEGORY")
    private String risk_category;

    @Column(name = "HEALTH_IMPACT")
    private Integer health_impact;

    @Column(name = "POLLUTION_IMPACT")
    private Integer pollution_impact;

    @Column(name = "HAPPINESS_IMPACT")
    private Integer happiness_impact;

    @Column(name = "WATER_IMPACT")
    private Integer water_impact;

    @Column(name = "ELECTRICITY_IMPACT")
    private Integer electricity_impact;

    @Column(name = "MONEY_IMPACT")
    private float money_impact;

    @OneToMany(mappedBy = "event")
    private List<Choice> choices = new ArrayList<>();

    public Event(){}

    public Event(Integer id, String name, String description, String risk_category, Integer health_impact, Integer pollution_impact, Integer happiness_impact, Integer water_impact, Integer electricity_impact, float money_impact) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.risk_category = risk_category;
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

    public String getRisk_category() {
        return risk_category;
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

    public List<Choice> getChoices() {
        return choices;
    }
}
