package run.attraction.api.v1.auth.filter;

import lombok.Builder;
import lombok.Getter;

@Getter
public class FilterMessageResponseDto {
  private String message;
  private int errorCode;

  @Builder
  private FilterMessageResponseDto(String message, int errorCode) {
    this.message = message;
    this.errorCode = errorCode;
  }
}
