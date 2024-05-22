package run.attraction.api.v1.mypage.service.user;

import run.attraction.api.v1.mypage.service.dto.UserDetaiilDto;

public interface MyPageUserService {
  UserDetaiilDto getUserDetails(Long userId);
}
