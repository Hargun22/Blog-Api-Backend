package com.springboot.blogapi.service;

import com.springboot.blogapi.payload.PostDto;
import com.springboot.blogapi.payload.PostResponse;

public interface PostService {
    PostDto createPost(PostDto postDto);

    PostResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir);

    PostDto getPost(Long id);

    PostDto updatePost(Long id, PostDto postDto);

    void deletePostById(Long id);


}
