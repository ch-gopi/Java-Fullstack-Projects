<<<<<<< HEAD
package Bug.Tracking.System.Application.Bug.Tracking.Application.repository;

import Bug.Tracking.System.Application.Bug.Tracking.Application.entity.BugComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BugCommentRepository extends JpaRepository<BugComment, Long> {
    List<BugComment> findByBugId(Long bugId);

    void deleteByBugId(Long id);
}
=======
package Bug.Tracking.System.Application.Bug.Tracking.Application.repository;

import Bug.Tracking.System.Application.Bug.Tracking.Application.entity.BugComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BugCommentRepository extends JpaRepository<BugComment, Long> {
    List<BugComment> findByBugId(Long bugId);

    void deleteByBugId(Long id);
}
>>>>>>> 7597e7e0eba66a899deb947e73815869450259fd
