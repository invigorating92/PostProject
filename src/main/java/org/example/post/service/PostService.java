package org.example.post.service;

import org.example.post.dto.PostDto;

import java.util.List;

public interface PostService {
    Long addPost(PostDto.PostCreateDto postDto);

    PostDto.PostResponseDto findPost(Long postId);

    List<PostDto.PostResponseDto> findAll();
}
