package run.attraction.api.v1.mypage.service.user;

import run.attraction.api.v1.mypage.service.dto.UserDetailDto;

public interface MyPageUserService {
  UserDetailDto getUserDetails(String email);
}
