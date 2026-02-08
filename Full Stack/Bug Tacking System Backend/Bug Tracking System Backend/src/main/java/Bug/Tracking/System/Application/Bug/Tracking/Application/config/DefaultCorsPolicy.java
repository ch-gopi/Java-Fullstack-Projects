package Bug.Tracking.System.Application.Bug.Tracking.Application.config;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

@Component
public class DefaultCorsPolicy implements CorsPolicy {
    @Override
    public void configure(CorsRegistry registry) {
        registry.addMapping("/uploads/images/**")
                .allowedOrigins("http://localhost:3000")
                .allowedMethods("GET");
    }
}
