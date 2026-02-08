package Bug.Tracking.System.Application.Bug.Tracking.Application.config;


import Bug.Tracking.System.Application.Bug.Tracking.Application.security.JwtAuthenticationEntryPoint;
import Bug.Tracking.System.Application.Bug.Tracking.Application.security.JwtAuthenticationFilter;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
<<<<<<< HEAD
import org.springframework.security.web.AuthenticationEntryPoint;
=======
>>>>>>> 7597e7e0eba66a899deb947e73815869450259fd
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

@Configuration
@EnableMethodSecurity
@AllArgsConstructor
public class SpringSecurityConfig {

    private UserDetailsService userDetailsService;

    private JwtAuthenticationEntryPoint authenticationEntryPoint;

    private JwtAuthenticationFilter authenticationFilter;

    @Bean
    public static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.csrf((csrf) -> csrf.disable())

                .sessionManagement(manager -> manager.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests((authorize) -> {

                    authorize.requestMatchers("/api/auth/**").permitAll();
                    authorize.requestMatchers(HttpMethod.OPTIONS, "/**").permitAll();
                    authorize.requestMatchers(HttpMethod.GET, "/api/bugs/analytics").permitAll();
                    authorize.requestMatchers("/error").permitAll();
                    authorize.requestMatchers("/favicon.ico").permitAll();
                    //  Allow report exports without authentication
                    authorize.requestMatchers("/api/images/uploads/images/**","/api/images/**").permitAll();
                    authorize.requestMatchers(HttpMethod.GET, "/api/reports/export/csv").permitAll();
                    authorize.requestMatchers(HttpMethod.GET, "/api/reports/export/pdf").permitAll();
                    authorize.requestMatchers(HttpMethod.GET,"/api/coms/{bugId}/comments").permitAll();
                    authorize.anyRequest().authenticated();
                }).httpBasic(Customizer.withDefaults());

        http.exceptionHandling( exception -> exception
<<<<<<< HEAD
                .authenticationEntryPoint((AuthenticationEntryPoint) authenticationEntryPoint));
=======
                .authenticationEntryPoint(authenticationEntryPoint));
>>>>>>> 7597e7e0eba66a899deb947e73815869450259fd

        http.addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }


}
