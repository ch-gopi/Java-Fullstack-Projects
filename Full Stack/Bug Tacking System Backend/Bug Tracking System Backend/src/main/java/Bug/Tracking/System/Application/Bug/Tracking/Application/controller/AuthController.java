package Bug.Tracking.System.Application.Bug.Tracking.Application.controller;


import Bug.Tracking.System.Application.Bug.Tracking.Application.dto.JwtAuthResponse;
import Bug.Tracking.System.Application.Bug.Tracking.Application.dto.LoginDto;
import Bug.Tracking.System.Application.Bug.Tracking.Application.dto.RegisterDto;
<<<<<<< HEAD
import Bug.Tracking.System.Application.Bug.Tracking.Application.service.AuthInterfaceService;
=======
import Bug.Tracking.System.Application.Bug.Tracking.Application.service.AuthService;
>>>>>>> 7597e7e0eba66a899deb947e73815869450259fd
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@AllArgsConstructor
@RestController
@RequestMapping("/api/auth")
<<<<<<< HEAD
public class AuthController extends AbstractController {

    private AuthInterfaceService authService;
=======
public class AuthController {

    private AuthService authService;
>>>>>>> 7597e7e0eba66a899deb947e73815869450259fd


    @PostMapping("/register")
    public ResponseEntity<String> register(@Valid @RequestBody RegisterDto registerDto){
        String response = authService.register(registerDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponse> login(@RequestBody LoginDto loginDto){
        System.out.println("Received login data: " + loginDto);
        JwtAuthResponse jwtAuthResponse = authService.login(loginDto);

        return new ResponseEntity<>(jwtAuthResponse, HttpStatus.OK);
    }

}
