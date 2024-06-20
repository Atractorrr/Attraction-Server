package run.attraction.api.v1.auth.service.helper;

import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import run.attraction.api.v1.gmail.event.UserLoggedEvent;
import run.attraction.api.v1.auth.service.dto.UserTokenDto;
import run.attraction.api.v1.gmail.entity.GoogleRefreshToken;
import run.attraction.api.v1.auth.token.LogoutAccessToken;
import run.attraction.api.v1.auth.token.RefreshToken;
import run.attraction.api.v1.auth.token.exception.InvalidTokenException;
import run.attraction.api.v1.auth.token.exception.TokenNotFoundException;
import run.attraction.api.v1.auth.token.jwt.JwtService;
import run.attraction.api.v1.gmail.repository.GoogleRefreshTokenRepository;
import run.attraction.api.v1.auth.token.repository.LogoutAccessTokenRepository;
import run.attraction.api.v1.auth.token.repository.RefreshTokenRepository;
import run.attraction.api.v1.user.User;
import run.attraction.api.v1.user.UserDetail;
import run.attraction.api.v1.user.repository.UserDetailRepository;
import run.attraction.api.v1.user.repository.UserRepository;
import run.attraction.api.v1.user.service.UserServiceImpl;

@Slf4j
@Component
@RequiredArgsConstructor
public class AuthProviderAndTokenHelper {
  private final JwtService jwtService;
  private final RefreshTokenRepository refreshTokenRepository;
  private final LogoutAccessTokenRepository logoutAccessTokenRepository;
  private final UserRepository userRepository;
  private final GoogleRefreshTokenRepository googleRefreshTokenRepository;
  private final UserDetailRepository userDetailRepository;
  private final UserServiceImpl userService;
  private final ApplicationEventPublisher eventPublisher;

  @Transactional
  public UserTokenDto getTokenAndRegisterUserByAuthUser(User authUser) {
    log.info("JWT 토큰 등록 및 유저 저장 시작");
    final Optional<User> findUser = userRepository.findById(authUser.getEmail());
    if (findUser.isPresent()) {
      log.info("기존에 존재하는 유저입니다.");
      User user = findUser.get();
      renewUserUpdateAt(user);
      log.info("JWT 토큰 발급 시작");
      return getToken(user, true);
    }
    log.info("새로운 유저 저장");
    userRepository.save(authUser);
    log.info("유저 저장 완료");
    log.info("JWT 토큰 발급 시작");
    return getToken(authUser, false);
  }

  private void renewUserUpdateAt(User user) {
    if (user.getUpdateAt().isBefore(LocalDate.now())){
      log.info("유저의 최신 접속 이력 갱신");
      userService.updateUserExpiration(user,LocalDate.now());
    }
  }

  public UserTokenDto getToken(User user, boolean isUserBefore) {
    log.info("getToken 시작");
    final String accessToken = jwtService.generateAccessToken(user, new Date(System.nanoTime()));
    final String refreshToken = jwtService.generateRefreshToken(user, new Date(System.nanoTime()));
    log.info("JWT 토큰 생성 완료");
    renewRefreshToken(user, refreshToken);
    UserTokenDto userTokenDto = UserTokenDto.builder()
        .accessToken(accessToken)
        .refreshToken(refreshToken)
        .email(user.getEmail())
        .isUserBefore(isUserBefore)
        .build();

    if (isUserBefore) {
      userTokenDto.setShouldReissueToken(getShouldReissueToken(user));
      final Optional<UserDetail> checkUserDetail = userDetailRepository.findById(user.getEmail());
      userTokenDto.setHasExtraDetails(checkUserDetail.isPresent());
      log.info("기존의 유저의 shouldReissueToken, hasExtraDetails 추출 완료 ");
    }
    return userTokenDto;
  }

  private boolean getShouldReissueToken(User user) {
    final GoogleRefreshToken googleRefreshToken = googleRefreshTokenRepository.findByEmail(user.getEmail());

    if (!googleRefreshToken.getShouldReissueToken()) {
      eventPublisher.publishEvent(new UserLoggedEvent(googleRefreshToken.getEmail(), googleRefreshToken.getToken()));
    }

    return googleRefreshToken.getShouldReissueToken();
  }

  private void renewRefreshToken(User user, String refreshToken) {
    log.info("refreshToken 저장 시작");
    RefreshToken token = refreshTokenRepository.findTokenByUser(user)
        .orElse(createRefreshToken(user, refreshToken));

    token.updateToken(refreshToken);
    refreshTokenRepository.save(token);
    log.info("refreshToken 저장 완료");
  }

  private RefreshToken createRefreshToken(User user, String refreshToken) {
    return RefreshToken.builder()
        .user(user)
        .token(refreshToken)
        .build();
  }

  public void saveAccessTokenAndDeleteRefreshToken(final String accessToken) {
    log.info("accessToken 저장 및 refreshToken 삭제 시작");
    String userEmail = jwtService.extractEmailFromToken(accessToken);
    saveLogoutAccessToken(accessToken);
    refreshTokenRepository.findTokenByUserEmail(userEmail)
        .ifPresent(this::deleteRefreshToken);
    log.info("accessToken 저장 및 refreshToken 삭제 완료");
  }

  private void saveLogoutAccessToken(final String accessToken) {
    final long expireTimeFromToken = jwtService.getExpireTimeFromToken(accessToken);
    final LogoutAccessToken logoutAccessToken = LogoutAccessToken.builder().id(accessToken)
        .expiration(expireTimeFromToken).build();
    log.info("로그아웃 AccessToken 저장 시작");
    logoutAccessTokenRepository.save(logoutAccessToken);
    log.info("로그아웃 AccessToken 저장 완료");
  }

  private void deleteRefreshToken(RefreshToken refreshToken) {
    log.info("refreshToken 삭제 시작");
    refreshTokenRepository.delete(refreshToken);
    log.info("refreshToken 삭제 완료");
  }

  public UserTokenDto reissueToken(String refreshToken, Date issuedAt) {
    User user = CheckValidTokenAndGetUser(refreshToken);

    String accessToken = jwtService.generateAccessToken(user, issuedAt);
    String reissueToken = jwtService.generateRefreshToken(user, issuedAt);
    reissueRefreshToken(user, reissueToken);
    return UserTokenDto.builder()
        .accessToken(accessToken)
        .refreshToken(reissueToken)
        .email(user.getEmail())
        .build();
  }

  private User CheckValidTokenAndGetUser(String token) {
    RefreshToken refreshToken = refreshTokenRepository.findByToken(token)
        .orElseThrow(() -> new TokenNotFoundException("존재하지 않는 Refresh Token 입니다."));
    User user = refreshToken.getUser();
    if (!jwtService.isTokenValid(token, user)) {
      throw new InvalidTokenException("토큰이 유효하지 않습니다.");
    }
    return user;
  }

  private void reissueRefreshToken(User user, String token) {
    final RefreshToken refreshToken = refreshTokenRepository.findTokenByUser(user)
        .orElse(createRefreshToken(user, token));
    refreshToken.updateToken(token);
    refreshTokenRepository.save(refreshToken);
  }
}
