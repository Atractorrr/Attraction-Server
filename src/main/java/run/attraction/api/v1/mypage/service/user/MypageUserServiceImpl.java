package run.attraction.api.v1.mypage.service.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import run.attraction.api.v1.mypage.service.dto.userDetail.UserDetailDto;
import run.attraction.api.v1.user.User;
import run.attraction.api.v1.user.UserDetail;
import run.attraction.api.v1.user.repository.UserDetailRepository;
import run.attraction.api.v1.user.repository.UserRepository;
import run.attraction.api.v1.user.validator.nickname.NicknameValidator;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

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
            .userExpirationDate(detail.getUserExpiration())
            .agreeAt(detail.getCreatedAt().toLocalDate())
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

  public void updateNickname(String email, String nickname) {
    UserDetail userDetail = userDetailRepository.findById(email).orElseThrow(() -> new NoSuchElementException("추가 정보를 입력하지 않은 유저입니다."));
    userDetail.updateNickName(nickname);
  }

  public void updateUserExpiration(String email, Integer expiration) {
    User user = userRepository.findById(email).orElseThrow(() -> new NoSuchElementException("존재하지 않은 유저 입니다."));
    UserDetail userDetail = userDetailRepository.findById(email).orElseThrow(() -> new NoSuchElementException("추가 정보를 입력하지 않은 유저입니다."));

    final int exp = expiration == 0 ? EXPIRATION_FOREVER : expiration;
    final LocalDate newExpiration = user.getUpdateAt().plus(Period.ofMonths(exp));
    userDetail.updateUserExpiration(newExpiration);
  }

  public void updateInterest(String email, List<String> interest) {
    UserDetail userDetail = userDetailRepository.findById(email).orElseThrow(() -> new NoSuchElementException("추가 정보를 입력하지 않은 유저입니다."));
    userDetail.updateInterest(interest);
  }

  public void updateOccupation(String email, String occupation) {
    UserDetail userDetail = userDetailRepository.findById(email).orElseThrow(() -> new NoSuchElementException("추가 정보를 입력하지 않은 유저입니다."));
    userDetail.updateOccupation(occupation);
  }

  public boolean checkNicknameDuplication(String nickname) {
    NicknameValidator.checkNickname(nickname);
    final boolean result = userDetailRepository.existsByNickname(nickname);
    return result;
  }

  public void updateIsDeleted(String email){
    User user = userRepository.findById(email).orElseThrow(() -> new NoSuchElementException("존재하지 않은 유저 입니다."));
    user.updateIsDeleted(true);

    userDetailRepository.deleteById(email);
  }
}
