package To_Do_Management_System.Todo_management_system.service;


import To_Do_Management_System.Todo_management_system.dto.JwtAuthResponse;
import To_Do_Management_System.Todo_management_system.dto.LoginDto;
import To_Do_Management_System.Todo_management_system.dto.RegisterDto;

public interface AuthService {
    String register(RegisterDto registerDto);

    JwtAuthResponse login(LoginDto loginDto);
}
