package run.attraction.api.v1.mypage.service.dto;

import java.util.Objects;

public record UserDetailsResponseDto (
  UserDetaiilDto user
){
  public UserDetailsResponseDto{
    Objects.requireNonNull(user);
  }
}
