package run.attraction.api.v1.auth.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import run.attraction.api.v1.auth.token.exception.InvalidTokenException;
import run.attraction.api.v1.auth.token.exception.TokenExpirationException;
import run.attraction.api.v1.auth.token.jwt.JwtService;
import run.attraction.api.v1.auth.token.repository.LogoutAccessTokenRepository;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

  private final JwtService jwtService;
  private final UserDetailsService customUserDetailsService;
  private final LogoutAccessTokenRepository logoutAccessTokenRepository;
  private final FilterExceptionHandler filterExceptionHandler;

  @Override
  protected void doFilterInternal(
      @NonNull HttpServletRequest request,
      @NonNull HttpServletResponse response,
      @NonNull FilterChain filterChain
  ) throws ServletException, IOException {
    final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
    final String accessToken = authHeader.substring(7);

    // 체크 사항 1 ) 로그아웃된 access Token으로 접근할때
    if (logoutAccessTokenRepository.existsById(accessToken)) {
      filterExceptionHandler.sendExceptionMessage(response, ErrorType.LOGOUT_ACCESS_TOKEN);
      return;
    }
    try {
      final String userEmail = jwtService.extractEmailFromToken(accessToken);
      if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
        setAuthentication(request, userEmail, accessToken);
      }
      filterChain.doFilter(request, response);
    } catch (TokenExpirationException e) {
      filterExceptionHandler.sendExceptionMessage(response, ErrorType.EXPIRED_TOKEN);
    } catch (InvalidTokenException e) {
      filterExceptionHandler.sendExceptionMessage(response, ErrorType.NOT_VALID_TOKEN);
    } catch (UsernameNotFoundException e) {
      filterExceptionHandler.sendExceptionMessage(response, ErrorType.NOT_FOUND_USER);
    }

  }

  private void setAuthentication(HttpServletRequest request, String userEmail, String accessToken) {
    UserDetails userDetails = customUserDetailsService.loadUserByUsername(userEmail);

    if (jwtService.isTokenValid(accessToken, userDetails)) {
      UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
          userDetails,
          null,
          userDetails.getAuthorities()
      );
      authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
      SecurityContextHolder.getContext().setAuthentication(authToken);
    }
  }
}
