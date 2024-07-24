package marcos.stockcontrol.domain.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.NoSuchElementException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> handlerNoElementException(NoSuchElementException elementException) {
        return new ResponseEntity<>("Resource Not Found.", HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handlerNoElementException(IllegalArgumentException elementException) {
        return new ResponseEntity<>(elementException.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(ProductException.class)
    public ResponseEntity<String> handleResourceProductException(ProductException exception) {
        return new ResponseEntity<>("Product Quantity is out of range.", HttpStatus.NOT_FOUND);
    }
}
