package mg.rjc.testcars.repository;

import mg.rjc.testcars.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    public List<Comment> findByCarId(Long id);
}
