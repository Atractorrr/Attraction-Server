package run.attraction.api.v1.auth.service.helper;

import java.time.LocalDate;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import run.attraction.api.v1.auth.service.dto.UserStateDto;
import run.attraction.api.v1.gmail.entity.GoogleRefreshToken;
import run.attraction.api.v1.gmail.repository.GoogleRefreshTokenRepository;
import run.attraction.api.v1.user.User;
import run.attraction.api.v1.user.UserDetail;
import run.attraction.api.v1.user.repository.UserDetailRepository;
import run.attraction.api.v1.user.repository.UserRepository;
import run.attraction.api.v1.user.service.UserServiceImpl;

@Slf4j
@Component
@RequiredArgsConstructor
public class LoginHelper {
  private final UserRepository userRepository;
  private final GoogleRefreshTokenRepository googleRefreshTokenRepository;
  private final UserDetailRepository userDetailRepository;
  private final UserServiceImpl userService;

  @Transactional
  public UserStateDto getUserState(User authUser) {
    log.info("유저 상태 조회 시작");
    final Optional<User> findUser = userRepository.findById(authUser.getEmail());
    // 이전에 로그인했던 유저라면
    if (findUser.isPresent()) {
      log.info("기존 유저");
      User user = findUser.get();
      checkIsDeleted(user);
      log.info("탈퇴 회원 검사 완료");
      renewUserUpdateAt(user);
      log.info("접속 시간 갱시 완료");
      return createUserStateDto(user, true);
    }
    // 처음 로그인하는 유저라면
    userRepository.save(authUser);
    return createUserStateDto(authUser, false);
  }

  private void checkIsDeleted(User user){
    if(user.isDeleted()){
      user.updateIsDeleted(false);
    }
  }
  private void renewUserUpdateAt(User user) {
    if (user.getUpdateAt().isBefore(LocalDate.now())){
      userService.updateUserExpiration(user,LocalDate.now());
    }
  }

  private UserStateDto createUserStateDto(User user, boolean isUserBefore){
    UserStateDto userStateDto = UserStateDto.builder()
        .email(user.getEmail())
        .isUserBefore(isUserBefore)
        .build();

    if (isUserBefore) {
      userStateDto.setShouldReissueToken(getShouldReissueToken(user));
      final Optional<UserDetail> checkUserDetail = userDetailRepository.findById(user.getEmail());
      userStateDto.setHasExtraDetails(checkUserDetail.isPresent());
    }
    return userStateDto;

  }

  private boolean getShouldReissueToken(User user) {
    final GoogleRefreshToken googleRefreshToken = googleRefreshTokenRepository.findByEmail(user.getEmail());
    return googleRefreshToken.getShouldReissueToken();
  }

}
