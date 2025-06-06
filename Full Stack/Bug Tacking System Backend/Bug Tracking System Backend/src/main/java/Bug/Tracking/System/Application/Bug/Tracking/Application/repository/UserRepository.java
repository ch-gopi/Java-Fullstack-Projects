package Bug.Tracking.System.Application.Bug.Tracking.Application.repository;


import Bug.Tracking.System.Application.Bug.Tracking.Application.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    Boolean existsByEmail(String email);

    Optional<User> findByUsernameOrEmail(String username, String email);


    Boolean existsByUsername(String username);
}
