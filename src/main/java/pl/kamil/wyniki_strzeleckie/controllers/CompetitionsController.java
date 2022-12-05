package pl.kamil.wyniki_strzeleckie.controllers;

import org.springframework.web.bind.annotation.*;
import pl.kamil.wyniki_strzeleckie.exceptions.CompetitorNotFoundException;
import pl.kamil.wyniki_strzeleckie.model.*;
import pl.kamil.wyniki_strzeleckie.exceptions.CompetitionNotFoundException;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CompetitionsController {

    private final CompetitionsRepository repository;
    private final CompetitorsRepository participantRepository;

    CompetitionsController(CompetitionsRepository repository, CompetitorsRepository participantRepository) {
        this.repository = repository;
        this.participantRepository = participantRepository;
    }
    @GetMapping("/competitions")
    List<Competition> all() {
        return repository.findAll();
    }

    @PostMapping("/competitions")
    Competition newCompetition(@RequestBody Competition newCompetition) {
        return repository.save(newCompetition);
    }

    @GetMapping("/competitions/{id}")
    Competition one(@PathVariable Long id) {

        return repository.findById(id)
                .orElseThrow(() -> new CompetitionNotFoundException(id));
    }

    @PostMapping("/competitions/{id}/{id2}")
    Competition newStart(@RequestBody Start newStart, @PathVariable Long id, @PathVariable Long id2) {
        return repository.findById(id)
                .map(competition -> {
                    newStart.setCompetitor(
                            participantRepository
                                    .findById(id2)
                                    .orElseThrow(() -> new CompetitorNotFoundException(id2)));
                    competition.getStarts().add(newStart);
                    return repository.save(competition);
                }).orElseThrow(() -> new CompetitionNotFoundException(id));
    }

}
