package run.attraction.api.v1.auth.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
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
import org.springframework.web.bind.annotation.RestController;
import run.attraction.api.v1.auth.service.AuthService;
import run.attraction.api.v1.auth.service.dto.ReissueTokenResponseDto;
import run.attraction.api.v1.auth.service.dto.UserTokenDto;
import run.attraction.api.v1.auth.service.dto.join.CheckDuplicationRequsetDto;
import run.attraction.api.v1.auth.service.dto.join.JoinRequestDto;
import run.attraction.api.v1.auth.service.dto.login.FirstLoginResponseDto;
import run.attraction.api.v1.auth.service.dto.login.LoginRequestDto;
import run.attraction.api.v1.auth.service.dto.login.LoginResponseDto;
import run.attraction.api.v1.auth.token.CookieTokenSetter;
import run.attraction.api.v1.mypage.service.dto.MessageResponse;

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
    final UserTokenDto userTokenDto = authService.login(loginRequestDto.getProvider(), loginRequestDto.getCode());
    log.info("JWT 토큰 등록 및 유저 저장 완료");

    cookieTokenSetter.setCookieToken(response, userTokenDto.getRefreshToken());

    if (userTokenDto.isUserBefore()) {
      log.info("기존 유저에 대한 응답 response 전달(로그인 완료)");
      return ResponseEntity.status(HttpStatus.CREATED).body(
          new LoginResponseDto(userTokenDto.getEmail(),
              userTokenDto.getAccessToken(),
              userTokenDto.getShouldReissueToken(),
              userTokenDto.isHasExtraDetails()
          ));
    }

    log.info("새로운 유저에 대한 응답 response 전달(로그인 완료)");
    return ResponseEntity.ok(FirstLoginResponseDto.builder()
        .email(userTokenDto.getEmail())
        .accessToken(userTokenDto.getAccessToken())
        .hasExtraDetails(false)
        .shouldReissueToken(false)
        .build());
  }

  @PostMapping("/join/username-duplicate")
  public ResponseEntity<MessageResponse> checkNicknameDuplication(@RequestBody CheckDuplicationRequsetDto request) {
    log.info("회원가입 닉네임 중복 체크 시작");
    String nickname = request.nickname();
    final boolean result = authService.checkNicknameDuplication(nickname);
    log.info("회원가입 닉네임 중복 체크 결과 = {}", result);
    if (result) {
      return ResponseEntity.status(HttpStatus.CONFLICT).body(new MessageResponse("이미 사용중인 닉네임 입니다."));
    }
    return ResponseEntity.ok(new MessageResponse("사용 가능한 닉네임입니다"));
  }

  @PostMapping("/join")
  public ResponseEntity<MessageResponse> join(@Valid @RequestBody JoinRequestDto joinRequestDto) {
    log.info("[join] 추가정보 받기 시작");
    authService.join(joinRequestDto);
    log.info("[join] 추가정보 저장하기 완료");
    return ResponseEntity.ok(new MessageResponse("회원가입이 완료되었습니다."));
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
