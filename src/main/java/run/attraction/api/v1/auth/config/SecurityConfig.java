package run.attraction.api.v1.auth.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import run.attraction.api.v1.auth.config.condition.SessionFilterCondition;
import run.attraction.api.v1.auth.filter.FilterExceptionHandler;
import run.attraction.api.v1.auth.filter.SessionFilter;
import run.attraction.api.v1.auth.session.SessionService;
import run.attraction.api.v1.user.Role;
import run.attraction.api.v1.user.service.UserDetailsServiceForSecurity;

@Slf4j
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

  private final AuthenticationProvider authenticationProvider;
  private final SessionService sessionService;
  private final FilterExceptionHandler filterExceptionHandler;
  private final UserDetailsServiceForSecurity userDetailsService;

  private static final String COOKIE_KEY = "JSESSIONID";
  private static final String[] WHITE_LIST = {
      "/api/v1/auth/login",
      "/api/v1/auth/google",
      "/api/v1/newsletters/**",
      "/api/v1/search/**",
      "/api/v1/rank/**",
      "/favicon.ico"
      };

  @Value("${path.monitoring}")
  public String monitoringPath;

  @Value("${enable.session.filter}")
  private boolean enableSessionFilter;

  @Bean
  @Conditional(SessionFilterCondition.class)
  public SessionFilter sessionFilter(SessionService sessionService,
                                     FilterExceptionHandler filterExceptionHandler,
                                     UserDetailsServiceForSecurity userDetailsService) {
    return new SessionFilter(sessionService, filterExceptionHandler, userDetailsService);
  }

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    HttpSecurity securityChain = http
        .csrf(AbstractHttpConfigurer::disable)
        .cors(configurer ->
            configurer.configurationSource(corsConfigurationSource()))
        .formLogin(AbstractHttpConfigurer::disable)
        .logout(logout -> logout
            .logoutUrl("api/v1/auth/logout")
            .deleteCookies(COOKIE_KEY))
        .httpBasic(AbstractHttpConfigurer::disable)
        .authorizeHttpRequests(req -> {
          if (enableSessionFilter) {
            req.requestMatchers(WHITE_LIST).permitAll();
            req.requestMatchers("/swagger", "/api-docs", "/swagger-ui/**").hasRole(Role.ADMIN.name());
            req.requestMatchers(monitoringPath).permitAll();
            req.anyRequest().authenticated();
          } else {
            req.anyRequest().permitAll();
          }
        });
    if(enableSessionFilter){
      securityChain.addFilterBefore(sessionFilter(sessionService, filterExceptionHandler, userDetailsService), UsernamePasswordAuthenticationFilter.class);
      securityChain.authenticationProvider(authenticationProvider);
    }

    return securityChain.build();
  }

  @Bean
  public CorsConfigurationSource corsConfigurationSource() {
    CorsConfiguration configuration = new CorsConfiguration();
    configuration.addAllowedOriginPattern("*"); // 모든 origin 허용, 필요에 따라 특정 origin으로 변경 가능
    configuration.addAllowedMethod("*");
    configuration.addAllowedHeader("*"); // 모든 헤더 허용
    configuration.setAllowCredentials(true);
    configuration.setMaxAge(3600L);

    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", configuration);
    return source;
  }

}