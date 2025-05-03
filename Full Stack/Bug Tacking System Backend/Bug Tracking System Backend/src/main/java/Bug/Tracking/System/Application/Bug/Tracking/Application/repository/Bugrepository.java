package Bug.Tracking.System.Application.Bug.Tracking.Application.repository;


import Bug.Tracking.System.Application.Bug.Tracking.Application.Enums.Severity;
import Bug.Tracking.System.Application.Bug.Tracking.Application.entity.Bug;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Bugrepository extends JpaRepository<Bug, Long> {
    long countBySeverity(Severity critical);

    long countByCompleted(boolean b);
}
