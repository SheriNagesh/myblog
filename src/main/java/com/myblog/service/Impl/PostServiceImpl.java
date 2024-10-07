package com.myblog.service.Impl;

import com.myblog.entity.Post;
import com.myblog.exception.ResourceNotFound;
import com.myblog.payload.PostDto;
import com.myblog.payload.PostResponse;
import com.myblog.repository.PostRepository;
import com.myblog.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    private PostRepository postRepository; // Internal library
    private ModelMapper modelMapper;//external library

    public PostServiceImpl(PostRepository postRepository, ModelMapper modelMapper) {
        this.postRepository = postRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public PostDto savePost(PostDto postDto) {
        Post post = mapToEntity(postDto);
        Post savedPost = postRepository.save(post);
        return mapToDto(savedPost);
    }

    @Override
    public void deletePost(long id) {
        postRepository.deleteById(id);
    }

    @Override
    public PostDto updatePost(long id, PostDto postDto) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFound("Post not found with id: " + id));

        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());

        Post updatedPost = postRepository.save(post);
        return mapToDto(updatedPost);
    }

    @Override
    public PostDto getPostById(long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFound("Post not found with id: " + id));
        return mapToDto(post);
    }

    @Override
    public PostResponse getPosts(int pageNo, int pageSize, String sortBy, String sortDir) {
        // Determine the sort direction(used Ternery Operator)
        Sort.Direction direction = sortDir.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;

        // Create Pageable object with the determined sort direction
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(direction, sortBy));

        // Retrieve the paginated and sorted posts
        Page<Post> pagePosts = postRepository.findAll(pageable);

        // Convert the list of Post entities to PostDto objects
        List<Post> posts = pagePosts.getContent();
        List<PostDto> postDtos = posts.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());

        PostResponse postReponse = new PostResponse();
        postReponse.setPostDto(postDtos);
        postReponse.setPageNo(pagePosts.getNumber());
        postReponse.setPageSize(pagePosts.getSize());
        postReponse.setTotalElements(pagePosts.getTotalElements());
        postReponse.setLast(pagePosts.isLast());
        postReponse.setTotalPages(pagePosts.getTotalPages());

        return postReponse;
    }


    private PostDto mapToDto(Post post){ // here we are converting JSON to POJO Object
        PostDto dto = modelMapper.map(post , PostDto.class);
//        PostDto dto = new PostDto();
//        dto.setId(post.getId());
//        dto.setTitle(post.getTitle());
//        dto.setDescription(post.getDescription());
//        dto.setContent(post.getContent());
        return dto;
    }

    private Post mapToEntity(PostDto postDto){ // here we are converting POJO to JSON Object
        Post post = modelMapper.map(postDto, Post.class);
//        Post post = new Post();
//        post.setTitle(postDto.getTitle());
//        post.setDescription(postDto.getDescription());
//        post.setContent(postDto.getContent());
        return post;
    }
}

