package Bug.Tracking.System.Application.Bug.Tracking.Application.controller;


import Bug.Tracking.System.Application.Bug.Tracking.Application.entity.BugComment;
import Bug.Tracking.System.Application.Bug.Tracking.Application.service.bugServiceImpl.BugCommentService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/bugs/{bugId}/comments")
@AllArgsConstructor
public class BugCommentController {
    private final BugCommentService bugCommentService;

    @PostMapping
    public ResponseEntity<String> addComment(@PathVariable Long bugId, @RequestParam Long userId, @RequestParam String commentText) {
        bugCommentService.addComment(bugId, userId, commentText);
        return ResponseEntity.ok("Comment added successfully!");
    }

    @GetMapping
    public ResponseEntity<List<BugComment>> getComments(@PathVariable Long bugId) {
        return ResponseEntity.ok(bugCommentService.getComments(bugId));
    }

    @PutMapping("/{commentId}")
    public ResponseEntity<String> updateComment(@PathVariable Long commentId, @RequestParam Long userId, @RequestParam String updatedComment) {
        bugCommentService.updateComment(commentId, updatedComment, userId);
        return ResponseEntity.ok("Comment updated successfully!");
    }
}
