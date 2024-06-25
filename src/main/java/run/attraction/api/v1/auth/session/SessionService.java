package run.attraction.api.v1.auth.session;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import run.attraction.api.v1.auth.service.dto.UserStateDto;
import run.attraction.api.v1.auth.session.dto.UserDetailBySession;
import run.attraction.api.v1.auth.session.exception.SessionIdNotFoundException;
import run.attraction.api.v1.user.repository.UserRepository;

@Slf4j
@Component
@RequiredArgsConstructor
public class SessionService {
  public static final String LOGIN_MEMBER = "LOGIN_MEMBER";

  private final UserRepository userRepository;

  public void getSession(HttpServletRequest request, UserStateDto userStateDto){
    HttpSession session = request.getSession();
    session.setAttribute(LOGIN_MEMBER,userStateDto.getEmail());
  }

  public void removeSession(HttpServletRequest request){
    HttpSession session = request.getSession(false);
    if (session != null) {
      session.invalidate();
    }
  }

  public UserDetailBySession getUserDetail(HttpServletRequest request){
    HttpSession session = request.getSession(false);
    if (session == null){
      throw new SessionIdNotFoundException();
    }
    String email = (String)session.getAttribute(LOGIN_MEMBER);
    return userRepository.getUserDetailsByEmail(email);
  }
}
