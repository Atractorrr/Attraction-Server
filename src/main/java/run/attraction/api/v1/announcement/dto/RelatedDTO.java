package run.attraction.api.v1.announcement.dto;

import java.util.Objects;
import run.attraction.api.v1.announcement.Post;

public record RelatedDTO(
        RelatedPostDTO previous,
        RelatedPostDTO next
) {
    public RelatedDTO(Post previousPost, Post nextPost) {
        this(
                Objects.isNull(previousPost) ? null : new RelatedPostDTO(previousPost),
                Objects.isNull(nextPost) ? null : new RelatedPostDTO(nextPost)
        );
    }
}
