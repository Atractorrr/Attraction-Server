package run.attraction.api.v1.mypage.service.dto.userDetail;

import java.util.Objects;

public record UpdateUserExpirationRequestDto(
        Integer userExpiration
) {
    public UpdateUserExpirationRequestDto {
        Objects.requireNonNull(userExpiration);
    }
}
