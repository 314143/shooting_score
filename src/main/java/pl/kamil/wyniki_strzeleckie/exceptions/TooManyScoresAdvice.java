package pl.kamil.wyniki_strzeleckie.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class TooManyScoresAdvice {
    @ResponseBody
    @ExceptionHandler(TooManyScoresException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String TooManyScoresHandler(TooManyScoresException ex) {
        return ex.getMessage();
    }
}
