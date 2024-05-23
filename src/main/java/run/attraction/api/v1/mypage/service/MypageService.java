package run.attraction.api.v1.mypage.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import run.attraction.api.v1.mypage.service.dto.UserDetaiilDto;
import run.attraction.api.v1.mypage.service.user.MyPageUserService;

@Service
@RequiredArgsConstructor
public class MypageService {
  private final MyPageUserService myPageUserService;

  public UserDetaiilDto getUserDetails(String email){
    return myPageUserService.getUserDetails(email);
  }
}
