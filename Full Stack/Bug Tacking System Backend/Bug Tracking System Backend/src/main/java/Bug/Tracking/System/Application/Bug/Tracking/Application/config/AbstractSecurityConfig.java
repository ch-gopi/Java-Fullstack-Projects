package Bug.Tracking.System.Application.Bug.Tracking.Application.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public abstract class AbstractSecurityConfig {

    protected abstract AuthenticationEntryPointStrategy authenticationEntryPoint();
    protected abstract AuthenticationFilterStrategy authenticationFilter();

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
