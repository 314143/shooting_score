package pl.kamil.wyniki_strzeleckie.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.List;

public interface CompetitorsRepository extends JpaRepository<Competitor, Long> {

}
