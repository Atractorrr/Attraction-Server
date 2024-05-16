package run.attraction.api.v1.auth.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import run.attraction.api.v1.auth.service.AuthService;
import run.attraction.api.v1.auth.service.dto.UserTokenDto;
import run.attraction.api.v1.auth.service.dto.join.JoinRequestDto;
import run.attraction.api.v1.auth.service.dto.login.LoginResponseDto;
import run.attraction.api.v1.auth.token.CookieTokenSetter;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {

  private final AuthService authService;
  private final CookieTokenSetter cookieTokenSetter;

  @PostMapping("/login")
  public ResponseEntity<LoginResponseDto> login(@Valid @RequestParam String code,
                                                HttpServletResponse response) {
//    final Optional<UserTokenDto> userTokenDtoOptional =
//        authService.login(loginRequestDto.getProvider(), loginRequestDto.getCode());
    final Optional<UserTokenDto> userTokenDtoOptional = authService.login("google", code);

    if (userTokenDtoOptional.isEmpty()) {
      return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    final UserTokenDto userTokenDto = userTokenDtoOptional.get();

    cookieTokenSetter.setCookieToken(response, userTokenDto.getRefreshToken());

    final LoginResponseDto responseDto = LoginResponseDto.builder()
        .userId(userTokenDto.getId())
        .hasExtraDetails(false)
        .accessToken(userTokenDto.getAccessToken())
        .build();

    return ResponseEntity.ok(responseDto);

  }

  @GetMapping("/join/username-duplicate")
  public boolean checkNicknameDuplication(@Valid @RequestParam String nickname) {
    return authService.checkNicknameDuplication(nickname);
  }

  @PostMapping("/join")
  public ResponseEntity<?> join(@Valid @RequestBody JoinRequestDto joinRequestDto) {
    authService.join(joinRequestDto);
    return ResponseEntity.ok().build();
  }

//  @GetMapping("/logout")
//  public ResponseEntity<?> logout(@RequestParam String accessToken, HttpServletResponse response) {
//    authService.logout(accessToken);
//    cookieTokenSetter.expireCookieToken(response);
//    return ResponseEntity.ok().build();
//  }

  @GetMapping("/logout")
  public ResponseEntity<?> logout(HttpServletRequest request, HttpServletResponse response) {
    final String accessToken = cookieTokenSetter.getBearerTokenFromRequest(request);
    authService.logout(accessToken);
    cookieTokenSetter.expireCookieToken(response);
    return ResponseEntity.ok().build();
  }
}
