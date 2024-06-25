package run.attraction.api.v1.auth.session;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import run.attraction.api.v1.auth.service.dto.UserStateDto;

@Slf4j
@Component
public class SessionService {
  public static final String LOGIN_MEMBER = "LOGIN_MEMBER";

  public void getSession(HttpServletRequest request, UserStateDto userStateDto){
    HttpSession session = request.getSession();
    session.setAttribute("LOGIN_MEMBER",userStateDto.getEmail());
  }

  public void removeSession(HttpServletRequest request){
    HttpSession session = request.getSession(false);
    if (session != null) {
      session.invalidate();
    }
  }
}
