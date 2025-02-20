package run.attraction.api.v1.announcement;

import java.util.Arrays;
import lombok.Getter;

@Getter
public enum SearchType {
    TITLE("제목"),
    CONTENT("내용"),
    TITLE_CONTENT("제목내용");

    private final String type;

    SearchType(final String type) {
        this.type = type;
    }

    public static SearchType findSearchType(final String type) {
        return Arrays.stream(SearchType.values())
                .filter(searchType -> searchType.type.equals(type))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 검색 타입입니다."));
    }
}
