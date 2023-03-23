package pl.kamil.wyniki_strzeleckie.model;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "competitions")
@Getter
@Setter
@ToString
public class Competition {
    private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;
    private String name;
    private LocalDate date;
    @OneToMany(cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<Start> starts = new ArrayList<>();

    public Competition(){}

    public void addStart(Start newStart) {
        this.starts.add(newStart);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Competition that = (Competition) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
