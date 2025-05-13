package Bug.Tracking.System.Application.Bug.Tracking.Application.repository;


import Bug.Tracking.System.Application.Bug.Tracking.Application.Enums.Severity;
import Bug.Tracking.System.Application.Bug.Tracking.Application.entity.Bug;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface Bugrepository extends JpaRepository<Bug, Long> {
    long countBySeverity(Severity critical);

    long countByCompleted(boolean b);
    Page<Bug> findAll(Pageable pageable);


    List<Bug> findBySprintId(Long sprintId);
}
