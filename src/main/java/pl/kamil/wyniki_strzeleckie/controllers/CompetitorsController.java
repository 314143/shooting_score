package pl.kamil.wyniki_strzeleckie.controllers;

import org.springframework.web.bind.annotation.*;
import pl.kamil.wyniki_strzeleckie.model.Competitor;
import pl.kamil.wyniki_strzeleckie.model.CompetitorsRepository;
import pl.kamil.wyniki_strzeleckie.model.Start;
import pl.kamil.wyniki_strzeleckie.model.StartRepository;

import java.util.List;

@RestController
public class CompetitorsController {
    private final CompetitorsRepository repository;
    private final StartRepository startRepository;

    CompetitorsController(CompetitorsRepository repository, StartRepository startRepository) {
        this.repository = repository;
        this.startRepository = startRepository;
    }

    @GetMapping("/competitors")
    List<Competitor> all() {
        return repository.findAll();
    }

    @PostMapping("/competitors")
    Competitor newCompetitor(@RequestBody Competitor newCompetitor) {
        return repository.save(newCompetitor);
    }

    @DeleteMapping("/competitors/{id}")
    void deleteCompetitor(@PathVariable Long id) {
        startRepository.findByCompetitor_Id(id).forEach(start -> startRepository.deleteById(start.getId()));
        repository.deleteById(id);
    }
}
