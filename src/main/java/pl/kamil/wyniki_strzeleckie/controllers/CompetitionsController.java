package pl.kamil.wyniki_strzeleckie.controllers;

import org.springframework.web.bind.annotation.*;
import pl.kamil.wyniki_strzeleckie.exceptions.CompetitorNotFoundException;
import pl.kamil.wyniki_strzeleckie.model.*;
import pl.kamil.wyniki_strzeleckie.exceptions.CompetitionNotFoundException;
import pl.kamil.wyniki_strzeleckie.services.CompetitionService;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@RestController
public class CompetitionsController {

    private final CompetitionService competitionService;

    CompetitionsController(CompetitionService competitionService) {
        this.competitionService = competitionService;
    }
    @GetMapping("/competitions")
    List<Competition> all() {
        return competitionService.getCompetitions();
    }

    @PostMapping("/competitions")
    Competition newCompetition(@RequestBody Competition newCompetition) {
        return competitionService.save(newCompetition);
    }

    @GetMapping("/competitions/{id}")
    Competition one(@PathVariable Long id) {
        return competitionService.getById(id);
    }

    @PostMapping("/competitions/{competitionId}/addstart/{participantId}")
    Competition newStart(
            @RequestBody Start newStart,
            @PathVariable Long competitionId,
            @PathVariable Long participantId
    ) {
        return competitionService.addStart(newStart, competitionId, participantId);
    }

    @PatchMapping("/competitions/{id}")
    String updateCompetition(@RequestParam String name, @RequestParam String dateString, @PathVariable Long id) {
        return competitionService.updateCompetitionInfo(name, LocalDate.parse(dateString), id);
    }

}
