package run.attraction.api.v1.announcement.dto;

import java.time.LocalDateTime;
import run.attraction.api.v1.announcement.Post;

public record PostSummaryDTO(
        Long id,
        String title,
        String content,
        String postCategory,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt,
        Long viewCount
) {
    public PostSummaryDTO(Post post) {
        this(
                post.getId(),
                post.getTitle(),
                post.getContent(),
                post.getPostCategory().getName(),
                post.getCreatedAt(),
                post.getModifiedAt(),
                post.getViewCount()
        );
    }
}
