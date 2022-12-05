package pl.kamil.wyniki_strzeleckie.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.*;

@Entity
@Table(name = "competitors")
public class Competitor {

    private @Id @GeneratedValue Long id;
    private String name;
    private String licenseNumber;
    private String clubName;
    @OneToMany(cascade = CascadeType.ALL)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<Start> starts = new ArrayList<>();

    public Competitor() {}

    public Competitor(String name, String role) {

        this.name = name;
        this.licenseNumber = role;
    }

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getLicenseNumber() {
        return this.licenseNumber;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLicenseNumber(String role) {
        this.licenseNumber = role;
    }

    public String getClubName() {
        return clubName;
    }

    public void setClubName(String clubName) {
        this.clubName = clubName;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o)
            return true;
        if (!(o instanceof Competitor))
            return false;
        Competitor employee = (Competitor) o;
        return Objects.equals(this.id, employee.id) && Objects.equals(this.name, employee.name)
                && Objects.equals(this.licenseNumber, employee.licenseNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.name, this.licenseNumber);
    }

    @Override
    public String toString() {
        return "Employee{" + "id=" + this.id + ", name='" + this.name + '\'' + ", role='" + this.licenseNumber + '\'' + '}';
    }
}
