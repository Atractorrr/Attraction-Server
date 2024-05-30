package run.attraction.api.v1.mypage.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import run.attraction.api.v1.mypage.service.archive.article.MypageArticleService;
import run.attraction.api.v1.mypage.service.archive.newsletter.MypageNewsletterService;
import run.attraction.api.v1.mypage.service.calendar.MypageCalendarService;
import run.attraction.api.v1.mypage.service.dto.archive.article.MypageArticle;
import run.attraction.api.v1.mypage.service.dto.archive.newsletter.MypageNewsletterDetail;
import run.attraction.api.v1.mypage.service.dto.calendar.CalendarDay;
import run.attraction.api.v1.mypage.service.dto.calendar.CalendarResponseDto;
import run.attraction.api.v1.mypage.service.dto.userDetail.UpdateUserDetailDto;
import run.attraction.api.v1.mypage.service.dto.userDetail.UserDetailDto;
import run.attraction.api.v1.mypage.service.user.MypageUserService;

@Service
@RequiredArgsConstructor
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

  public List<MypageArticle> getRecentArticlesByEmail(String email){
    return articleService.getUserRecentArticles(email);
  }


  public void updateProfileImgByEmail(String email, String profileImg){
    userService.updateProfileImg(email, profileImg);
  }

  public void updateBackgroundImgByEmail(String email, String backgroundImg){
    userService.updateBackgroundImg(email, backgroundImg);
  }

  public List<MypageNewsletterDetail> getSubscribeByEmail(String email){
    return newsletterService.getSubscribesByEmail(email);
  }

  public void updateUserDetails(UpdateUserDetailDto updateUserDetailDto){
    userService.updateUserDetail(updateUserDetailDto);
  }

  public boolean checkNickNameDuplication(String nickName) {
    return userService.checkNickNameDuplication(nickName);
  }

}
