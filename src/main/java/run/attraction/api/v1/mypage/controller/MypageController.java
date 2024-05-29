package run.attraction.api.v1.mypage.controller;

import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import run.attraction.api.v1.mypage.service.MypageService;
import run.attraction.api.v1.mypage.service.dto.archive.article.MypageArticle;
import run.attraction.api.v1.mypage.service.dto.archive.article.RecentArticlesResponseDto;
import run.attraction.api.v1.mypage.service.dto.archive.newsletter.MypageNewsletterDetail;
import run.attraction.api.v1.mypage.service.dto.archive.newsletter.SubscribeResponseDto;
import run.attraction.api.v1.mypage.service.dto.calendar.CalendarResponseDto;
import run.attraction.api.v1.mypage.service.dto.userDetail.UpdateBackgroundImgRequestDto;
import run.attraction.api.v1.mypage.service.dto.userDetail.UpdateProfileImgRequestDto;
import run.attraction.api.v1.mypage.service.dto.userDetail.UpdateUserDetailDto;
import run.attraction.api.v1.mypage.service.dto.userDetail.UpdateUserDetailRequestDto;
import run.attraction.api.v1.mypage.service.dto.userDetail.UserDetailDto;
import run.attraction.api.v1.mypage.service.dto.userDetail.UserDetailsResponseDto;
import org.springframework.http.HttpStatus;


@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
@Slf4j
public class MypageController {

  private final MypageService mypageService;

  @GetMapping("/{email}")
  public final ResponseEntity<UserDetailsResponseDto> getUserDetails(@PathVariable("email") String email) {
    final UserDetailDto userDetails = mypageService.getUserDetails(email);
    return ResponseEntity.ok(new UserDetailsResponseDto(userDetails));
  }

  @GetMapping("/{email}/calendar")
  public final ResponseEntity<?> getCalendar(@PathVariable("email") String email) {
    final CalendarResponseDto userCalendar = mypageService.getUserCalendar(email);
    return ResponseEntity.ok(userCalendar);
  }

  @GetMapping("/{email}/articles/recent")
  public final ResponseEntity<?> getRecentArticles(@PathVariable("email") String email) {
    final List<MypageArticle> recentArticlesByEmail = mypageService.getRecentArticlesByEmail(email);
    return ResponseEntity.ok(new RecentArticlesResponseDto(recentArticlesByEmail));
  }

  @PatchMapping("/{email}/profile")
  public final ResponseEntity<?> updateProfileImg(@PathVariable("email") String email, @RequestBody UpdateProfileImgRequestDto request){
    mypageService.updateProfileImgByEmail(email,request.profileImg());
    return ResponseEntity.ok().build();
  }

  @PatchMapping("/{email}/background")
  public final ResponseEntity<?> updateBackgroundImg(@PathVariable("email") String email, @RequestBody UpdateBackgroundImgRequestDto request){
    mypageService.updateBackgroundImgByEmail(email,request.backgroundImg());
    return ResponseEntity.ok().build();
  }

  @GetMapping("/{email}/subscribe-list")
  public final ResponseEntity<SubscribeResponseDto> getSubscribes(@PathVariable("email") String email) {
    final List<MypageNewsletterDetail> subscribeByEmail = mypageService.getSubscribeByEmail(email);
    return ResponseEntity.ok(new SubscribeResponseDto(subscribeByEmail));
  }

  @PatchMapping("/{email}")
  public final ResponseEntity<?> updateUserDetails(@PathVariable("email") String email,
                                                   @RequestBody(required = false) UpdateUserDetailRequestDto request)
  {
    UpdateUserDetailDto updateUserDetailDto = getUpdateUserDetailDto(email, request);
    mypageService.updateUserDetails(updateUserDetailDto);
    return ResponseEntity.ok().build();
  }

  private UpdateUserDetailDto getUpdateUserDetailDto(String email, UpdateUserDetailRequestDto request) {
    return UpdateUserDetailDto.builder()
        .email(email)
        .nickName(request.nickName())
        .userExpiration(request.userExpiration())
        .interest(request.interest())
        .occupation(request.occupation())
        .build();
  }

  @PostMapping("/username-duplicate")
  public final ResponseEntity<?> checkNickNameDuplication(@Valid @RequestParam String nickname) {
    if (mypageService.checkNickNameDuplication(nickname)) {
      return ResponseEntity.status(HttpStatus.CONFLICT).body("중복된 닉네임 입니다.");
    }
    return ResponseEntity.ok().build();
  }
}
