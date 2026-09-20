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
