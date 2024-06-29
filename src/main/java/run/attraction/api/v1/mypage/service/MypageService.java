package run.attraction.api.v1.mypage.service;

import io.micrometer.core.annotation.Counted;
import io.micrometer.core.annotation.Timed;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import run.attraction.api.v1.mypage.service.archive.article.MypageArticleService;
import run.attraction.api.v1.mypage.service.archive.newsletter.MypageNewsletterService;
import run.attraction.api.v1.mypage.service.calendar.MypageCalendarService;
import run.attraction.api.v1.mypage.service.dto.archive.article.RecentArticlesDto;
import run.attraction.api.v1.mypage.service.dto.archive.newsletter.MypageNewsletterDetail;
import run.attraction.api.v1.mypage.service.dto.calendar.CalendarDay;
import run.attraction.api.v1.mypage.service.dto.calendar.CalendarResponseDto;
import run.attraction.api.v1.mypage.service.dto.userDetail.UserDetailDto;
import run.attraction.api.v1.mypage.service.user.MypageUserService;

@Timed("mypage.service")
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MypageService {
  private final MypageUserService userService;
  private final MypageCalendarService calendarService;
  private final MypageArticleService articleService;
  private final MypageNewsletterService newsletterService;

  public UserDetailDto getUserDetails(String email){
    return userService.getUserDetails(email);
  }

  public CalendarResponseDto getUserCalendar(String email){
    final Map<LocalDate, Integer> userCalendar = calendarService.getUserCalendar(email);
    List<CalendarDay> calendarDays = userCalendar.entrySet().stream()
        .sorted(Map.Entry.comparingByKey())
        .map(entry -> new CalendarDay(entry.getKey(), entry.getValue()))
        .toList();
    return new CalendarResponseDto(calendarDays);
  }

  @Counted("mypage.service")
  public List<RecentArticlesDto> getRecentArticlesByEmail(String email, int size){
    return articleService.getUserRecentArticles(email, size);
  }

  @Transactional
  @Counted("mypage.service")
  public void updateProfileImgByEmail(String email, String profileImg){
    userService.updateProfileImg(email, profileImg);
  }

  @Transactional
  @Counted("mypage.service")
  public void updateBackgroundImgByEmail(String email, String backgroundImg){
    userService.updateBackgroundImg(email, backgroundImg);
  }

  @Transactional
  public void updateNicknameByEmail(String email, String nickname){
    userService.updateNickname(email, nickname);
  }

  @Transactional
  public void updateUserExpirationByEmail(String email, Integer expiration){
    userService.updateUserExpiration(email, expiration);
  }

  @Transactional
  public void updateInterestByEmail(String email, List<String> interest){
    userService.updateInterest(email, interest);
  }

  @Transactional
  public void updateOccupationByEmail(String email, String occupation){
    userService.updateOccupation(email, occupation);
  }

  public List<MypageNewsletterDetail> getSubscribeByEmail(String email){
    return newsletterService.getSubscribesByEmail(email);
  }

  @Transactional
  public boolean checkNicknameDuplication(String nickname) {
    return userService.checkNicknameDuplication(nickname);
  }

  @Transactional
  public void resignByEmail(String email){
    userService.updateIsDeleted(email);
  }
}
