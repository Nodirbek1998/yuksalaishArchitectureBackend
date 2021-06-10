package uz.cas.controllersestem.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class RoomsException extends RuntimeException {
    public RoomsException(String message) {
        super(message);
    }
}
