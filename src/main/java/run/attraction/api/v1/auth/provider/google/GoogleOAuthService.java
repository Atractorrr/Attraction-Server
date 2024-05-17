package run.attraction.api.v1.auth.provider.google;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import java.time.LocalDate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import run.attraction.api.v1.auth.provider.exception.GoogleApiAccessTokenException;
import run.attraction.api.v1.auth.provider.exception.GoogleApiCodeException;
import run.attraction.api.v1.auth.provider.oauth.OAuthService;
import run.attraction.api.v1.auth.provider.oauth.OAuthToken;
import run.attraction.api.v1.user.Role;
import run.attraction.api.v1.user.User;

@Slf4j
@Component
public class GoogleOAuthService implements OAuthService {

  private final GoogleOAuth googleOAuth;

  private static final String SERVICE_NAME = "google";


  public GoogleOAuthService(GoogleOAuth googleOAuth) {
    this.googleOAuth = googleOAuth;
  }

  @Override
  public String getServiceName() {
    return SERVICE_NAME;
  }

  @Override
  public OAuthToken getToken(final String code) {
    String tokenUrl = googleOAuth.getTokenUrl();
    var requestBody = googleOAuth.getTokenRequestBody(code);
    RestClient restClient = RestClient.create(tokenUrl);

    return restClient.post()
        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
        .body(requestBody)
        .retrieve()
        .onStatus(HttpStatusCode::is4xxClientError, (request, response) -> {
          throw new GoogleApiCodeException(response.getStatusCode(), response.getHeaders());
        })
        .toEntity(OAuthToken.class)
        .getBody();
  }

  @Override
  public String getResponseBody(String accessToken) {
    String userInfoUri = googleOAuth.getUserInfoUri();
    RestClient restClient = RestClient.create(userInfoUri);
    return restClient.get()
        .header(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken)
        .retrieve()
        .onStatus(HttpStatusCode::is4xxClientError, (req, rep) -> {
          throw new GoogleApiAccessTokenException(rep.getStatusCode(), rep.getHeaders());
        })
        .body(String.class);
  }

  @Override
  public User getAuthUser(String responseBody) {
    JsonElement element = JsonParser.parseString(responseBody);
    var email = element.getAsJsonObject().get("email").getAsString();
    var profileImg = element.getAsJsonObject().get("picture").getAsString();

    return User.builder()
        .email(email)
        .profileImg(profileImg)
        .backgroundImg(null)
        .createdAt(LocalDate.now())
        .modifiedAt(LocalDate.now())
        .role(Role.USER)
        .build();
  }

}
