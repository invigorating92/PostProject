package org.example.post.service;

import lombok.RequiredArgsConstructor;
import org.example.post.dto.PostDto;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService{
    @Override
    public Long addPost(PostDto.PostCreateDto postDto) {
        return null;
    }
}
