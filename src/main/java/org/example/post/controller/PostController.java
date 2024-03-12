package org.example.post.controller;

import lombok.RequiredArgsConstructor;
import org.example.post.dto.PostDto;
import org.example.post.service.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping("/posts")
    public String createPost(Model model) {
        model.addAttribute("postDto", new PostDto.PostCreateDto());
        return "post/addPost";
    }

    @PostMapping("/posts")
    public String createPost(@ModelAttribute PostDto.PostCreateDto postDto, RedirectAttributes redirectAttributes) {
        Long postId = postService.addPost(postDto);
        redirectAttributes.addAttribute("postId", postId);
        return "redirect:/posts/{postId}";
    }

    @GetMapping("/posts/{postId}")
    public String getPost(@PathVariable Long postId, Model model) {
        PostDto.PostResponseDto postDto = postService.findPost(postId);
        model.addAttribute("postDto", postDto);
        return "post/getPost";
    }

    @GetMapping("/posts/list")
    public String postList(Model model) {
        model.addAttribute("postList", postService.findAll());
        return "post/postList";
    }
}
