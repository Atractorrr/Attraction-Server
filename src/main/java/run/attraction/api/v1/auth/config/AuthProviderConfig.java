package run.attraction.api.v1.auth.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import run.attraction.api.v1.auth.provider.AuthProvider;
import run.attraction.api.v1.auth.provider.google.GoogleOAuthService;

import java.util.Arrays;
import java.util.List;
import run.attraction.api.v1.auth.provider.oauth.OAuthService;

@Configuration
@RequiredArgsConstructor
public class AuthProviderConfig {

  private final GoogleOAuthService googleOAuthService;

  @Bean
  public AuthProvider authProvider() {
    return new AuthProvider(getExternalProviders());
  }

  private List<OAuthService> getExternalProviders() {
    return Arrays.asList(
        googleOAuthService
    );
  }
}