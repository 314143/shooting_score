package pl.kamil.wyniki_strzeleckie.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Iterator;

@ControllerAdvice
public class NotValidAdvice {
    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String notValidHandler(MethodArgumentNotValidException ex) {
        StringBuilder sb = new StringBuilder("Errors occurred:\n");

        for (ObjectError error : ex.getBindingResult().getAllErrors()) {
            sb.append(error.getDefaultMessage()).append("\n");
        }

        return sb.toString();
    }

}
