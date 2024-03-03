package org.example.post.service;

import org.example.post.dto.PostDto;

public interface PostService {
    Long addPost(PostDto.PostCreateDto postDto);
}
