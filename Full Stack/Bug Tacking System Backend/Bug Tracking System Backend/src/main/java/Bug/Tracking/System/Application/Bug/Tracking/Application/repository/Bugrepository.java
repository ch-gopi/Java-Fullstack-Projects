package Bug.Tracking.System.Application.Bug.Tracking.Application.repository;


import Bug.Tracking.System.Application.Bug.Tracking.Application.Enums.Severity;
import Bug.Tracking.System.Application.Bug.Tracking.Application.entity.Bug;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
public interface Bugrepository extends JpaRepository<Bug, Long>, JpaSpecificationExecutor<Bug> {

    long countBySeverity(Severity critical); 

    long countByCompleted(boolean b); 
   

    @EntityGraph(attributePaths = {"user", "sprint"})
    Page<Bug> findAll(Pageable pageable);
   

    @EntityGraph(attributePaths = {"user", "sprint"})
    List<Bug> findBySprintId(Long sprintId);
   

    @EntityGraph(attributePaths = {"user", "sprint"})
    List<Bug> findAll(Specification<Bug> spec);
   
}



