package com.springboot.blogapi.service.impl;

import com.springboot.blogapi.exception.ResourceNotFoundException;
import com.springboot.blogapi.model.Category;
import com.springboot.blogapi.model.Post;
import com.springboot.blogapi.payload.PostDto;
import com.springboot.blogapi.repository.CategoryRepository;
import com.springboot.blogapi.repository.PostRepository;
import com.springboot.blogapi.payload.PostResponse;
import com.springboot.blogapi.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {
    private PostRepository postRepository;

    private ModelMapper mapper;

    private CategoryRepository categoryRepository;

    public PostServiceImpl(PostRepository postRepository, CategoryRepository categoryRepository, ModelMapper mapper) {

        this.postRepository = postRepository;
        this.categoryRepository = categoryRepository;
        this.mapper = mapper;
    }

    @Override
    public PostDto createPost(PostDto postDto) {

        Category category = categoryRepository.findById(postDto.getCategoryId()).orElseThrow(() -> new ResourceNotFoundException("Category", "id", postDto.getCategoryId()));

        Post post = mapToEntity(postDto);
        post.setCategory(category);
        Post newPost = postRepository.save(post);
        return mapToDto(newPost);

    }

    @Override
    public PostResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        Page<Post> posts = postRepository.findAll(pageable);
        List<Post> listOfPosts = posts.getContent();
        List<PostDto> content = listOfPosts.stream().map(this::mapToDto).collect(Collectors.toList());
        return new PostResponse(content, posts.getTotalPages(), posts.getTotalElements(), posts.getNumber(), posts.getSize(), posts.isLast());
    }

    @Override
    public PostDto getPost(Long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
        return mapToDto(post);
    }

    @Override
    public PostDto updatePost(Long id, PostDto postDto) {
        //get post by id
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
        Category category = categoryRepository.findById(postDto.getCategoryId()).orElseThrow(() -> new ResourceNotFoundException("Category", "id", postDto.getCategoryId()));


        //update post
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setDescription(postDto.getDescription());
        post.setCategory(category);
        //save post
        Post updatedPost = postRepository.save(post);

        return mapToDto(updatedPost);
    }

    @Override
    public List<PostDto> getAllPostsByCategoryId(Long categoryId) {

        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId));
        //get posts by category id
        List<Post> posts = postRepository.findByCategoryId(categoryId);
        //map posts to postDto
        return posts.stream().map(this::mapToDto).toList();


    }

    @Override
    public void deletePostById(Long id) {
        //get post by id
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
        //delete post
        postRepository.delete(post);


    }

    private PostDto mapToDto(Post post) {
        return mapper.map(post, PostDto.class);
    }

    private Post mapToEntity(PostDto postDto) {

        return mapper.map(postDto, Post.class);
    }

}
