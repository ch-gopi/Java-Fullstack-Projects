package Bug.Tracking.System.Application.Bug.Tracking.Application.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public class BugAPIException extends RuntimeException{
    private HttpStatus status;
    private String message;
}
