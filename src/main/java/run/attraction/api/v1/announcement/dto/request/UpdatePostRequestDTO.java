package run.attraction.api.v1.announcement.dto.request;

public record UpdatePostRequestDTO(
        String title,
        String content,
        String postCategory,
        boolean isPinned
) {
}
