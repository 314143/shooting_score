package pl.kamil.wyniki_strzeleckie.controllers;

import org.springframework.web.bind.annotation.*;
import pl.kamil.wyniki_strzeleckie.exceptions.StartNotFoundException;
import pl.kamil.wyniki_strzeleckie.model.Start;
import pl.kamil.wyniki_strzeleckie.model.StartRepository;
import pl.kamil.wyniki_strzeleckie.services.StartService;

import java.util.ArrayList;
import java.util.List;

@RestController
public class StartController {
    private final StartService startService;

    public StartController(StartService startService) {
        this.startService = startService;
    }

    @PutMapping("/starts/{startId}")
    Start setScores(@RequestBody ArrayList<Integer> scores, @PathVariable Long startId) {
        return startService.setScores(scores, startId);
    }

    @DeleteMapping("/starts/{startId}")
    void deleteStart(@PathVariable Long startId) {
        startService.deleteById(startId);
    }


}
