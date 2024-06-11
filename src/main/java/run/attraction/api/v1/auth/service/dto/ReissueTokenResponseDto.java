package run.attraction.api.v1.auth.service.dto;

import lombok.Getter;

@Getter
public class ReissueTokenResponseDto {

  private String accessToken;

  public ReissueTokenResponseDto(String accessToken) {
    this.accessToken = accessToken;
  }
}
