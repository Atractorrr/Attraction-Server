package run.attraction.api.v1.auth.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
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
import run.attraction.api.v1.auth.filter.SessionFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

  private final AuthenticationProvider authenticationProvider;
  private final SessionFilter sessionFilter;

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

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    return http
        .csrf(AbstractHttpConfigurer::disable)
        .cors(configurer ->
            configurer.configurationSource(corsConfigurationSource()))
        .formLogin(AbstractHttpConfigurer::disable)
        .logout(logout -> logout
            .logoutUrl("api/v1/auth/logout")
            .deleteCookies(COOKIE_KEY))
        .httpBasic(AbstractHttpConfigurer::disable)
        .authorizeHttpRequests(req -> {
          req.requestMatchers(WHITE_LIST).permitAll();
          req.requestMatchers(monitoringPath).permitAll();
          req.anyRequest().authenticated();
        })
        .authenticationProvider(authenticationProvider)
        .addFilterBefore(sessionFilter, UsernamePasswordAuthenticationFilter.class)
        .build();
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