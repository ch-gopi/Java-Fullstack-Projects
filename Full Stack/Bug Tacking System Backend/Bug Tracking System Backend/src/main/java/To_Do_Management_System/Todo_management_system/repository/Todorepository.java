package To_Do_Management_System.Todo_management_system.repository;

import To_Do_Management_System.Todo_management_system.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Todorepository extends JpaRepository<Todo, Long> {
}
