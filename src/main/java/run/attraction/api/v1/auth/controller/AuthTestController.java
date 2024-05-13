package run.attraction.api.v1.auth.Controller;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import run.attraction.api.v1.auth.provider.AuthProvider;
import run.attraction.api.v1.auth.provider.google.GoogleOAuthService;
import run.attraction.api.v1.auth.provider.oauth.OAuthToken;
import run.attraction.api.v1.user.User;

@RestController
@RequiredArgsConstructor
public class AuthTestController {

  /*
   * Test용 Controller
   */
  private final GoogleOAuthService googleOAuthService;
  private final AuthProvider authProvider;

  @Value("${spring.security.oauth2.client.registration.google.client-id}")
  private String googleClientId;
  @Value("${spring.security.oauth2.client.registration.google.redirect-uri}")
  private String googleRedirectUrl;

  @PostMapping("/api/v1/auth")
  public String loginUrlGoogle() {
    String reqUrl = "https://accounts.google.com/o/oauth2/v2/auth?client_id=" + googleClientId
        + "&redirect_uri=" + googleRedirectUrl
        + "&response_type=code&scope=email%20profile%20openid&access_type=offline";
    return reqUrl;
  }

  @GetMapping("/api/v1/auth/user")
  public String getUserProfile(@Param("code") String code) {
    final OAuthToken tokens = googleOAuthService.getToken(code);
    final String responseBody = googleOAuthService.getResponseBody(tokens.getAccess_token());
    JsonElement element = JsonParser.parseString(responseBody);
    return element.getAsJsonObject().get("email").getAsString() + " / " + element.getAsJsonObject().get("picture")
        .getAsString();
  }

}
