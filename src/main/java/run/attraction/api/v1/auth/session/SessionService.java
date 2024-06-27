package run.attraction.api.v1.auth.session;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import run.attraction.api.v1.auth.service.dto.UserStateDto;
import run.attraction.api.v1.auth.session.dto.UserDetailBySession;
import run.attraction.api.v1.auth.session.exception.InValidUserException;
import run.attraction.api.v1.auth.session.exception.ResignedUserException;
import run.attraction.api.v1.auth.session.exception.SessionNotFoundException;
import run.attraction.api.v1.user.User;
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
    log.info("getCreationTime={}",session.getCreationTime());
    log.info("getAttributeNames={}",session.getAttributeNames());
    log.info("getLastAccessedTime={}",session.getLastAccessedTime());
    log.info("isNew={}",session.isNew());
    log.info("getMaxInactiveInterval={}",session.getMaxInactiveInterval());
    log.info("getServletContext={}",session.getServletContext());
    log.info("getId={}",session.getId());
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
      throw new SessionNotFoundException();
    }
    String email = (String)session.getAttribute(LOGIN_MEMBER);
    return userRepository.getUserDetailsByEmail(email);
  }

  public HttpSession getSession(HttpServletRequest request){
    HttpSession session = request.getSession(false);
    log.info("getCreationTime={}",session.getCreationTime());
    log.info("getAttributeNames={}",session.getAttributeNames());
    log.info("getLastAccessedTime={}",session.getLastAccessedTime());
    log.info("isNew={}",session.isNew());
    log.info("getMaxInactiveInterval={}",session.getMaxInactiveInterval());
    log.info("getServletContext={}",session.getServletContext());
    log.info("getId={}",session.getId());
    if (session == null || session.getAttribute(LOGIN_MEMBER) == null){
      throw new SessionNotFoundException();
    }
    return session;
  }

  public String getUserEmail(HttpSession session){
    String userEmail = (String)session.getAttribute(LOGIN_MEMBER);
    if(userEmail==null) {
      throw new InValidUserException();
    }
    return userEmail;
  }

  public boolean isUser(String email){
    User user = userRepository.findById(email)
        .orElseThrow(InValidUserException::new);

    if (user.isDeleted()) {
      throw new ResignedUserException();
    }

    return true;
  }
}
