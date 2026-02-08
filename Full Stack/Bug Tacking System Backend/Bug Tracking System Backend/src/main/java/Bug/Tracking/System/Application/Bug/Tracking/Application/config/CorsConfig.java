<<<<<<< HEAD
package Bug.Tracking.System.Application.Bug.Tracking.Application.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class CorsConfig {
    private final CorsPolicy corsPolicy;

    public CorsConfig(CorsPolicy corsPolicy) {
        this.corsPolicy = corsPolicy;
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                corsPolicy.configure(registry);
            }
        };
    }
}
=======
package Bug.Tracking.System.Application.Bug.Tracking.Application.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/uploads/images/**")
                        .allowedOrigins("/api/**")
                        .allowedOrigins("http://localhost:3000")
                        .allowedOrigins("*")
                        .allowedMethods("GET");
            }
        };
    }
}
>>>>>>> 7597e7e0eba66a899deb947e73815869450259fd
