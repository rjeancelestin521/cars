package mg.rjc.testcars.controller;

import lombok.RequiredArgsConstructor;
import mg.rjc.testcars.entities.Comment;
import mg.rjc.testcars.service.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/api")
@RequiredArgsConstructor
public class CommentRestController {

    private final CommentService commentService;

    @PostMapping(path = "/comments/save")
    public ResponseEntity<Comment> saveComment(@RequestBody Comment comment) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/comments/save").toUriString());
        Comment newComment = commentService.saveComment(comment);
        return ResponseEntity.created(uri).body(newComment);
    }

    @GetMapping(path = "/comments/car/{id}")
    public ResponseEntity<List<Comment>> getCommentsByCarId(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(commentService.findCommentsByCarId(id));
    }
}
