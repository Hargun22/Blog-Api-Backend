package com.springboot.blogapi.payload;

import com.springboot.blogapi.payload.PostDto;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class PostResponse {
    private List<PostDto> content;
    private int totalPages;
    private long totalElements;
    private int pageNo;
    private int pageSize;
    private boolean last;

}