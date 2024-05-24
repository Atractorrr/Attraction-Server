package run.attraction.api.v1.mypage.service.dto;

import java.util.Objects;

public record UserDetailsResponseDto (
  UserDetailDto user
){
  public UserDetailsResponseDto{
    Objects.requireNonNull(user);
  }
}
