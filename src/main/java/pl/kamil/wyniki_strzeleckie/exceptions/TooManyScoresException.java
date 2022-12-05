package pl.kamil.wyniki_strzeleckie.exceptions;

public class TooManyScoresException extends RuntimeException {
    public TooManyScoresException() {
        super("You are trying to add too many scores!");
    }
}
