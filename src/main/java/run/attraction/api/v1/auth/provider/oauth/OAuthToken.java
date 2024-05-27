package run.attraction.api.v1.auth.provider.oauth;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class OAuthToken {
  private String access_token;
  private String refresh_token;
}
