package pl.kamil.wyniki_strzeleckie.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;
import org.springframework.scheduling.annotation.Async;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.List;

public interface CompetitorsRepository extends JpaRepository<Competitor, Long> {
    boolean existsByEmail(String email);
    Competitor findByEmail(String email);

    @Transactional
    @Modifying
    @Query("update Competitor c set c.name = ?1, c.licenseNumber = ?2, c.clubName = ?3 where c.id = ?4")
    int updateNameAndLicenseNumberAndClubNameById(String name, String licenseNumber, String clubName, Long id);

    @Transactional
    @Modifying
    @Query("update Competitor c set c.active = ?1 where c.id = ?2")
    int updateActiveById(boolean active, Long id);

}
