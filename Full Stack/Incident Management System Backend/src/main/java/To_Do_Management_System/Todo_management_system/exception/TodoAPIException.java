package To_Do_Management_System.Todo_management_system.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public class TodoAPIException extends RuntimeException{
    private HttpStatus status;
    private String message;
}
