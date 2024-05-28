package run.attraction.api.v1.mypage.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
import run.attraction.api.v1.mypage.service.dto.userDetail.UserDetailDto;
import run.attraction.api.v1.mypage.service.dto.userDetail.UserDetailsResponseDto;


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
}
