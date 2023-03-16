package pl.kamil.wyniki_strzeleckie.exceptions;

public class WrongAmountScoresException extends RuntimeException {
    public WrongAmountScoresException() {
        super("You are trying to add wrong amount of scores!");
    }
}
