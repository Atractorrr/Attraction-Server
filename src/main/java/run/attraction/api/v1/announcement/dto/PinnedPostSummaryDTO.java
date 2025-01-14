package run.attraction.api.v1.announcement.dto;

import java.time.LocalDateTime;
import run.attraction.api.v1.announcement.Post;

public record PinnedPostSummaryDTO(
        Long id,
        String title,
        String postCategory,
        LocalDateTime createdAt,
        Long viewCount
) {
    public PinnedPostSummaryDTO(Post post) {
        this(
                post.getId(),
                post.getTitle(),
                post.getPostCategory().getName(),
                post.getCreatedAt(),
                post.getViewCount()
        );
    }
}
