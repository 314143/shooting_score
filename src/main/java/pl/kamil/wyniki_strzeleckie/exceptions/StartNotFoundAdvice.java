package pl.kamil.wyniki_strzeleckie.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class StartNotFoundAdvice {
    @ResponseBody
    @ExceptionHandler(StartNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String startNotFoundHandler(StartNotFoundException ex) {
        return ex.getMessage();
    }
}
