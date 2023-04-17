package com.springboot.blogapi.payload;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Set;

@Data
@Schema(
        name = "PostDto",
        description = "Data Transfer Object for Post"
)
public class PostDto {
    private Long id;

    @Schema(
            name = "title",
            description = "Title of the Post",
            example = "Spring Boot"
    )
    @NotEmpty
    @Size(min = 2, message = "Title should have at least 2 characters")
    private String title;


    @Schema(
            name = "description",
            description = "Description of the Post",
            example = "Spring Boot is a framework for building microservices"
    )
    @NotEmpty
    @Size(min = 10, message = "Description should have at least 10 characters")
    private String description;


    @Schema(
            name = "content",
            description = "Content of the Post"
    )
    @NotEmpty
    private String content;


    @Schema(
            name = "comments",
            description = "List of comments on the Post"
    )
    private Set<CommentDto> comments;

    @Schema(
            name = "categoryId",
            description = "Category ID of the Post"
    )
    private Long categoryId;
}
