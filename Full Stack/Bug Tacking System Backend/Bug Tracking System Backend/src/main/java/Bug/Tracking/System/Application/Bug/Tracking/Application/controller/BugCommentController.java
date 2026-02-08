<<<<<<< HEAD
package Bug.Tracking.System.Application.Bug.Tracking.Application.controller;


import Bug.Tracking.System.Application.Bug.Tracking.Application.entity.BugComment;
import Bug.Tracking.System.Application.Bug.Tracking.Application.service.BugInterfaceCommentService;
import Bug.Tracking.System.Application.Bug.Tracking.Application.service.bugServiceImpl.BugCommentService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/coms")
@AllArgsConstructor
public class BugCommentController extends AbstractController {
    private final BugInterfaceCommentService bugCommentService;

    @PostMapping("/{bugId}/comments")
    public ResponseEntity<String> addComment(@PathVariable Long bugId, @RequestParam Long userId, @RequestParam String commentText) {
        bugCommentService.addComment(bugId, userId, commentText);
        return ResponseEntity.ok("Comment added successfully!");
    }

    @GetMapping("/{bugId}/comments")
    public ResponseEntity<List<BugComment>> getComments(@PathVariable Long bugId) {

        System.out.println("Fetching comments for Bug ID: " + bugId);

        List<BugComment> comments = bugCommentService.getComments(bugId);
        System.out.println("Fetched Comments Count: " + comments);

        if (comments.isEmpty()) {
            return ResponseEntity.ok(Collections.emptyList()); //  Return empty list instead of 500 error
        }

        return ResponseEntity.ok(comments);
    }

    @PutMapping("/{commentId}")
    public ResponseEntity<String> updateComment(@PathVariable Long commentId, @RequestParam Long userId, @RequestParam String updatedComment) {
        bugCommentService.updateComment(commentId, updatedComment, userId);
        return ResponseEntity.ok("Comment updated successfully!");
    }
}
=======
package Bug.Tracking.System.Application.Bug.Tracking.Application.controller;


import Bug.Tracking.System.Application.Bug.Tracking.Application.entity.BugComment;
import Bug.Tracking.System.Application.Bug.Tracking.Application.service.bugServiceImpl.BugCommentService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/coms")
@AllArgsConstructor
public class BugCommentController {
    private final BugCommentService bugCommentService;

    @PostMapping("/{bugId}/comments")
    public ResponseEntity<String> addComment(@PathVariable Long bugId, @RequestParam Long userId, @RequestParam String commentText) {
        bugCommentService.addComment(bugId, userId, commentText);
        return ResponseEntity.ok("Comment added successfully!");
    }

    @GetMapping("/{bugId}/comments")
    public ResponseEntity<List<BugComment>> getComments(@PathVariable Long bugId) {

        System.out.println("Fetching comments for Bug ID: " + bugId);

        List<BugComment> comments = bugCommentService.getComments(bugId);
        System.out.println("Fetched Comments Count: " + comments);

        if (comments.isEmpty()) {
            return ResponseEntity.ok(Collections.emptyList()); //  Return empty list instead of 500 error
        }

        return ResponseEntity.ok(comments);
    }

    @PutMapping("/{commentId}")
    public ResponseEntity<String> updateComment(@PathVariable Long commentId, @RequestParam Long userId, @RequestParam String updatedComment) {
        bugCommentService.updateComment(commentId, updatedComment, userId);
        return ResponseEntity.ok("Comment updated successfully!");
    }
}
>>>>>>> 7597e7e0eba66a899deb947e73815869450259fd
