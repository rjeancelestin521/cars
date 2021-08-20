package mg.rjc.testcars.service;

import mg.rjc.testcars.entities.Comment;

import java.util.List;

public interface CommentService {
    public Comment saveComment(Comment comment);
    public List<Comment> findCommentsByCarId(Long id);
}
