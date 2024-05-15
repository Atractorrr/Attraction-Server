package run.attraction.api.v1.auth.service.dto.join;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
@NotNull
public class JoinRequestDto {
  private Long userId;
  private String nickName;
  private List<String> interest;
  private LocalDate birthDate;
  private int userExpiration;
  private int jobCode;
  private boolean adPolices;

  @Builder
  public JoinRequestDto(Long userId, String nickName,
                        List<String> interest,
                        LocalDate birthDate,
                        int userExpiration,
                        int jobCode,
                        boolean adPolices) {
    this.userId = userId;
    this.nickName = nickName;
    this.interest = interest;
    this.birthDate = birthDate;
    this.userExpiration = userExpiration;
    this.jobCode = jobCode;
    this.adPolices = adPolices;
  }
}
