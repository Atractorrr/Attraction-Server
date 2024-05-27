package run.attraction.api.v1.auth.provider;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import run.attraction.api.v1.auth.provider.oauth.OAuthService;
import run.attraction.api.v1.auth.provider.oauth.OAuthToken;
import run.attraction.api.v1.user.User;

public class AuthProvider {

  private static final Logger log = LoggerFactory.getLogger(AuthProvider.class);
  private final List<OAuthService> OAuthServices;

  public AuthProvider(List<OAuthService> OAuthServices) {
    this.OAuthServices = OAuthServices;
  }

  public User getUserProfileByCode(String provider, final String code) {
    OAuthService oAuthService = getOAuthService(provider);
    final OAuthToken token = oAuthService.getToken(code);
    final String responseBody = oAuthService.getResponseBody(token.getAccess_token());
    //
    return oAuthService.getAuthUser(responseBody);
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
