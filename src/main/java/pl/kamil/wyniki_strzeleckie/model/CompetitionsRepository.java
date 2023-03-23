package pl.kamil.wyniki_strzeleckie.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Date;

public interface CompetitionsRepository extends JpaRepository<Competition, Long> {
    @Transactional
    @Modifying
    @Query("update Competition c set c.name = ?1, c.date = ?2 where c.id = ?3")
    int updateNameAndDateById(String name, LocalDate date, Long id);

}
