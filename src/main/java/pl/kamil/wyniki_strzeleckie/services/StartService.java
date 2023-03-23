package pl.kamil.wyniki_strzeleckie.services;

import org.springframework.stereotype.Service;
import pl.kamil.wyniki_strzeleckie.exceptions.StartNotFoundException;
import pl.kamil.wyniki_strzeleckie.model.Start;
import pl.kamil.wyniki_strzeleckie.model.StartRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class StartService {
    private final StartRepository repository;

    public StartService(StartRepository repository) {
        this.repository = repository;
    }

    public Start setScores(ArrayList<Integer> scores, Long startId) {
        return repository.findById(startId)
                .map(start -> {
                    start.addScore(scores);
                    return repository.save(start);
                }).orElseThrow(() -> new StartNotFoundException(startId));
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public List<Start> getStartsByCompetitorId(Long competitorId) {
        return repository.findByCompetitor_Id(competitorId);
    }
}
