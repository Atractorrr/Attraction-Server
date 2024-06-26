package run.attraction.api.v1.auth.controller;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import jakarta.servlet.http.HttpServletRequest;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import run.attraction.api.v1.auth.provider.AuthProvider;
import run.attraction.api.v1.auth.provider.google.GoogleOAuthService;
import run.attraction.api.v1.auth.provider.oauth.OAuthToken;
import run.attraction.api.v1.mypage.service.dto.MessageResponse;
import run.attraction.api.v1.user.UserDetail;
import run.attraction.api.v1.user.repository.UserDetailRepository;

@RestController
@RequiredArgsConstructor
@Slf4j
public class AuthTestController {

  /*
   * Test용 Controller
   */
  private final GoogleOAuthService googleOAuthService;
  private final AuthProvider authProvider;
  private final UserDetailRepository userDetailRepository;

  @Value("${spring.security.oauth2.client.registration.google.client-id}")
  private String googleClientId;
  @Value("${spring.security.oauth2.client.registration.google.redirect-uri}")
  private String googleRedirectUrl;

  private static final String MAIL_GOOGLE_COM = "https://mail.google.com/";
  private static final String GMAIL_READONLY = "https://www.googleapis.com/auth/gmail.readonly";
  private static final String GMAIL_LABELS = "https://www.googleapis.com/auth/gmail.labels";
  private static final String GMAIL_SETTINGS_BASIC = "https://www.googleapis.com/auth/gmail.settings.basic";

  @PostMapping("/api/v1/auth")
  public String loginUrlGoogle() {
    String reqUrl = "https://accounts.google.com/o/oauth2/v2/auth?client_id=" + googleClientId
        + "&redirect_uri=" + googleRedirectUrl
        + "&response_type=code&scope=email profile openid "
        + MAIL_GOOGLE_COM + " "
        + GMAIL_READONLY + " "
        + GMAIL_LABELS + " "
        + GMAIL_SETTINGS_BASIC + " "
        + "https://www.googleapis.com/auth/userinfo.email" + " "
        + "https://www.googleapis.com/auth/userinfo.profile" + " "
        +"&access_type=offline";
    return reqUrl;
  }

  @GetMapping("/api/v1/auth/google")
  public String getCode(@RequestParam String code) {
    return code;
  }

  @GetMapping("/api/v1/auth/user")
  public String getUserProfile(@Param("code") String code) {
    final OAuthToken token = googleOAuthService.getToken(code);
    final String responseBody = googleOAuthService.getResponseBody(token.getAccess_token());
    JsonElement element = JsonParser.parseString(responseBody);
    return element.getAsJsonObject().get("email").getAsString() + " / " + element.getAsJsonObject().get("picture")
        .getAsString();
  }

  @PostMapping("/api/v1/auth/{email}")
  public ResponseEntity<MessageResponse> deleteUserDetail(@PathVariable(value = "email") String email){
    final Optional<UserDetail> userDetail = userDetailRepository.findById(email);
    if(userDetail.isPresent()){
      userDetailRepository.delete(userDetail.get());
    }
    return ResponseEntity.ok(new MessageResponse("삭제 완료"));
  }

  @PostMapping("/api/test")
  public ResponseEntity<MessageResponse> login(HttpServletRequest request){
    log.info("Request 헤더 확인하기 API 시작");
    log.info("cookie = {}", request.getHeader("Cookie"));
    log.info("Request 헤더 확인하기 API 완료");
    return ResponseEntity.ok(new MessageResponse("성공"));
  }
}
