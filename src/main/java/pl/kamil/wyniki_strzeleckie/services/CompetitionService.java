package pl.kamil.wyniki_strzeleckie.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.kamil.wyniki_strzeleckie.exceptions.CompetitionNotFoundException;
import pl.kamil.wyniki_strzeleckie.exceptions.CompetitorNotFoundException;
import pl.kamil.wyniki_strzeleckie.model.Competition;
import pl.kamil.wyniki_strzeleckie.model.CompetitionsRepository;
import pl.kamil.wyniki_strzeleckie.model.CompetitorsRepository;
import pl.kamil.wyniki_strzeleckie.model.Start;

import java.time.LocalDate;
import java.util.List;

@Service
public class CompetitionService {
    private final CompetitionsRepository repository;
    private final CompetitorsRepository participantRepository;

    public CompetitionService(CompetitionsRepository repository, CompetitorsRepository participantRepository) {
        this.repository = repository;
        this.participantRepository = participantRepository;
    }

    public List<Competition>  getCompetitions() {
        return repository.findAll();
    }

    public Competition getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new CompetitionNotFoundException(id));
    }

    public List<Start> getStartsByCompetitionId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new CompetitionNotFoundException(id))
                .getStarts();
    }

    public Competition save(Competition competition) {
        return repository.save(competition);
    }

    @Transactional
    public Competition addStart(Start newStart, Long competitionId, Long participantId) {
        return repository.findById(competitionId)
                .map(competition -> {
                    newStart.setCompetitor(
                            participantRepository
                                    .findById(participantId)
                                    .orElseThrow(() -> new CompetitorNotFoundException(participantId)));
                    competition.getStarts().add(newStart);
                    return repository.save(competition);
                }).orElseThrow(() -> new CompetitionNotFoundException(competitionId));
    }

    public String updateCompetitionInfo(String name, LocalDate date, Long id) {
        return repository.updateNameAndDateById(name, date, id) == 1 ? "OK" : "ERROR";
    }

}
