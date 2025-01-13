package run.attraction.api.v1.announcement.dto.request;

public record PostSearchRequest(
        String query,
        String type,
        Integer page,
        Integer size
) {
    public PostSearchRequest {
        if (page == null || page < 0) {
            page = 0;
        }
        if (size == null || size <= 0) {
            size = 3;
        }
    }
}
