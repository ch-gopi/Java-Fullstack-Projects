package Bug.Tracking.System.Application.Bug.Tracking.Application.controller;


import Bug.Tracking.System.Application.Bug.Tracking.Application.dto.JwtAuthResponse;
import Bug.Tracking.System.Application.Bug.Tracking.Application.dto.LoginDto;
import Bug.Tracking.System.Application.Bug.Tracking.Application.dto.RegisterDto;
import Bug.Tracking.System.Application.Bug.Tracking.Application.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@AllArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private AuthService authService;

    // Build Register REST API
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto){
        String response = authService.register(registerDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // Build Login REST API
    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponse> login(@RequestBody LoginDto loginDto){

        JwtAuthResponse jwtAuthResponse = authService.login(loginDto);

        return new ResponseEntity<>(jwtAuthResponse, HttpStatus.OK);
    }

}
