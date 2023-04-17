package com.springboot.blogapi.service;

import com.springboot.blogapi.payload.PostDto;
import com.springboot.blogapi.payload.PostResponse;

import java.util.List;

public interface PostService {
    PostDto createPost(PostDto postDto);

    PostResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir);

    PostDto getPost(Long id);

    PostDto updatePost(Long id, PostDto postDto);

    List<PostDto> getAllPostsByCategoryId(Long categoryId);

    void deletePostById(Long id);


}
