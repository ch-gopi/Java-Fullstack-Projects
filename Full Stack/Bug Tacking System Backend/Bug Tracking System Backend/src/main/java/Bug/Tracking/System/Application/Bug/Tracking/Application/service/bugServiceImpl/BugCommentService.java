<<<<<<< HEAD
package Bug.Tracking.System.Application.Bug.Tracking.Application.service.bugServiceImpl;

import Bug.Tracking.System.Application.Bug.Tracking.Application.entity.Bug;
import Bug.Tracking.System.Application.Bug.Tracking.Application.entity.BugComment;
import Bug.Tracking.System.Application.Bug.Tracking.Application.entity.User;
import Bug.Tracking.System.Application.Bug.Tracking.Application.repository.BugCommentRepository;
import Bug.Tracking.System.Application.Bug.Tracking.Application.repository.Bugrepository;
import Bug.Tracking.System.Application.Bug.Tracking.Application.repository.UserRepository;
import Bug.Tracking.System.Application.Bug.Tracking.Application.service.BugInterfaceCommentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class BugCommentService implements BugInterfaceCommentService {
    private final BugCommentRepository bugCommentRepository;
    private final Bugrepository bugRepository;
    private final UserRepository userRepository;


    public void addComment(Long bugId, Long userId, String commentText) {
        Bug bug = bugRepository.findById(bugId).orElseThrow(() -> new RuntimeException("Bug not found"));
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

        BugComment newComment = new BugComment();
        newComment.setBug(bug);
        newComment.setUser(user);
        newComment.setComment(commentText);
        newComment.setCreatedAt(LocalDateTime.now());

        bugCommentRepository.save(newComment);
    }
    @Transactional
    public List<BugComment> getComments(Long bugId) {
        List<BugComment> comments = bugCommentRepository.findByBugId(bugId);
        System.out.println("Fetched Comments: " + comments);
        return comments;    }

    public void updateComment(Long commentId, String updatedComment, Long userId) {
        BugComment comment = bugCommentRepository.findById(commentId).orElseThrow(() -> new RuntimeException("Comment not found"));

        if (!comment.getUser().getId().equals(userId)) {
            throw new RuntimeException("You can only edit your own comments!");
        }

        comment.setComment(updatedComment);
        comment.setUpdatedAt(LocalDateTime.now());
        bugCommentRepository.save(comment);
    }
}

=======
package Bug.Tracking.System.Application.Bug.Tracking.Application.service.bugServiceImpl;

import Bug.Tracking.System.Application.Bug.Tracking.Application.entity.Bug;
import Bug.Tracking.System.Application.Bug.Tracking.Application.entity.BugComment;
import Bug.Tracking.System.Application.Bug.Tracking.Application.entity.User;
import Bug.Tracking.System.Application.Bug.Tracking.Application.repository.BugCommentRepository;
import Bug.Tracking.System.Application.Bug.Tracking.Application.repository.Bugrepository;
import Bug.Tracking.System.Application.Bug.Tracking.Application.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class BugCommentService {
    private final BugCommentRepository bugCommentRepository;
    private final Bugrepository bugRepository;
    private final UserRepository userRepository;


    public void addComment(Long bugId, Long userId, String commentText) {
        Bug bug = bugRepository.findById(bugId).orElseThrow(() -> new RuntimeException("Bug not found"));
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

        BugComment newComment = new BugComment();
        newComment.setBug(bug);
        newComment.setUser(user);
        newComment.setComment(commentText);
        newComment.setCreatedAt(LocalDateTime.now());

        bugCommentRepository.save(newComment);
    }
    @Transactional
    public List<BugComment> getComments(Long bugId) {
        List<BugComment> comments = bugCommentRepository.findByBugId(bugId);
        System.out.println("Fetched Comments: " + comments);
        return comments;    }

    public void updateComment(Long commentId, String updatedComment, Long userId) {
        BugComment comment = bugCommentRepository.findById(commentId).orElseThrow(() -> new RuntimeException("Comment not found"));

        if (!comment.getUser().getId().equals(userId)) {
            throw new RuntimeException("You can only edit your own comments!");
        }

        comment.setComment(updatedComment);
        comment.setUpdatedAt(LocalDateTime.now());
        bugCommentRepository.save(comment);
    }
}

>>>>>>> 7597e7e0eba66a899deb947e73815869450259fd
