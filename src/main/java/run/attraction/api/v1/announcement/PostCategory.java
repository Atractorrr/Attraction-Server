package run.attraction.api.v1.announcement;

import java.util.Arrays;
import lombok.Getter;

@Getter
public enum PostCategory {
    NOTICE("공지사항"),
    UPDATE("업데이트"),
    EVENT("이벤트"),
    MAINTENANCE("점검");

    private final String name;

    PostCategory(final String name) {
        this.name = name;
    }

    public static PostCategory findByName(final String name) {
        return Arrays.stream(PostCategory.values())
                .filter(postCategory -> postCategory.getName().equals(name))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시글 카테고리입니다."));
    }
}
