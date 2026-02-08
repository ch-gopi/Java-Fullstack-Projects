package Bug.Tracking.System.Application.Bug.Tracking.Application.service;


import Bug.Tracking.System.Application.Bug.Tracking.Application.dto.JwtAuthResponse;
import Bug.Tracking.System.Application.Bug.Tracking.Application.dto.LoginDto;
import Bug.Tracking.System.Application.Bug.Tracking.Application.dto.RegisterDto;

public interface AuthService {
    String register(RegisterDto registerDto);

    JwtAuthResponse login(LoginDto loginDto);
}
