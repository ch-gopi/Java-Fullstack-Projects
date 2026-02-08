package Bug.Tracking.System.Application.Bug.Tracking.Application.config;

import org.springframework.web.servlet.config.annotation.CorsRegistry;

public interface CorsPolicy {
    void configure(CorsRegistry registry);
}
