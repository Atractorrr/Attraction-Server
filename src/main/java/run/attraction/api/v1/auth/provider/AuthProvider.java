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
    final OAuthToken token = oAuthService.getToken(code);
    final String responseBody = oAuthService.getResponseBody(token.getAccess_token());
    final User authUser = oAuthService.getAuthUser(responseBody);

    String googleRefreshToken = token.getRefresh_token();
    log.info("email = {}", authUser.getEmail());
    log.info("googleRefreshToken = {}", googleRefreshToken);
    if (!(googleRefreshToken==null)) {
      saveGoogleRefreshToken(googleRefreshToken, authUser.getEmail());
    }
    return authUser;
  }

  private void saveGoogleRefreshToken(String refreshToken, String email) {
    googleRefreshTokenRepository.findInvalidTokenByEmail(email)
        .ifPresentOrElse(
            token -> updateGoogleRefreshToken(token, refreshToken),
            () -> saveNewGoogleRefreshToken(refreshToken, email)
        );
  }

  private void saveNewGoogleRefreshToken(String refreshToken, String email) {
    final GoogleRefreshToken googleRefreshToken = GoogleRefreshToken.builder()
        .token(refreshToken)
        .email(email)
        .build();
    googleRefreshTokenRepository.save(googleRefreshToken);
  }

  private void updateGoogleRefreshToken(GoogleRefreshToken googleRefreshToken, String refreshToken) {
    googleRefreshToken.updateStateAndToken(false, refreshToken);
    googleRefreshTokenRepository.save(googleRefreshToken);
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
