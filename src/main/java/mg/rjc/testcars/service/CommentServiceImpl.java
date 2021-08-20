package mg.rjc.testcars.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mg.rjc.testcars.entities.Comment;
import mg.rjc.testcars.repository.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    @Override
    public Comment saveComment(Comment comment) {
        comment.setDateComment(new Date());
        log.info("Saving comment {} to the database", comment.getTitle());
        return commentRepository.save(comment);
    }

    @Override
    public List<Comment> findCommentsByCarId(Long id) {
        log.info("Fetching all comments by car id: {}", id);
        return commentRepository.findByCarId(id);
    }
}
