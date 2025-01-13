package run.attraction.api.v1.announcement.dto;

import java.time.LocalDateTime;
import run.attraction.api.v1.announcement.Post;

public record RelatedPostDTO(
        Long id,
        String title,
        String postCategory,
        LocalDateTime createdAt,
        boolean isPinned
) {
    public RelatedPostDTO(Post post) {
        this(post.getId(), post.getTitle(), post.getPostCategory().getName(), post.getCreatedAt(), post.isPinned());
    }
}
