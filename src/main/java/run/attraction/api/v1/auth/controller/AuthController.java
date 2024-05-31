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
    log.info("로그인 시작");
    log.info("요청 받은 code = {}", loginRequestDto.getCode());
    final UserTokenDto userTokenDto = authService.login(loginRequestDto.getProvider(),loginRequestDto.getCode());

    log.info("JWT 토큰 등록 및 유저 저장 완료");
    log.info("헤더에 토큰 담기 시작 진입");
    cookieTokenSetter.setCookieToken(response, userTokenDto.getRefreshToken());
    if (userTokenDto.isUserBefore()) {
      log.info("기존 유저에 대한 응답 response 전달(로그인 완료)");

      return ResponseEntity.status(HttpStatus.CREATED).body(
          new LoginResponseDto(userTokenDto.getEmail(),userTokenDto.getAccessToken(), userTokenDto.getShouldReissueToken()));
    }
    log.info("새로운 유저에 대한 응답 response 전달(로그인 완료)");
    return ResponseEntity.ok(FirstLoginResponseDto.builder()
        .email(userTokenDto.getEmail())
        .hasExtraDetails(false)
        .accessToken(userTokenDto.getAccessToken())
        .build());
  }

  @PostMapping("/join/username-duplicate")
  public ResponseEntity<?> checkNicknameDuplication(@Valid @RequestParam String nickname) {
    log.info("회원가입 닉네임 중복 체크 시작");
    if(authService.checkNicknameDuplication(nickname)){
      log.info("회원가입 닉네임 중복 체크 결과 = {}",authService.checkNicknameDuplication(nickname));
      return ResponseEntity.status(HttpStatus.CONFLICT).body("중복된 닉네임 입니다.");
    };
    log.info("회원가입 닉네임 중복 체크 결과 = {}",authService.checkNicknameDuplication(nickname));
    return ResponseEntity.ok().build();
  }

  @PostMapping("/join")
  public ResponseEntity<?> join(@Valid @RequestBody JoinRequestDto joinRequestDto) {
    log.info("[join] 추가정보 받기 시작");
    authService.join(joinRequestDto);
    log.info("[join] 추가정보 저장하기 완료");
    return ResponseEntity.ok().build();
  }


  @PostMapping("/logout")
  public ResponseEntity<?> logout(HttpServletRequest request, HttpServletResponse response) {
    log.info("로그아웃 시작");
    final String accessToken = cookieTokenSetter.getBearerTokenFromRequest(request);
    log.info("accessToken = {}", accessToken);
    authService.logout(accessToken);
    cookieTokenSetter.expireCookieToken(response);
    log.info("로그아웃 완료");
    return ResponseEntity.ok("로그아웃 완료되었습니다.");
  }

  @GetMapping("/reissue-token")
  public ResponseEntity<ReissueTokenResponseDto> reissueToken(@CookieValue("Refresh-Token") String refreshToken,
                                                              HttpServletResponse response) {
    final UserTokenDto userTokenDto = authService.reissueToken(refreshToken, new Date(System.nanoTime()));
    cookieTokenSetter.setCookieToken(response, userTokenDto.getRefreshToken());
    return ResponseEntity.ok(new ReissueTokenResponseDto(userTokenDto.getAccessToken()));
  }

}
