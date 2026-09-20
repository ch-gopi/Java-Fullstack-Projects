package Bug.Tracking.System.Application.Bug.Tracking.Application;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BugTrackingApplication {
	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}
	@Bean
	public ObjectMapper objectMapper() {
	    return new ObjectMapper()
	        .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
	        .registerModule(new JavaTimeModule());
	}

	public static void main(String[] args) {
		SpringApplication.run(BugTrackingApplication.class, args);
	}

}
