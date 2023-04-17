package com.springboot.blogapi.controller;

import com.springboot.blogapi.payload.PostDto;
import com.springboot.blogapi.payload.PostResponse;
import com.springboot.blogapi.service.PostService;
import com.springboot.blogapi.utils.AppConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
@Tag(
        name = "CRUD Operations on Posts Resource"
)
public class PostController {

    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    // create blog post
    @Operation(
            summary = "Create a new blog post",
            description = "Create a new blog post"
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP 201: Created."
    )
    @SecurityRequirement(
            name = "Bearer Auth"
    )
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postDto) {

        return new ResponseEntity<>(postService.createPost(postDto), HttpStatus.CREATED);
    }

    // get all blog posts
    @Operation(
            summary = "Get all blog posts",
            description = "Get all blog posts"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP 200: Ok."
    )
    @GetMapping
    public PostResponse getAllPosts(
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIR, required = false) String sortDir
    ) {
        return postService.getAllPosts(pageNo, pageSize, sortBy, sortDir);
    }

    // get blog post by id
    @Operation(
            summary = "Get blog post by id",
            description = "Get blog post by id"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP 200: Ok."
    )
    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPost(@PathVariable Long id) {
        return ResponseEntity.ok(postService.getPost(id));
    }

    // update blog post
    @Operation(
            summary = "Update blog post by id",
            description = "Update blog post by id"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP 200: Ok."
    )
    @SecurityRequirement(
            name = "Bearer Auth"
    )
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<PostDto> updatePost(@PathVariable(name = "id") Long id, @Valid @RequestBody PostDto postDto) {
        return ResponseEntity.ok(postService.updatePost(id, postDto));
    }

    // get all blog posts by category id
    @Operation(
            summary = "Get all blog posts by category id",
            description = "Get all blog posts by category id"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP 200: Ok."
    )
    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<PostDto>> getAllPostsByCategoryId(@PathVariable(name = "categoryId") Long id) {
        List<PostDto> postDtoList = postService.getAllPostsByCategoryId(id);
        return ResponseEntity.ok(postDtoList);
    }

    // delete blog post
    @Operation(
            summary = "Delete blog post by id",
            description = "Delete blog post by id"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP 200: Ok."
    )
    @SecurityRequirement(
            name = "Bearer Auth"
    )
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePost(@PathVariable(name = "id") Long id) {
        postService.deletePostById(id);
        return new ResponseEntity<>("Post deleted successfully", HttpStatus.OK);
    }

}
