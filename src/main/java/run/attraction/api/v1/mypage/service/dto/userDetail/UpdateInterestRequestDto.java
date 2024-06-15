package run.attraction.api.v1.mypage.service.dto.userDetail;

import java.util.List;
import java.util.Objects;

public record UpdateInterestRequestDto(
        List<String> interest
) {
    public UpdateInterestRequestDto {
        Objects.requireNonNull(interest);
    }
}
