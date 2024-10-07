package com.myblog.controller;

import com.myblog.payload.CommentDto;
import com.myblog.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class CommentController {

    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }
   //http://localhost:8080/api/posts/1/comments
    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<CommentDto> createComment(@PathVariable(value = "postId") long postId,
                                                    @RequestBody CommentDto commentDto){
        return new ResponseEntity<>(commentService.createComment(postId, commentDto), HttpStatus.CREATED);
    }
    //http://localhost:8080/api/posts/1/comments
    @GetMapping("/posts/{postId}/comments")
    public List<CommentDto> getCommentsByPostId(@PathVariable(value = "postId") Long postId){
        return commentService.getCommentsByPostId(postId);
    }

    //http://localhost:8080/api/posts/1/comments/2
    @GetMapping("/posts/{postId}/comments/{commentId}")
    public CommentDto getCommentsId(@PathVariable(value = "postId") Long postId,
                                          @PathVariable(value = "commentId") Long commentId){
        return commentService.getCommentsById(postId,commentId);
    }

    //http://localhost:8080/api/comments
    @GetMapping("/comments")
    public List<CommentDto> getAllCommentsId(){
        return commentService.getAllCommentsById();
    }

    //http://localhost:8080/api/posts/1/comments/2
    @DeleteMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity<String> deleteCommentById(@PathVariable(value = "postId") Long postId,
                                    @PathVariable(value = "commentId") Long commentId){
        commentService.deleteCommentById(postId,commentId);

        return new ResponseEntity<>("Comment is deleted",HttpStatus.OK);
    }
}

