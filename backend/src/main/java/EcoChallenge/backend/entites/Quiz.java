package EcoChallenge.backend.entites;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "quiz")
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "question")
    private String question;

    @Column(name = "answers")
    private List<String> answers;

    @Column(name = "correct_answer")
    private String correct_answer;

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

    public Quiz() {}

    public Quiz(Integer id, String question, List<String> answers, String correct_answer, Integer health_impact, Integer pollution_impact, Integer happiness_impact, Integer water_impact, Integer electricity_impact, float money_impact) {
        this.id = id;
        this.question = question;
        this.answers = answers;
        this.correct_answer = correct_answer;
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

    public String getQuestion() {
        return question;
    }

    public List<String> getAnswers() {
        return answers;
    }

    public String getCorrect_answer() {
        return correct_answer;
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
