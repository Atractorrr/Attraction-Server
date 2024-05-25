package run.attraction.api.v1.mypage.service.user;

import run.attraction.api.v1.mypage.service.dto.userDetail.UserDetailDto;

public interface MypageUserService {
  UserDetailDto getUserDetails(String email);
}
