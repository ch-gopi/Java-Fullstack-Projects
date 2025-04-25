package To_Do_Management_System.Todo_management_system;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
public class TodoManagementSystemApplication {
	@Bean
  public ModelMapper modelMapper(){
	  return new ModelMapper();
  }
	public static void main(String[] args) {
		SpringApplication.run(TodoManagementSystemApplication.class, args);
	}

}
