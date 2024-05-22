package run.attraction.api.v1.auth.token;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

@Component
public class CookieTokenSetter {
  private static final String KEY = "Refresh-Token";

  private static final int EXPIRE_TIME = 60 * 60 * 2;
//  private static final int EXPIRE_TIME = 7 * 24 * 60 * 60;

  @Value("${application.security.jwt.refresh-token.expiration}")
  private long expireTime;

  public void setCookieToken(HttpServletResponse response, String refreshToken) {

    Cookie cookie = new Cookie(KEY, refreshToken);

    cookie.setMaxAge((int) expireTime);
    cookie.setMaxAge(EXPIRE_TIME);
    // Test용
//    cookie.setSecure(true);
//    cookie.setHttpOnly(true);
    cookie.setPath("/");
    response.addCookie(cookie);
  }

  public void expireCookieToken(HttpServletResponse response) {
    Cookie cookie = new Cookie(KEY, null);
    cookie.setMaxAge(0);
    response.addCookie(cookie);
  }

  public String getBearerTokenFromRequest(HttpServletRequest request) {
    String header = request.getHeader(HttpHeaders.AUTHORIZATION);
    return header.substring(7);
  }

}
