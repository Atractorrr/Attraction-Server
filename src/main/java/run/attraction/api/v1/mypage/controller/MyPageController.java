package run.attraction.api.v1.mypage.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import run.attraction.api.v1.mypage.service.MypageService;
import run.attraction.api.v1.mypage.service.dto.UserDetaiilDto;
import run.attraction.api.v1.mypage.service.dto.UserDetailsResponseDto;

@RestController
@RequestMapping("/api/v1/member")
@RequiredArgsConstructor
@Slf4j
public class MyPageController {

  private final MypageService mypageService;

  @GetMapping("/{memberId}")
  public final ResponseEntity<UserDetailsResponseDto> getUserDetails(@PathVariable("memberId") Long memberId) {
    final UserDetaiilDto userDetails = mypageService.getUserDetails(memberId);
    return ResponseEntity.ok(new UserDetailsResponseDto(userDetails));
  }


}
