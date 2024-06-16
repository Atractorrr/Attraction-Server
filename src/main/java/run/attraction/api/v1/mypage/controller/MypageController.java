package run.attraction.api.v1.mypage.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import run.attraction.api.v1.auth.service.dto.join.CheckDuplicationRequsetDto;
import run.attraction.api.v1.mypage.service.MypageService;
import run.attraction.api.v1.mypage.service.dto.MessageResponse;
import run.attraction.api.v1.mypage.service.dto.archive.article.MypageArticle;
import run.attraction.api.v1.mypage.service.dto.archive.article.RecentArticlesResponseDto;
import run.attraction.api.v1.mypage.service.dto.archive.newsletter.MypageNewsletterDetail;
import run.attraction.api.v1.mypage.service.dto.archive.newsletter.SubscribeResponseDto;
import run.attraction.api.v1.mypage.service.dto.calendar.CalendarResponseDto;
import run.attraction.api.v1.mypage.service.dto.userDetail.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
@Slf4j
public class MypageController {

  private final MypageService mypageService;

  @GetMapping("/{email}")
  public final ResponseEntity<UserDetailsResponseDto> getUserDetails(@PathVariable("email") String email) {
    log.info("마이페이지 유저 정보 API 시작 ( {} )", LocalDateTime.now());
    log.info("email = {} ",email);
    final UserDetailDto userDetails = mypageService.getUserDetails(email);
    log.info("마이페이지 유저 정보 API 완료 ( {} )", LocalDateTime.now());
    return ResponseEntity.ok(new UserDetailsResponseDto(userDetails));
  }

  @GetMapping("/{email}/calendar")
  public final ResponseEntity<?> getCalendar(@PathVariable("email") String email) {
    log.info("마이페이지 캘린더 API 시작");
    log.info("email = {} ",email);
    final CalendarResponseDto userCalendar = mypageService.getUserCalendar(email);
    log.info("마이페이지 캘린더 API 완료");
    return ResponseEntity.ok(userCalendar);
  }

  @GetMapping("/{email}/articles/recent")
  public final ResponseEntity<RecentArticlesResponseDto> getRecentArticles(@PathVariable("email") String email) {
    log.info("최근 읽은 아티클 API 시작");
    log.info("email = {} ",email);
    final List<MypageArticle> recentArticlesByEmail = mypageService.getRecentArticlesByEmail(email);
    log.info("최근 읽은 아티클 API 완료");
    return ResponseEntity.ok(new RecentArticlesResponseDto(recentArticlesByEmail));
  }

  @PatchMapping("/{email}/profile")
  public final ResponseEntity<MessageResponse> updateProfileImg(@PathVariable("email") String email, @RequestBody UpdateImgRequestDto request){
    log.info("마이페이지 프로필 이미지 수정 시작");
    log.info("email = {} ",email);
    log.info("img = {} ",request.fileImgSrc());
    mypageService.updateProfileImgByEmail(email,request.fileImgSrc());
    log.info("마이페이지 프로필이미지 수정 완료");
    return ResponseEntity.ok(new MessageResponse("프로필 이미지 변경 성공"));
  }

  @PatchMapping("/{email}/background")
  public final ResponseEntity<MessageResponse> updateBackgroundImg(@PathVariable("email") String email, @RequestBody UpdateImgRequestDto request){
    log.info("마이페이지 배경이미지 수정 시작");
    log.info("email = {} ",email);
    log.info("img = {} ",request.fileImgSrc());
    mypageService.updateBackgroundImgByEmail(email,request.fileImgSrc());
    log.info("마이페이지 배경이미지 수정 완료");
    return ResponseEntity.ok(new MessageResponse("배경 이미지 변경 성공"));
  }

  @GetMapping("/{email}/subscribe-list")
  public final ResponseEntity<SubscribeResponseDto> getSubscribes(@PathVariable("email") String email) {
    log.info("마이페이지 구독리스트 API 시작");
    log.info("email = {} ",email);
    final List<MypageNewsletterDetail> subscribeByEmail = mypageService.getSubscribeByEmail(email);
    log.info("마이페이지 구독리스트 API 완료");
    return ResponseEntity.ok(new SubscribeResponseDto(subscribeByEmail));
  }


  @PostMapping("/username-duplicate")
  public final ResponseEntity<MessageResponse> checkNicknameDuplication(@RequestBody CheckDuplicationRequsetDto request) {
    String nickname = request.nickname();
    log.info("마이페이지 개인설정 닉네임 중복체크 시작");
    log.info("nickname = {} ",nickname);
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
    log.info("닉네임 변경 시작");
    log.info("nickname = {} ",nickname);
    mypageService.updateNicknameByEmail(email, nickname);
    log.info("닉네임 변경 완료");
    return ResponseEntity.ok(new MessageResponse("닉네임 설정 완료"));
  }

  @PatchMapping("/{email}/expiration")
  public final ResponseEntity<MessageResponse> updateUserExpiration(@PathVariable("email") String email,
                                                                    @RequestBody UpdateUserExpirationRequestDto request){
    Integer expiration = request.userExpiration();
    log.info("계정 만료일 변경 시작");
    log.info("user_expiration = {} ",expiration);
    mypageService.updateUserExpirationByEmail(email, expiration);
    log.info("계정 만료일 변경 완료");
    return ResponseEntity.ok(new MessageResponse("계정 만료일 설정 완료"));
  }

  @PatchMapping("/{email}/interest")
  public final ResponseEntity<MessageResponse> updateInterest(@PathVariable("email") String email,
                                                              @RequestBody UpdateInterestRequestDto request){
    List<String> interest = request.interest();
    log.info("관심사 변경 시작");
    log.info("interest = {} ",interest.toString());
    mypageService.updateInterestByEmail(email, interest);
    log.info("관심사 변경 완료");
    return ResponseEntity.ok(new MessageResponse("계정 만료일 설정 완료"));
  }

  @PatchMapping("/{email}/occupation")
  public final ResponseEntity<MessageResponse> updateOccupation(@PathVariable("email") String email,
                                                                @RequestBody UpdateOccupationRequestDto request){
    String occupation = request.occupation();
    log.info("직업군 변경 시작");
    log.info("occupation = {} ",occupation);
    mypageService.updateOccupationByEmail(email, occupation);
    log.info("직업군 변경 완료");
    return ResponseEntity.ok(new MessageResponse("계정 만료일 설정 완료"));
  }

}
