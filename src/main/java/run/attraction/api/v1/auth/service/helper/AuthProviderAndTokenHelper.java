package run.attraction.api.v1.auth.service.helper;

import java.util.Date;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import run.attraction.api.v1.auth.service.dto.UserTokenDto;
import run.attraction.api.v1.auth.token.RefreshToken;
import run.attraction.api.v1.auth.token.jwt.JwtService;
import run.attraction.api.v1.auth.token.repository.RefreshTokenRepository;
import run.attraction.api.v1.user.User;
import run.attraction.api.v1.user.repository.UserRepository;

@Component
@RequiredArgsConstructor
public class AuthProviderAndTokenHelper {
  private final JwtService jwtService;
  private final RefreshTokenRepository refreshTokenRepository;
  private final UserRepository userRepository;

  @Transactional
  public Optional<UserTokenDto> getTokenAndRegisterUserByAuthUser(User authUser){
    final Optional<User> findUser = userRepository.findByEmail(authUser.getEmail());
    if (findUser.isPresent()){
      return Optional.empty();
    }
    userRepository.save(authUser);
    return getToken(authUser);
  }

  public Optional<UserTokenDto> getToken(User preparedJoinUser) {
    final String accessToken = jwtService.generateAccessToken(preparedJoinUser, new Date(System.nanoTime()));
    final String refreshToken = jwtService.generateRefreshToken(preparedJoinUser, new Date(System.nanoTime()));

    renewRefreshToken(preparedJoinUser, refreshToken);
    return Optional.of(UserTokenDto.builder()
        .accessToken(accessToken)
        .refreshToken(refreshToken)
        .id(preparedJoinUser.getId())
        .build());
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
}
