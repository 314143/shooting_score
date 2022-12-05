package pl.kamil.wyniki_strzeleckie.controllers;

import org.springframework.web.bind.annotation.*;
import pl.kamil.wyniki_strzeleckie.exceptions.CompetitionNotFoundException;
import pl.kamil.wyniki_strzeleckie.exceptions.CompetitorNotFoundException;
import pl.kamil.wyniki_strzeleckie.exceptions.StartNotFoundException;
import pl.kamil.wyniki_strzeleckie.exceptions.TooManyScoresException;
import pl.kamil.wyniki_strzeleckie.model.Start;
import pl.kamil.wyniki_strzeleckie.model.StartRepository;

import java.util.ArrayList;

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
                    if (!start.addScore(scores))
                        throw new TooManyScoresException();
                    return startRepository.save(start);
                }).orElseThrow(() -> new StartNotFoundException(startId));
    }

    @DeleteMapping("/starts/{startId}")
    void deleteStart(@PathVariable Long startId) {
        startRepository.deleteById(startId);
    }
}
