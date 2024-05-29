package run.attraction.api.v1.auth.service.dto.join;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
@NotNull
public class JoinRequestDto {
  private String email;
  private String nickName;
  private List<String> interest;
  private String birthDate;
  private int userExpiration;
  private String occupation;
  private boolean adPolices;

  @Builder
  private JoinRequestDto(String email,
                        String nickName,
                        List<String> interest,
                        String birthDate,
                        int userExpiration,
                        String occupation,
                        boolean adPolices) {
    this.email = email;
    this.nickName = nickName;
    this.interest = interest;
    this.birthDate = birthDate;
    this.userExpiration = userExpiration;
    this.occupation = occupation;
    this.adPolices = adPolices;
  }
}

