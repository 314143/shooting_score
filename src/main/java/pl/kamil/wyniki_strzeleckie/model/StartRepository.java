package pl.kamil.wyniki_strzeleckie.model;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StartRepository extends JpaRepository<Start, Long> {
    List<Start> findByCompetitor_Id(Long competitor_id);
}
