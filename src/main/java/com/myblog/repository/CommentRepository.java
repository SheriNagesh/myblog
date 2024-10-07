package com.myblog.repository;

import com.myblog.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Long> {
    // hibernate will build sql query automatically, when we used findById (custom method)
    List<Comment> findByPostId(long postId);
}
