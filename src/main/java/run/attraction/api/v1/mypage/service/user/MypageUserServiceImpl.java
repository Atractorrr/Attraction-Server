package run.attraction.api.v1.mypage.service.user;

import java.time.Period;
import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import run.attraction.api.v1.mypage.service.dto.userDetail.UpdateUserDetailDto;
import run.attraction.api.v1.mypage.service.dto.userDetail.UserDetailDto;
import run.attraction.api.v1.user.User;
import run.attraction.api.v1.user.repository.UserRepository;

@Slf4j
@Component
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MypageUserServiceImpl implements MypageUserService {
  private final UserRepository userRepository;

  private static final int EXPIRATION_FOREVER = 120;
  public UserDetailDto getUserDetails(String email) {
    log.info("유저 검색 시작");
    User user = userRepository.findById(email).orElseThrow(() -> new NoSuchElementException("존재하지 않은 유저입니다."));
    log.info("유저 검색 완료");
    log.info("닉네임 = {}", user.getNickname());
    log.info("프로필 url = {}", user.getProfileImg());
    log.info("배경 이미지 url = {}", user.getNickname());
    log.info("관심사 = {}", user.getInterests().toString());
    log.info("직업 = {}", user.getOccupation().name());
    final UserDetailDto userDetailDto = getUserDetailDto(user);
    return userDetailDto;
  }

  private static UserDetailDto getUserDetailDto(User user) {
    return new UserDetailDto(
        user.getEmail(),
        user.getNickname(),
        user.getProfileImg(),
        user.getBackgroundImg(),
        user.getInterests(),
        user.getOccupation().name(),
        calculateExpiration(user));
  }

  private static int calculateExpiration(User user){
    final Period between = Period.between(user.getUpdateAt(), user.getUserExpiration());
    int betweenMonths = between.getYears() * 12 + between.getMonths();
    return betweenMonths == EXPIRATION_FOREVER ? 0 : betweenMonths;
  }

  @Transactional
  public void updateProfileImg(String email, String profileImg) {
    User user = userRepository.findById(email).orElseThrow(() -> new NoSuchElementException("존재하지 않은 유저 입니다."));
    user.updateProfileImg(profileImg);
  }

  @Transactional
  public void updateBackgroundImg(String email, String backgroundImg) {
    User user = userRepository.findById(email).orElseThrow(() -> new NoSuchElementException("존재하지 않은 유저 입니다."));
    user.updateBackgroundImg(backgroundImg);
  }

  @Transactional
  public void updateUserDetail(UpdateUserDetailDto updateUserDetailDto) {
    User user = userRepository.findById(updateUserDetailDto.getEmail())
        .orElseThrow(() -> new NoSuchElementException("존재하지 않은 유저 입니다."));
    updateUserDetailDto.getNickname().ifPresent(user::updateNickName);
    updateUserDetailDto.getUserExpiration().ifPresent(user::updateExpiration);
    updateUserDetailDto.getInterest().ifPresent(user::updateInterest);
    updateUserDetailDto.getOccupation().ifPresent(user::updateOccupation);
  }

  @Transactional
  public boolean checkNicknameDuplication(String nickname) {
    log.info("userRepository.existsByNickname() 시작");
    final boolean result = userRepository.existsByNickname(nickname);
    log.info("result = {}", result);
    return result;
  }
}
