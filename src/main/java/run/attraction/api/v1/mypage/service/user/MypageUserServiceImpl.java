package run.attraction.api.v1.mypage.service.user;

import java.time.LocalDate;
import java.time.Period;
import java.util.NoSuchElementException;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import run.attraction.api.v1.mypage.service.dto.userDetail.UpdateUserDetailDto;
import run.attraction.api.v1.mypage.service.dto.userDetail.UserDetailDto;
import run.attraction.api.v1.user.User;
import run.attraction.api.v1.user.UserDetail;
import run.attraction.api.v1.user.repository.UserDetailRepository;
import run.attraction.api.v1.user.repository.UserRepository;

@Slf4j
@Component
@RequiredArgsConstructor
public class MypageUserServiceImpl implements MypageUserService {
  private final UserRepository userRepository;
  private final UserDetailRepository userDetailRepository;

  private static final int EXPIRATION_FOREVER = 120;
  public UserDetailDto getUserDetails(String email) {
    log.info("User Detail 검색 시작");
    User user = userRepository.findById(email).orElseThrow(() -> new NoSuchElementException("존재하지 않은 유저입니다."));
    //추가정보를 입력하지 않을 수도 있기때문에 예외 X
    final Optional<UserDetail> userDetail = userDetailRepository.findById(email);
    log.info("User Detail 검색 완료");
    log.info("조회결과 = {}", userDetail.isPresent());
    return getUserDetailDto(user,userDetail);
  }

  private static UserDetailDto getUserDetailDto(User user,Optional<UserDetail> userDetail) {
    return userDetail.map(detail -> UserDetailDto.builder()
            .email(user.getEmail())
            .nickname(detail.getNickname())
            .profileImg(user.getProfileImg())
            .backgroundImg(user.getBackgroundImg())
            .interest(detail.getInterests())
            .occupation(detail.getOccupation().name())
            .userExpiration(calculateExpiration(user, detail))
            .build())
        .orElseGet(() -> UserDetailDto.builder()
            .email(user.getEmail())
            .profileImg(user.getProfileImg())
            .backgroundImg(user.getBackgroundImg())
            .build());
  }

  private static int calculateExpiration(User user,UserDetail userDetail){
    final Period between = Period.between(user.getUpdateAt(), userDetail.getUserExpiration());
    int betweenMonths = between.getYears() * 12 + between.getMonths();
    return betweenMonths == EXPIRATION_FOREVER ? 0 : betweenMonths;
  }

  public void updateProfileImg(String email, String profileImg) {
    User user = userRepository.findById(email).orElseThrow(() -> new NoSuchElementException("존재하지 않은 유저 입니다."));
    user.updateProfileImg(profileImg);
  }

  public void updateBackgroundImg(String email, String backgroundImg) {
    User user = userRepository.findById(email).orElseThrow(() -> new NoSuchElementException("존재하지 않은 유저 입니다."));
    user.updateBackgroundImg(backgroundImg);
  }

  public void updateUserDetail(UpdateUserDetailDto updateUserDetailDto) {
    final String email = updateUserDetailDto.getEmail();
    User user = userRepository.findById(email)
         .orElseThrow(() -> new NoSuchElementException("존재하지 않은 유저 입니다."));
    UserDetail userDetail = userDetailRepository.findUserDetailByEmail(email)
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

  public boolean checkNicknameDuplication(String nickname) {
    log.info("userDetailRepository.existsByNickname() 시작");
    final boolean result = userDetailRepository.existsByNickname(nickname);
    log.info("result = {}", result);
    return result;
  }
}
