package run.attraction.api.v1.announcement.dto;

import java.util.List;
import org.springframework.data.domain.Page;
import run.attraction.api.v1.announcement.PostCategory;

public record CustomPageDTO<T>(
        long totalElements,
        int totalPages,
        boolean first,
        boolean last,
        int size,
        int number,
        PostCategory category,
        List<T> content
) {
    // 생성자: Page 객체를 받아 필드 초기화
    public CustomPageDTO(Page<T> page, PostCategory category) {
        this(
                page.getTotalElements(),
                page.getTotalPages(),
                page.isFirst(),
                page.isLast(),
                page.getSize(),
                page.getNumber(),
                category,
                page.getContent()
        );
    }
}
