package run.attraction.api.v1.mypage.service.dto.userDetail;

import java.util.Objects;

public record UpdateNicknameRequestDto(
        String nickname
) {
    public UpdateNicknameRequestDto {
        Objects.requireNonNull(nickname);
    }
}
