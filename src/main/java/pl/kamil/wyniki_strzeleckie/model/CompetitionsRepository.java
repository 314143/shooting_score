package pl.kamil.wyniki_strzeleckie.model;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CompetitionsRepository extends JpaRepository<Competition, Long> {

}
