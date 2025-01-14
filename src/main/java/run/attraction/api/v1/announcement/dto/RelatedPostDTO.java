package run.attraction.api.v1.announcement.dto;

import java.time.LocalDateTime;
import run.attraction.api.v1.announcement.Post;

public record RelatedPostDTO(
        Long id,
        String title,
        LocalDateTime createdAt
) {
    public RelatedPostDTO(Post post) {
        this(post.getId(), post.getTitle(), post.getCreatedAt());
    }
}
