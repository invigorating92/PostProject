package org.example.post.service;

import lombok.RequiredArgsConstructor;
import org.example.post.domain.Post;
import org.example.post.dto.PostDto;
import org.example.post.repository.PostRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService{

    private final PostRepository postRepository;

    @Override
    @Transactional
    public Long addPost(PostDto.PostCreateDto postDto) {
        var createPost = Post.builder()
                .title(postDto.getTitle())
                .content(postDto.getContent())
                .build();
        return postRepository.save(createPost).getId();
    }

    @Override
    @Transactional
    public void updatePost(Long postId, PostDto.PostUpdateDto postDto) {
        var post = postRepository.findById(postId)
                .orElseThrow(() -> new NoSuchElementException());
        post.updateText(postDto.getTitle(), postDto.getContent());
    }

    @Override
    @Transactional(readOnly = true)
    public PostDto.PostResponseDto findPost(Long postId) {
        var post = postRepository.findById(postId)
                .orElseThrow(() -> new NoSuchElementException());

        return PostDto.PostResponseDto.builder()
                .postId(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .build();
    }

    @Override
    @Transactional(readOnly = true)
    public List<PostDto.PostResponseDto> findAll() {
        List<Post> postList = postRepository.findAll();

        return postList.stream().map(p -> PostDto.PostResponseDto.builder()
                        .postId(p.getId())
                        .title(p.getTitle())
                        .content(p.getContent())
                        .build()).collect(Collectors.toList());
    }
}
