package run.attraction.api.v1.mypage.service.user;

import java.time.LocalDate;
import java.time.Period;
import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import run.attraction.api.v1.mypage.service.dto.userDetail.UpdateUserDetailDto;
import run.attraction.api.v1.mypage.service.dto.userDetail.UserDetailDto;
import run.attraction.api.v1.user.User;
import run.attraction.api.v1.user.UserDetail;
import run.attraction.api.v1.user.repository.UserDetailRepository;
import run.attraction.api.v1.user.repository.UserRepository;

@Slf4j
@Component
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MypageUserServiceImpl implements MypageUserService {
  private final UserRepository userRepository;
  private final UserDetailRepository userDetailRepository;

  private static final int EXPIRATION_FOREVER = 120;
  public UserDetailDto getUserDetails(String email) {
    log.info("유저 검색 시작");
    User user = userRepository.findById(email).orElseThrow(() -> new NoSuchElementException("존재하지 않은 유저입니다."));
    UserDetail userDetail = userDetailRepository.findById(email).orElseThrow(() -> new NoSuchElementException("추가 정보를 입력하지 않은 유저입니다."));
    log.info("유저 검색 완료");
    log.info("닉네임 = {}", userDetail.getNickname());
    log.info("프로필 url = {}", user.getProfileImg());
    log.info("배경 이미지 url = {}", userDetail.getNickname());
    log.info("관심사 = {}", userDetail.getInterests().toString());
    log.info("직업 = {}", userDetail.getOccupation().name());
    return getUserDetailDto(user,userDetail);
  }

  private static UserDetailDto getUserDetailDto(User user,UserDetail userDetail) {
    return new UserDetailDto(
        user.getEmail(),
        userDetail.getNickname(),
        user.getProfileImg(),
        user.getBackgroundImg(),
        userDetail.getInterests(),
        userDetail.getOccupation().name(),
        calculateExpiration(user,userDetail));
  }

  private static int calculateExpiration(User user,UserDetail userDetail){
    final Period between = Period.between(user.getUpdateAt(), userDetail.getUserExpiration());
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
    final String email = updateUserDetailDto.getEmail();
    User user = userRepository.findById(email)
         .orElseThrow(() -> new NoSuchElementException("존재하지 않은 유저 입니다."));
    UserDetail userDetail = userDetailRepository.findById(email)
        .orElseThrow(() -> new NoSuchElementException("추가정보를 입력하지 않은 유저입니다."));

    updateUserDetailDto.getNickname().ifPresent(userDetail::updateNickName);
    updateUserDetailDto.getInterest().ifPresent(userDetail::updateInterest);
    updateUserDetailDto.getOccupation().ifPresent(userDetail::updateOccupation);
    updateUserDetailDto.getUserExpiration().ifPresent(exp -> {
      final int expiration = exp == 0 ? EXPIRATION_FOREVER : exp;
      final LocalDate newExpiration = user.getUpdateAt().plus(Period.ofMonths(expiration));
      userDetail.updateUserExpiration(newExpiration);
    });
  }

  @Transactional
  public boolean checkNicknameDuplication(String nickname) {
    log.info("userRepository.existsByNickname() 시작");
    final boolean result = userRepository.existsByNickname(nickname);
    log.info("result = {}", result);
    return result;
  }
}
