package run.attraction.api.v1.mypage.service.dto.userDetail;

import java.util.Objects;

public record UpdateOccupationRequestDto(
        String occupation
) {
    public UpdateOccupationRequestDto {
        Objects.requireNonNull(occupation);
    }
}
