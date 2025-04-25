package To_Do_Management_System.Todo_management_system.repository;


import To_Do_Management_System.Todo_management_system.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(String name);
}
