package run.attraction.api.v1.auth.provider;

import run.attraction.api.v1.auth.provider.oauth.OAuthService;
import run.attraction.api.v1.auth.provider.oauth.OAuthToken;
import run.attraction.api.v1.user.User;

import java.util.List;

public class AuthProvider {

  private final List<OAuthService> OAuthServices;

  public AuthProvider(List<OAuthService> OAuthServices) {
    this.OAuthServices = OAuthServices;
  }

  public User getUserProfileByCode(String provider, String code) {
    OAuthService oAuthService = getOAuthService(provider);
    final OAuthToken token = oAuthService.getToken(code);
    final String responseBody = oAuthService.getResponseBody(token.getAccess_token());
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
