package pl.kamil.wyniki_strzeleckie.model;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CompetitorsRepository extends JpaRepository<Competitor, Long> {
}
