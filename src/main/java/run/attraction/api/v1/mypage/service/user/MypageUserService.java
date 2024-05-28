package run.attraction.api.v1.mypage.service.user;

import org.springframework.web.multipart.MultipartFile;
import run.attraction.api.v1.mypage.service.dto.userDetail.UpdateUserDetailDto;
import run.attraction.api.v1.mypage.service.dto.userDetail.UserDetailDto;

public interface MypageUserService {
  UserDetailDto getUserDetails(String email);
  void updateProfileImg(String email, String Profile);
  void updateBackgroundImg(String email, String backgroundImg);
  void updateUserDetail(UpdateUserDetailDto updateUserDetailDto);
}
