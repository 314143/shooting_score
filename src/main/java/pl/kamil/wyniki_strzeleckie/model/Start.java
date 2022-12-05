package pl.kamil.wyniki_strzeleckie.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "starts")
public class Start {
    private @Id @Column(name = "start_id") @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;
    private StartType type;
    private Long shotsAmount;
    @ManyToOne
    private Competitor competitor;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Score> scores = new ArrayList<>();
    @ManyToOne
    @JoinColumn(name = "competition_id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Competition competition;


    public Start() {
    }

    public Start(Start newStart) {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public StartType getType() {
        return type;
    }

    public void setType(StartType type) {
        this.type = type;
    }

    public Long getShotsAmount() {
        return shotsAmount;
    }

    public void setShotsAmount(Long shotsAmount) {
        this.shotsAmount = shotsAmount;
    }

    public Competitor getCompetitor() {
        return competitor;
    }

    public void setCompetitor(Competitor competitor) {
        this.competitor = competitor;
    }

    public void setScores(List<Score> scores) {
        this.scores = scores;
    }

    public Competition getCompetition() {
        return competition;
    }

    public void setCompetition(Competition competition) {
        this.competition = competition;
    }

    public boolean addScore(ArrayList<Integer> scores) {    //TODO: Usuwanie starych wyników
        if (scores.size() <= this.shotsAmount) {
            ArrayList<Score> newScores = new ArrayList<>();
            for (int score :
                    scores) {
                newScores.add(new Score(score));
            }
            this.scores = newScores;
        }
        else
            return false;
        return true;
    }

    public List<Score> getScores() {
        return scores;
    }

}
