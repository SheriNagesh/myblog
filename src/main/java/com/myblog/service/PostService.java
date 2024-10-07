package com.myblog.service;

//import com.myblog.payload.PostDto;
//
//public interface PostService {
//    PostDto savePost(PostDto postDto);
//    void deletePost(long id);
//
//    void updatePost(long id, PostDto postDto);
//
//    PostDto getPostById(long id);
//}

import com.myblog.payload.PostDto;
import com.myblog.payload.PostResponse;

import java.util.List;

public interface PostService {
    PostDto savePost(PostDto postDto);
    void deletePost(long id);
    PostDto updatePost(long id, PostDto postDto);
    PostDto getPostById(long id);

    PostResponse getPosts(int pageNo, int pageSize, String sortBy, String sortDir);
}

