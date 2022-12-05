package pl.kamil.wyniki_strzeleckie.exceptions;

public class CompetitionNotFoundException extends RuntimeException {
    public CompetitionNotFoundException(Long id) {
        super("Could not find competition " + id);
    }
}
