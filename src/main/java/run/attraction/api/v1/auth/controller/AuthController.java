package run.attraction.api.v1.auth.controller;

import jakarta.servlet.http.HttpServletResponse;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import run.attraction.api.v1.auth.service.AuthService;
import run.attraction.api.v1.auth.service.dto.UserTokenDto;
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
  public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto loginRequestDto,
                                                HttpServletResponse response) {
    final Optional<UserTokenDto> userTokenDtoOptional = authService.login(loginRequestDto.getProvider(),
        loginRequestDto.getCode());
//    final Optional<UserTokenDto> userTokenDtoOptional = authService.login("google", code);

    if (userTokenDtoOptional.isEmpty()) {
      return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    final UserTokenDto userTokenDto = userTokenDtoOptional.get();

    cookieTokenSetter.setCookieToken(response, userTokenDto.getRefreshToken());

    final LoginResponseDto responseDto = LoginResponseDto.builder()
        .userId(userTokenDto.getId())
        .hasExtraDetails(false)
        .build();

    return ResponseEntity.ok(responseDto);

  }
}
