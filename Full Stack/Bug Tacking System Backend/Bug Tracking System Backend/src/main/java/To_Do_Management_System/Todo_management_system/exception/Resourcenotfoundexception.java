package To_Do_Management_System.Todo_management_system.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
@ResponseStatus( value = HttpStatus.NOT_FOUND)
public class Resourcenotfoundexception extends RuntimeException{
    public Resourcenotfoundexception(String message) {
        super(message);
    }
}
