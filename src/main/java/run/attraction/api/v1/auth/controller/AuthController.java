package run.attraction.api.v1.auth.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import run.attraction.api.v1.auth.service.AuthService;
import run.attraction.api.v1.auth.service.dto.ReissueTokenResponseDto;
import run.attraction.api.v1.auth.service.dto.UserTokenDto;
import run.attraction.api.v1.auth.service.dto.join.JoinRequestDto;
import run.attraction.api.v1.auth.service.dto.login.FirstLoginResponseDto;
import run.attraction.api.v1.auth.service.dto.login.LoginRequestDto;
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
  public ResponseEntity<?> login(@RequestBody LoginRequestDto loginRequestDto,
                                 HttpServletResponse response) {
    log.info("code={}", loginRequestDto.getCode());
//    String code = new String(Base64.getEncoder().encode(
//        loginRequestDto.getCode().getBytes(StandardCharsets.UTF_8)));
    final UserTokenDto userTokenDto = authService.login(loginRequestDto.getProvider(),loginRequestDto.getCode());
    cookieTokenSetter.setCookieToken(response, userTokenDto.getRefreshToken());
    if (userTokenDto.isUserBefore()) {
      return ResponseEntity.status(HttpStatus.CREATED).body(
          new LoginResponseDto(userTokenDto.getEmail(),userTokenDto.getAccessToken(), userTokenDto.getShouldReissueToken()));
    }
    return ResponseEntity.ok(FirstLoginResponseDto.builder()
        .email(userTokenDto.getEmail())
        .hasExtraDetails(false)
        .accessToken(userTokenDto.getAccessToken())
        .build());
  }

  @PostMapping("/join/username-duplicate")
  public ResponseEntity<?> checkNicknameDuplication(@Valid @RequestParam String nickname) {
    if(authService.checkNicknameDuplication(nickname)){
      return ResponseEntity.status(HttpStatus.CONFLICT).body("중복된 닉네임 입니다.");
    };
    return ResponseEntity.ok().build();
  }

  @PostMapping("/join")
  public ResponseEntity<?> join(@Valid @RequestBody JoinRequestDto joinRequestDto) {
    authService.join(joinRequestDto);
    return ResponseEntity.ok().build();
  }


  @PostMapping("/logout")
  public ResponseEntity<?> logout(HttpServletRequest request, HttpServletResponse response) {
    final String accessToken = cookieTokenSetter.getBearerTokenFromRequest(request);
    authService.logout(accessToken);
    cookieTokenSetter.expireCookieToken(response);
    return ResponseEntity.ok().build();
  }

  @GetMapping("/reissue-token")
  public ResponseEntity<ReissueTokenResponseDto> reissueToken(@CookieValue("Refresh-Token") String refreshToken,
                                                              HttpServletResponse response) {
    final UserTokenDto userTokenDto = authService.reissueToken(refreshToken, new Date(System.nanoTime()));
    cookieTokenSetter.setCookieToken(response, userTokenDto.getRefreshToken());
    return ResponseEntity.ok(new ReissueTokenResponseDto(userTokenDto.getAccessToken()));
  }

}
