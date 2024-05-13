package run.attraction.api.v1.auth.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import run.attraction.api.v1.auth.config.properties.GoogleLoginProperties;
import run.attraction.api.v1.auth.provider.google.GoogleOAuth;

@Configuration
@RequiredArgsConstructor
@EnableConfigurationProperties(GoogleLoginProperties.class)
public class GoogleLoginConfig {

  private final GoogleLoginProperties googleLoginProperties;

  @Bean
  public GoogleOAuth googleOAuth(){
    return GoogleOAuth.builder()
        .clientId(googleLoginProperties.getClientId())
        .clientSecret(googleLoginProperties.getClientSecret())
        .grantType(googleLoginProperties.getAuthorizationGrantType())
        .redirectUri(googleLoginProperties.getRedirectUri())
        .tokenUrl(googleLoginProperties.getTokenUri())
        .userInfoUri(googleLoginProperties.getUserInfoUri())
        .build();
  }
}
