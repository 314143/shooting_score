package pl.kamil.wyniki_strzeleckie.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.hibernate.Hibernate;
import pl.kamil.wyniki_strzeleckie.exceptions.WrongAmountScoresException;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "starts")
@Getter
@Setter
@ToString
public class Start {
    private @Id @Column(name = "start_id") @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;
    private StartType type;
    private Long shotsAmount;
    @ManyToOne
    private Competitor competitor;
    @ElementCollection
    @ToString.Exclude
    private List<Integer> scores = new ArrayList<>();
    @ManyToOne
    @JoinColumn(name = "competition_id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Competition competition;


    public Start() {
    }

    public void addScore(ArrayList<Integer> scores) {
        if (scores.size() == this.shotsAmount) {
            this.scores.clear();
            this.scores = scores;
        }
        else
            throw new WrongAmountScoresException();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Start start = (Start) o;
        return getId() != null && Objects.equals(getId(), start.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
