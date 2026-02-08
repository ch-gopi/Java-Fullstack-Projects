<<<<<<< HEAD
package userManagementSystemBackend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class Resourcenotfoundexception extends  RuntimeException {
    public Resourcenotfoundexception(String message){
        super((message));
    }
}
=======
package userManagementSystemBackend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class Resourcenotfoundexception extends  RuntimeException {
    public Resourcenotfoundexception(String message){
        super((message));
    }
}
>>>>>>> 7597e7e0eba66a899deb947e73815869450259fd
