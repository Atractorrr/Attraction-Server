package run.attraction.api.v1.auth.service.helper;

import java.util.Date;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import run.attraction.api.v1.auth.service.dto.UserTokenDto;
import run.attraction.api.v1.auth.token.LogoutAccessToken;
import run.attraction.api.v1.auth.token.RefreshToken;
import run.attraction.api.v1.auth.token.exception.InvalidTokenException;
import run.attraction.api.v1.auth.token.exception.TokenNotFoundException;
import run.attraction.api.v1.auth.token.jwt.JwtService;
import run.attraction.api.v1.auth.token.repository.LogoutAccessTokenRepository;
import run.attraction.api.v1.auth.token.repository.RefreshTokenRepository;
import run.attraction.api.v1.user.User;
import run.attraction.api.v1.user.repository.UserRepository;

@Component
@RequiredArgsConstructor
public class AuthProviderAndTokenHelper {
  private final JwtService jwtService;
  private final RefreshTokenRepository refreshTokenRepository;
  private final LogoutAccessTokenRepository logoutAccessTokenRepository;
  private final UserRepository userRepository;

  @Transactional
  public UserTokenDto getTokenAndRegisterUserByAuthUser(User authUser) {
    final Optional<User> findUser = userRepository.findByEmail(authUser.getEmail());
    if (findUser.isPresent()) {
      return getToken(findUser.get(), true);
    }
    userRepository.save(authUser);
    return getToken(authUser, false);
  }

  public UserTokenDto getToken(User preparedJoinUser, boolean isUserBefore) {
    final String accessToken = jwtService.generateAccessToken(preparedJoinUser, new Date(System.nanoTime()));
    final String refreshToken = jwtService.generateRefreshToken(preparedJoinUser, new Date(System.nanoTime()));

    renewRefreshToken(preparedJoinUser, refreshToken);
    return UserTokenDto.builder()
        .accessToken(accessToken)
        .refreshToken(refreshToken)
        .id(preparedJoinUser.getId())
        .isUserBefore(isUserBefore)
        .build();
  }

  private void renewRefreshToken(User user, String refreshToken) {
    RefreshToken token = refreshTokenRepository.findTokenByUser(user)
        .orElse(createRefreshToken(user, refreshToken));

    token.updateToken(refreshToken);
    refreshTokenRepository.save(token);
  }

  private RefreshToken createRefreshToken(User user, String refreshToken) {
    return RefreshToken.builder()
        .user(user)
        .token(refreshToken)
        .build();
  }

  public void saveAccessTokenAndDeleteRefreshToken(final String accessToken) {
    System.out.println(1111111);
    String userEmail = jwtService.extractEmailFromToken(accessToken);
    saveLogoutAccessToken(accessToken);
    refreshTokenRepository.findTokenByUserEmail(userEmail)
        .ifPresent(this::deleteRefreshToken);
  }

  private void saveLogoutAccessToken(final String accessToken) {
    final long expireTimeFromToken = jwtService.getExpireTimeFromToken(accessToken);
    final LogoutAccessToken logoutAccessToken = LogoutAccessToken.builder().id(accessToken)
        .expiration(expireTimeFromToken).build();
    logoutAccessTokenRepository.save(logoutAccessToken);
  }

  private void deleteRefreshToken(RefreshToken refreshToken) {
    refreshTokenRepository.delete(refreshToken);
  }

  public UserTokenDto reissueToken(String refreshToken, Date issuedAt) {
    User user = CheckValidTokenAndGetUser(refreshToken);

    String accessToken = jwtService.generateAccessToken(user, issuedAt);
    String reissueToken = jwtService.generateRefreshToken(user, issuedAt);
    reissueRefreshToken(user, reissueToken);
    return UserTokenDto.builder()
        .accessToken(accessToken)
        .refreshToken(reissueToken)
        .id(user.getId())
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