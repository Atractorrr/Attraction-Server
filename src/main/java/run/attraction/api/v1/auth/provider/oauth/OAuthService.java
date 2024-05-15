package run.attraction.api.v1.auth.provider.oauth;

import run.attraction.api.v1.user.User;

public interface OAuthService {

  String getServiceName();

  OAuthToken getToken(String code);

  String getResponseBody(String accessToken);

  User getAuthUser(String responseBody);

}
