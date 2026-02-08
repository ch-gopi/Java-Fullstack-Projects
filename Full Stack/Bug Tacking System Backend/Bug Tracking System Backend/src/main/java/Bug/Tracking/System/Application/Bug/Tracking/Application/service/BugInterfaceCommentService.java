package Bug.Tracking.System.Application.Bug.Tracking.Application.service;

import Bug.Tracking.System.Application.Bug.Tracking.Application.entity.BugComment;

import java.util.List;

public interface BugInterfaceCommentService {
    void addComment(Long bugId, Long userId, String commentText);
    List<BugComment> getComments(Long bugId); void updateComment(Long commentId, String updatedComment, Long userId); }