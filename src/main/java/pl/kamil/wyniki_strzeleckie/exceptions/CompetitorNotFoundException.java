package pl.kamil.wyniki_strzeleckie.exceptions;

public class CompetitorNotFoundException extends RuntimeException {
    public CompetitorNotFoundException(Long id) {
        super("Could not find participant " + id);
    }
}
