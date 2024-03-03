package org.example.post.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class PostDto {

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class PostCreateDto {

        @NotEmpty
        private String title;
        @NotEmpty
        private String content;
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class PostUpdateDto {

        @NotEmpty
        private String title;
        @NotEmpty
        private String content;
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class PostResponseDto {

        @NotNull
        private Long postId;
        @NotEmpty
        private String title;
        @NotEmpty
        private String content;
    }
}
