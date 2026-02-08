package Bug.Tracking.System.Application.Bug.Tracking.Application.security;

<<<<<<< HEAD
import Bug.Tracking.System.Application.Bug.Tracking.Application.config.AuthenticationEntryPointStrategy;
=======
>>>>>>> 7597e7e0eba66a899deb947e73815869450259fd
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
<<<<<<< HEAD
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPointStrategy {

=======
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
>>>>>>> 7597e7e0eba66a899deb947e73815869450259fd
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {

        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, authException.getMessage());
    }
}
