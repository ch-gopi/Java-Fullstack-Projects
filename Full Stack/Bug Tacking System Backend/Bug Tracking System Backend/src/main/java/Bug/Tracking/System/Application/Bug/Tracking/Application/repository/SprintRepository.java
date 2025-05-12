package Bug.Tracking.System.Application.Bug.Tracking.Application.repository;


import Bug.Tracking.System.Application.Bug.Tracking.Application.entity.Sprint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SprintRepository extends JpaRepository<Sprint, Long> {
}


