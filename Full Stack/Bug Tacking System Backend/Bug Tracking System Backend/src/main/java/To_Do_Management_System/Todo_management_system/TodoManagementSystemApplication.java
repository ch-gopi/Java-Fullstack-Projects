package To_Do_Management_System.Todo_management_system;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TodoManagementSystemApplication {
	@Bean
  public ModelMapper modelMapper(){
	  return new ModelMapper();
  }
	public static void main(String[] args) {
		SpringApplication.run(TodoManagementSystemApplication.class, args);
	}

}
