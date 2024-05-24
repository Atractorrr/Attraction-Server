package run.attraction.api.v1.mypage.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import run.attraction.api.v1.mypage.service.dto.UserDetailDto;
import run.attraction.api.v1.mypage.service.user.MypageUserService;

@Service
@RequiredArgsConstructor
public class MypageService {
  private final MypageUserService mypageUserService;

  public UserDetailDto getUserDetails(String email){
    return mypageUserService.getUserDetails(email);
  }
}
