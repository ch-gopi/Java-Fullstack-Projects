package Bug.Tracking.System.Application.Bug.Tracking.Application.repository;



import Bug.Tracking.System.Application.Bug.Tracking.Application.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(String name);
}
