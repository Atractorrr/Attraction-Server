package run.attraction.api.v1.announcement.dto;

import java.time.LocalDateTime;
import run.attraction.api.v1.announcement.Post;

public record PostDTO(
        Long id,
        String title,
        String content,
        String postCategory,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt,
        Long viewCount,
        boolean isPinned,
        RelatedDTO related
) {
    public PostDTO(Post post, Post previousPost, Post nextPost) {
        this(
                post.getId(),
                post.getTitle(),
                post.getContent(),
                post.getPostCategory().name(),
                post.getCreatedAt(),
                post.getModifiedAt(),
                post.getViewCount(),
                post.isPinned(),
                new RelatedDTO(previousPost, nextPost)
        );
    }
}
