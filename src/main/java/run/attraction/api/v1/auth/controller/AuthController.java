package run.attraction.api.v1.auth.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import run.attraction.api.v1.archive.dto.response.ApiResponse;
import run.attraction.api.v1.auth.service.AuthService;
import run.attraction.api.v1.auth.service.dto.UserStateDto;
import run.attraction.api.v1.auth.service.dto.join.CheckDuplicationRequsetDto;
import run.attraction.api.v1.auth.service.dto.join.JoinRequestDto;
import run.attraction.api.v1.auth.service.dto.login.FirstLoginResponseDto;
import run.attraction.api.v1.auth.service.dto.login.LoginRequestDto;
import run.attraction.api.v1.auth.service.dto.login.LoginResponseDto;
import run.attraction.api.v1.auth.session.SessionService;
import run.attraction.api.v1.auth.session.dto.UserDetailBySession;
import run.attraction.api.v1.mypage.service.dto.MessageResponse;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {

  private final AuthService authService;
  private final SessionService sessionService;

  @PostMapping("/login")
  public ResponseEntity<?> login(@RequestBody LoginRequestDto loginRequestDto,
                                 HttpServletRequest request) {
    log.info("로그인 시작");
    final UserStateDto userStateDto = authService.login(loginRequestDto.getProvider(), loginRequestDto.getCode());

    log.info("세션 시작");
    sessionService.getSession(request,userStateDto);

    log.info("로그인 완료");
    if (userStateDto.isUserBefore()) {
      return ResponseEntity.status(HttpStatus.CREATED).body(
          new LoginResponseDto(userStateDto.getEmail(),
              userStateDto.isShouldReissueToken(),
              userStateDto.isHasExtraDetails()
          ));
    }

    return ResponseEntity.ok(FirstLoginResponseDto.builder()
        .email(userStateDto.getEmail())
        .hasExtraDetails(false)
        .shouldReissueToken(false)
        .build());
  }



  @PostMapping("/join/username-duplicate")
  public ResponseEntity<MessageResponse> checkNicknameDuplication(@RequestBody CheckDuplicationRequsetDto request) {
    String nickname = request.nickname();
    final boolean result = authService.checkNicknameDuplication(nickname);
    if (result) {
      return ResponseEntity.status(HttpStatus.CONFLICT).body(new MessageResponse("이미 사용중인 닉네임 입니다."));
    }
    return ResponseEntity.ok(new MessageResponse("사용 가능한 닉네임입니다"));
  }

  @PostMapping("/join")
  public ResponseEntity<MessageResponse> join(@Valid @RequestBody JoinRequestDto joinRequestDto) {
    authService.join(joinRequestDto);
    return ResponseEntity.ok(new MessageResponse("회원가입이 완료되었습니다."));
  }


  @PostMapping("/logout")
  public ResponseEntity<?> logout(HttpServletRequest request) {
    sessionService.removeSession(request);
    return ResponseEntity.ok(new MessageResponse("로그아웃 완료되었습니다."));
  }

  @GetMapping("/session")
  public ApiResponse<UserDetailBySession> getUserDetail(HttpServletRequest request){
    UserDetailBySession userDetail = sessionService.getUserDetail(request);
    return ApiResponse.from(HttpStatus.OK, "성공,", userDetail);
  }

}
