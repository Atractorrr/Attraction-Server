package run.attraction.api.v1.mypage.service.dto;

import java.util.Objects;
import java.util.Set;
import lombok.Builder;
import lombok.Getter;
import run.attraction.api.v1.user.Interest;

public record UserDetaiilDto (
  String email,
  String name,
  String profileImg,
  String backgroundImg,
  Set<Interest> categories
){
  public UserDetaiilDto{
    Objects.requireNonNull(email);
    Objects.requireNonNull(name);
    Objects.requireNonNull(profileImg);
    Objects.requireNonNull(backgroundImg);
    Objects.requireNonNull(categories);
  }
}
