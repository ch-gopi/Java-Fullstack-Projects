package Bug.Tracking.System.Application.Bug.Tracking.Application.service.bugServiceImpl;

import Bug.Tracking.System.Application.Bug.Tracking.Application.dto.JwtAuthResponse;
import Bug.Tracking.System.Application.Bug.Tracking.Application.dto.LoginDto;
import Bug.Tracking.System.Application.Bug.Tracking.Application.dto.RegisterDto;
import Bug.Tracking.System.Application.Bug.Tracking.Application.entity.Role;
import Bug.Tracking.System.Application.Bug.Tracking.Application.entity.User;
import Bug.Tracking.System.Application.Bug.Tracking.Application.exception.BugAPIException;
import Bug.Tracking.System.Application.Bug.Tracking.Application.repository.RoleRepository;
import Bug.Tracking.System.Application.Bug.Tracking.Application.repository.UserRepository;
import Bug.Tracking.System.Application.Bug.Tracking.Application.security.JwtTokenProvider;
<<<<<<< HEAD
import Bug.Tracking.System.Application.Bug.Tracking.Application.service.AuthInterfaceService;
=======
import Bug.Tracking.System.Application.Bug.Tracking.Application.service.AuthService;
>>>>>>> 7597e7e0eba66a899deb947e73815869450259fd
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
<<<<<<< HEAD
public class AuthServiceImpl implements AuthInterfaceService {
=======
public class AuthServiceImpl implements AuthService {
>>>>>>> 7597e7e0eba66a899deb947e73815869450259fd

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    private AuthenticationManager authenticationManager;
    private JwtTokenProvider jwtTokenProvider;

    @Override
    public String register(RegisterDto registerDto) {
        if((registerDto.getName().isEmpty()|| registerDto.getEmail().isEmpty()||registerDto.getUsername().isEmpty()||registerDto.getPassword().isEmpty())){
            throw new BugAPIException(HttpStatus.CONFLICT, "Please fill all details!");
        }

        // check username is already exists in database
        if(userRepository.existsByUsername(registerDto.getUsername())){
            throw new BugAPIException(HttpStatus.BAD_REQUEST, "Username already exists!");
        }

        // check email is already exists in database
        if(userRepository.existsByEmail(registerDto.getEmail())){
            throw new BugAPIException(HttpStatus.BAD_REQUEST, "Email is already exists!.");
        }

        User user = new User();
        user.setName(registerDto.getName());
        user.setUsername(registerDto.getUsername());
        user.setEmail(registerDto.getEmail());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));

        Set<Role> roles = new HashSet<>();
        Role userRole = roleRepository.findByName("ROLE_USER");
        roles.add(userRole);

        user.setRoles(roles);

        userRepository.save(user);

        return "User Registered Successfully!.";
    }

    @Override
    public JwtAuthResponse login(LoginDto loginDto) {

        User user = userRepository.findByUsernameOrEmail(loginDto.getUsernameOrEmail(), loginDto.getUsernameOrEmail())
                .orElseThrow(() -> new UsernameNotFoundException("User not exists by Username or Email"));


        Authentication authentication;
        try {
             authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginDto.getUsernameOrEmail(), loginDto.getPassword())
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (Exception e) {
            throw new BugAPIException(HttpStatus.UNAUTHORIZED, "Invalid username or password.");
        }


        String token = jwtTokenProvider.generateToken(authentication);

        Optional<User> userOptional = userRepository.findByUsernameOrEmail(loginDto.getUsernameOrEmail(),
                loginDto.getUsernameOrEmail());

        String role = null;
        if(userOptional.isPresent()){
            User loggedInUser = userOptional.get();
            Optional<Role> optionalRole = loggedInUser.getRoles().stream().findFirst();

            if(optionalRole.isPresent()){
                Role userRole = optionalRole.get();
                role = userRole.getName();
            }
        }

        JwtAuthResponse jwtAuthResponse = new JwtAuthResponse();
        jwtAuthResponse.setRole(role);
        jwtAuthResponse.setAccessToken(token);
        return jwtAuthResponse;
    }
}
