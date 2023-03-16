package pl.kamil.wyniki_strzeleckie.controllers;

import org.springframework.web.bind.annotation.*;
import pl.kamil.wyniki_strzeleckie.exceptions.StartNotFoundException;
import pl.kamil.wyniki_strzeleckie.exceptions.WrongAmountScoresException;
import pl.kamil.wyniki_strzeleckie.model.Start;
import pl.kamil.wyniki_strzeleckie.model.StartRepository;

import java.util.ArrayList;
import java.util.List;

@RestController
public class StartController {
    private final StartRepository startRepository;

    public StartController(StartRepository startRepository) {
        this.startRepository = startRepository;
    }

    @PutMapping("/starts/{startId}")
    Start setScores(@RequestBody ArrayList<Integer> scores, @PathVariable Long startId) {
        return startRepository.findById(startId)
                .map(start -> {
                    start.addScore(scores);
                    return startRepository.save(start);
                }).orElseThrow(() -> new StartNotFoundException(startId));
    }

    @DeleteMapping("/starts/{startId}")
    void deleteStart(@PathVariable Long startId) {
        startRepository.deleteById(startId);
    }

    @GetMapping("/competitors/{id}/starts")
    List<Start> getStartsByCompetitorId(@PathVariable Long id) {
        return startRepository.findByCompetitor_Id(id);
    }

}
