package run.attraction.api.v1.mypage.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import run.attraction.api.v1.archive.dto.response.ApiResponse;
import run.attraction.api.v1.auth.service.dto.join.CheckDuplicationRequsetDto;
import run.attraction.api.v1.auth.session.SessionService;
import run.attraction.api.v1.mypage.service.MypageService;
import run.attraction.api.v1.mypage.service.dto.MessageResponse;
import run.attraction.api.v1.mypage.service.dto.archive.article.RecentArticlesDto;
import run.attraction.api.v1.mypage.service.dto.archive.article.RecentArticlesResponseDto;
import run.attraction.api.v1.mypage.service.dto.archive.newsletter.MypageNewsletterDetail;
import run.attraction.api.v1.mypage.service.dto.archive.newsletter.SubscribeResponseDto;
import run.attraction.api.v1.mypage.service.dto.calendar.CalendarResponseDto;
import run.attraction.api.v1.mypage.service.dto.userDetail.UpdateImgRequestDto;
import run.attraction.api.v1.mypage.service.dto.userDetail.UpdateInterestRequestDto;
import run.attraction.api.v1.mypage.service.dto.userDetail.UpdateNicknameRequestDto;
import run.attraction.api.v1.mypage.service.dto.userDetail.UpdateOccupationRequestDto;
import run.attraction.api.v1.mypage.service.dto.userDetail.UpdateUserExpirationRequestDto;
import run.attraction.api.v1.mypage.service.dto.userDetail.UserDetailDto;
import run.attraction.api.v1.mypage.service.dto.userDetail.UserDetailsResponseDto;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "마이 페이지", description = "MypageController")
public class MypageController {

  private final MypageService mypageService;
  private final SessionService sessionService;

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
  @Operation(summary = "최근 읽은 아티클들 가져오기", description = "최근 읽은 아티클들을 가져온다. / size 조절이 가능하다  (default = 10)")
  public final ApiResponse<RecentArticlesResponseDto> getRecentArticles(@PathVariable("email") String email,
                                                                        @RequestParam(name = "size", defaultValue = "10") int size) {
    final List<RecentArticlesDto> recentArticlesByEmail = mypageService.getRecentArticlesByEmail(email, size);

    return ApiResponse.from(HttpStatus.OK, "성공", new RecentArticlesResponseDto(recentArticlesByEmail));
  }

  @PatchMapping("/{email}/profile")
  public final ResponseEntity<MessageResponse> updateProfileImg(@PathVariable("email") String email, @RequestBody UpdateImgRequestDto request){
    mypageService.updateProfileImgByEmail(email,request.fileImgSrc());
    return ResponseEntity.ok(new MessageResponse("프로필 이미지 변경 성공"));
  }

  @PatchMapping("/{email}/background")
  public final ResponseEntity<MessageResponse> updateBackgroundImg(@PathVariable("email") String email, @RequestBody UpdateImgRequestDto request){
    mypageService.updateBackgroundImgByEmail(email,request.fileImgSrc());
    return ResponseEntity.ok(new MessageResponse("배경 이미지 변경 성공"));
  }

  @GetMapping("/{email}/subscribe-list")
  @Operation(summary = "구독한 뉴스레터들 가져오기", description = "구독한 뉴스레터 전부를 가져온다")
  public final ResponseEntity<SubscribeResponseDto> getSubscribes(@PathVariable("email") String email) {
    final List<MypageNewsletterDetail> subscribeByEmail = mypageService.getSubscribeByEmail(email);
    return ResponseEntity.ok(new SubscribeResponseDto(subscribeByEmail));
  }


  @PostMapping("/username-duplicate")
  public final ResponseEntity<MessageResponse> checkNicknameDuplication(@RequestBody CheckDuplicationRequsetDto request) {
    String nickname = request.nickname();
    boolean result = mypageService.checkNicknameDuplication(nickname);
    if (result) {
      return ResponseEntity.status(HttpStatus.CONFLICT).body(new MessageResponse("이미 사용중인 닉네임 입니다."));
    }
    return ResponseEntity.ok(new MessageResponse("사용 가능한 닉네임입니다"));
  }

  @PatchMapping("/{email}/nickname")
  public final ResponseEntity<MessageResponse> updateNickname(@PathVariable("email") String email,
                                                              @RequestBody UpdateNicknameRequestDto request){
    String nickname = request.nickname();
    mypageService.updateNicknameByEmail(email, nickname);
    return ResponseEntity.ok(new MessageResponse("닉네임 설정 완료"));
  }

  @PatchMapping("/{email}/expiration")
  public final ResponseEntity<MessageResponse> updateUserExpiration(@PathVariable("email") String email,
                                                                    @RequestBody UpdateUserExpirationRequestDto request){
    Integer expiration = request.userExpiration();
    mypageService.updateUserExpirationByEmail(email, expiration);
    return ResponseEntity.ok(new MessageResponse("계정 만료일 설정 완료"));
  }

  @PatchMapping("/{email}/interest")
  public final ResponseEntity<MessageResponse> updateInterest(@PathVariable("email") String email,
                                                              @RequestBody UpdateInterestRequestDto request){
    List<String> interest = request.interest();
    mypageService.updateInterestByEmail(email, interest);
    return ResponseEntity.ok(new MessageResponse("계정 만료일 설정 완료"));
  }

  @PatchMapping("/{email}/occupation")
  public final ResponseEntity<MessageResponse> updateOccupation(@PathVariable("email") String email,
                                                                @RequestBody UpdateOccupationRequestDto request){
    String occupation = request.occupation();
    mypageService.updateOccupationByEmail(email, occupation);
    return ResponseEntity.ok(new MessageResponse("계정 만료일 설정 완료"));
  }

  @DeleteMapping("/{email}")
  public final ResponseEntity<MessageResponse> resignUser(@PathVariable("email") String email,
                                                          HttpServletRequest request){
    mypageService.resignByEmail(email);
    sessionService.removeSession(request);
    return ResponseEntity.ok(new MessageResponse("탈퇴 완료되었습니다."));
  }

}
