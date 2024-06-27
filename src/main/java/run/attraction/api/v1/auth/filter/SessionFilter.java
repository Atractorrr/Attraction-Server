package run.attraction.api.v1.auth.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import run.attraction.api.v1.auth.session.SessionService;
import run.attraction.api.v1.auth.session.exception.InValidUserException;
import run.attraction.api.v1.auth.session.exception.ResignedUserException;
import run.attraction.api.v1.auth.session.exception.SessionNotFoundException;
import run.attraction.api.v1.user.service.UserDetailsServiceForSecurity;

@Slf4j
@Component
@RequiredArgsConstructor
public class SessionFilter extends OncePerRequestFilter {
  public static final String LOGIN_MEMBER = "LOGIN_MEMBER";
  private static final List<String> WHITE_LIST = List.of(
      "/api/v1/auth/login",
      "/api/v1/auth/google",
      "/api/v1/newsletters/",
      "/api/v1/search/",
      "/api/v1/rank/",
      "/favicon.ico"
  );

  private final SessionService sessionService;
  private final FilterExceptionHandler filterExceptionHandler;
  private final UserDetailsServiceForSecurity userDetailsService;

  @Override
  protected void doFilterInternal(HttpServletRequest request,
                                  HttpServletResponse response,
                                  FilterChain filterChain
  ) throws ServletException, IOException {
    try {

      if (isWhiteList(request.getServletPath())) {
        filterChain.doFilter(request, response);
        return;
      }

      //session 검사
      HttpSession session = sessionService.getSession(request);
      String userEmail = sessionService.getUserEmail(session);
      if(sessionService.isUser(userEmail)) {
        if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
          UserDetails userDetails = userDetailsService.loadUserByUsername(userEmail);

          UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
              userDetails,
              null,
              userDetails.getAuthorities());

          authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
          SecurityContextHolder.getContext().setAuthentication(authToken);
        }
      }
      filterChain.doFilter(request, response);
    } catch (SessionNotFoundException e) {
      filterExceptionHandler.sendExceptionMessage(response,ErrorType.SESSION_NOT_FOUND);
    } catch(InValidUserException e){
      filterExceptionHandler.sendExceptionMessage(response, ErrorType.SESSION_INVALID__USER);
    } catch(ResignedUserException e){
      filterExceptionHandler.sendExceptionMessage(response, ErrorType.SESSION_RESIGNED_USER);
    }
  }

  private boolean isWhiteList(String path) {
    return WHITE_LIST.stream().anyMatch(path::startsWith);
  }
}
