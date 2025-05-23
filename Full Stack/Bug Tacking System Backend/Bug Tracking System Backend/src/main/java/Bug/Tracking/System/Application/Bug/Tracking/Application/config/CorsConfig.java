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
                registry.addMapping("/uploads/images/**") // ✅ Allow image access
                        .allowedOrigins("*") // ✅ Adjust to restrict access
                        .allowedMethods("GET"); // ✅ Ensures images can be retrieved
            }
        };
    }
}
