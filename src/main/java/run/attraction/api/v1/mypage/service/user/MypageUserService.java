package run.attraction.api.v1.mypage.service.user;

import run.attraction.api.v1.mypage.service.dto.userDetail.UserDetailDto;

import java.util.List;

public interface MypageUserService {
  UserDetailDto getUserDetails(String email);
  void updateProfileImg(String email, String Profile);
  void updateBackgroundImg(String email, String backgroundImg);
  void updateNickname(String email, String nickname);
  void updateUserExpiration(String email, Integer expiration);
  void updateInterest(String email, List<String> interest);
  void updateOccupation(String email, String occupation);
  boolean checkNicknameDuplication(String nikeName);
}
