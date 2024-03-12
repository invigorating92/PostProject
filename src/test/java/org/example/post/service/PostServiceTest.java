package org.example.post.service;

import org.example.post.domain.Post;
import org.example.post.dto.PostDto;
import org.example.post.repository.PostRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@SpringBootTest
class PostServiceTest {

    @MockBean
    private PostRepository postRepository;
    @Autowired
    private PostService postService;

    @Test
    @DisplayName("게시글 추가 테스트")
    void addPost() {
        //given
        var post = Post.builder().id(1L).title("post 제목").content("post 내용").build();
        var postDto = PostDto.PostCreateDto.builder().title("dto 제목").content("dto 내용").build();
        given(postRepository.save(any())).willReturn(post);

        //when
        Long postId = postService.addPost(postDto);

        //then
        assertThat(postId).isEqualTo(1L);
    }

    @Test
    @DisplayName("게시글 단건 찾기 테스트")
    void findPost() {
        //given
        var post = Post.builder().id(1L).title("제목").content("내용").build();
        given(postRepository.findById(anyLong())).willReturn(Optional.ofNullable(post));

        //when
        var findPost = postService.findPost(1L);

        //then
        assertThat(findPost.getPostId()).isEqualTo(1L);
        assertThat(findPost.getTitle()).isEqualTo("제목");
        assertThat(findPost.getContent()).isEqualTo("내용");
    }

    @Test
    @DisplayName("게시글 전체 찾기 테스트")
    void findAll() {
        //given
        List<Post> postList = new ArrayList<>();
        var post1 = Post.builder().id(1L).title("제목1").content("내용1").build();
        var post2 = Post.builder().id(2L).title("제목2").content("내용2").build();
        postList.add(post1);
        postList.add(post2);

        given(postRepository.findAll()).willReturn(postList);

        //when
        var findPostList = postService.findAll();

        //then
        assertThat(findPostList.get(0).getPostId()).isEqualTo(1L);
        assertThat(findPostList.get(0).getTitle()).isEqualTo("제목1");
        assertThat(findPostList.get(0).getContent()).isEqualTo("내용1");

        assertThat(findPostList.get(1).getPostId()).isEqualTo(2L);
        assertThat(findPostList.get(1).getTitle()).isEqualTo("제목2");
        assertThat(findPostList.get(1).getContent()).isEqualTo("내용2");
    }

    @Test
    @DisplayName("게시글 수정 테스트")
    void updatePost() {
        //given
        var post = Post.builder().id(1L).title("제목").content("내용").build();
        var postDto = PostDto.PostUpdateDto.builder().title("수정 제목").content("수정 내용").build();
        given(postRepository.findById(anyLong())).willReturn(Optional.ofNullable(post));

        System.out.println(post.getTitle());
        System.out.println(post.getContent());

        //when
        postService.updatePost(post.getId(), postDto);

        //then
        assertThat(post.getTitle()).isEqualTo("수정 제목");
        assertThat(post.getContent()).isEqualTo("수정 내용");

        System.out.println(post.getTitle());
        System.out.println(post.getContent());
    }
}