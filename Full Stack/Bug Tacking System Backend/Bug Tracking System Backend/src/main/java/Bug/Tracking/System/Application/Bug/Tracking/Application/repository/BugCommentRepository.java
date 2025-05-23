package Bug.Tracking.System.Application.Bug.Tracking.Application.repository;

import Bug.Tracking.System.Application.Bug.Tracking.Application.entity.BugComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BugCommentRepository extends JpaRepository<BugComment, Long> {
    List<BugComment> findByBugId(Long bugId);
}
