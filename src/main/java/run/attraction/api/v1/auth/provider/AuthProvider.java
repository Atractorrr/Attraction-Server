package run.attraction.api.v1.auth.provider;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import run.attraction.api.v1.auth.provider.oauth.OAuthService;
import run.attraction.api.v1.auth.provider.oauth.OAuthToken;
import run.attraction.api.v1.auth.token.GoogleRefreshToken;
import run.attraction.api.v1.auth.token.repository.GoogleRefreshTokenRepository;
import run.attraction.api.v1.user.User;

public class AuthProvider {

  private static final Logger log = LoggerFactory.getLogger(AuthProvider.class);
  private final List<OAuthService> OAuthServices;
  private final GoogleRefreshTokenRepository googleRefreshTokenRepository;

  public AuthProvider(List<OAuthService> OAuthServices, GoogleRefreshTokenRepository googleRefreshTokenRepository) {
    this.OAuthServices = OAuthServices;
    this.googleRefreshTokenRepository = googleRefreshTokenRepository;
  }

  public User getUserProfileByCode(String provider, final String code) {
    OAuthService oAuthService = getOAuthService(provider);

    log.info("code로 구글 Token 받기 시작");
    final OAuthToken token = oAuthService.getToken(code);
    log.info("code로 구글 Token 받기 완료");
    log.info("googleRefreshToken = {}", token.getRefresh_token());
    log.info("googleAccessToken = {}", token.getAccess_token());

    log.info("googleAccessToken으로 구글 유저정보 받기 시작");
    final String responseBody = oAuthService.getResponseBody(token.getAccess_token());
    log.info("googleAccessToken으로 구글 유저정보 받기 완료");

    final User authUser = oAuthService.getAuthUser(responseBody);
    log.info("유저 eamil = {}", authUser.getEmail());

    String googleRefreshToken = token.getRefresh_token();
    if (!(googleRefreshToken==null)) {
      log.info("googleRefreshToken가 존재합니다. 구글 refreshToken을 저장합니다.");
      saveGoogleRefreshToken(googleRefreshToken, authUser.getEmail());
    }
    return authUser;
  }

  private void saveGoogleRefreshToken(String refreshToken, String email) {
    log.info("saveGoogleRefreshToken 진입");
    googleRefreshTokenRepository.findInvalidTokenByEmail(email)
        .ifPresentOrElse(
            token -> updateGoogleRefreshToken(token, refreshToken),
            () -> saveNewGoogleRefreshToken(refreshToken, email)
        );
  }

  private void saveNewGoogleRefreshToken(String refreshToken, String email) {
    log.info("첫 로그인한 유저입니다.");
    final GoogleRefreshToken googleRefreshToken = GoogleRefreshToken.builder()
        .token(refreshToken)
        .email(email)
        .build();
    googleRefreshTokenRepository.save(googleRefreshToken);
    log.info("Google refresh Token 저장완료.");
    log.info("Google refresh Token 저장완료.");

  }

  private void updateGoogleRefreshToken(GoogleRefreshToken googleRefreshToken, String refreshToken) {
    log.info("기존에 있던 유저 입니다. Google refresh Token을 갱신합니다.");
    googleRefreshToken.updateStateAndToken(false, refreshToken);
    googleRefreshTokenRepository.save(googleRefreshToken);
    log.info("Google refresh Token 갱신완료.");
  }

  private OAuthService getOAuthService(String provider) {
    return OAuthServices.stream()
        .filter(service -> service.getServiceName().equals(provider))
        .findAny()
        .orElseThrow(() -> {
          throw new IllegalStateException("일치하는 API가 없습니다.");
        });
  }

}
