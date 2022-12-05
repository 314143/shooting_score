package pl.kamil.wyniki_strzeleckie.exceptions;

public class StartNotFoundException extends RuntimeException {
    public StartNotFoundException(Long id) {
        super("Could not find start " + id);
    }
}
