package pl.kamil.wyniki_strzeleckie.controllers;

import org.springframework.web.bind.annotation.*;
import pl.kamil.wyniki_strzeleckie.model.*;
import pl.kamil.wyniki_strzeleckie.services.CompetitorService;
import pl.kamil.wyniki_strzeleckie.services.StartService;

import javax.validation.Valid;
import java.util.List;

@RestController
public class CompetitorsController {
    private final CompetitorService competitorService;
    private final StartService startService;

    CompetitorsController(CompetitorsRepository repository, CompetitorService competitorService, StartRepository startRepository, StartService startService) {
        this.competitorService = competitorService;
        this.startService = startService;
    }

    @GetMapping("/competitors")
    List<Competitor> all() {
        return competitorService.getCompetitors();
    }

    @PostMapping("/competitors")
    Competitor newCompetitor(@RequestBody Competitor newCompetitor) {
        return competitorService.saveCompetitor(newCompetitor);
    }

    @PostMapping("/competitors/register")
    Competitor registerNewCompetitor(@RequestBody @Valid CompetitorDTO newCompetitor) {
        return competitorService.registerNewUser(newCompetitor);
    }

    @DeleteMapping("/competitors/{id}")
    void deleteCompetitor(@PathVariable Long id) {
        competitorService.deleteCompetitor(id);
    }

    @PatchMapping("/competitors/{id}")
    int updateCompetitor(@PathVariable Long id,
                                @RequestParam String name,
                                @RequestParam String licenseNumber,
                                @RequestParam String clubName) {
        return competitorService.updateCompetitor(name, licenseNumber, clubName, id);
    }

    @GetMapping("/competitors/{competitorId}/starts") //TODO: Do przerobienia
    List<Start> getStartsByCompetitorId(@PathVariable Long competitorId) {
        return startService.getStartsByCompetitorId(competitorId);
    }

}
