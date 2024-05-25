package run.attraction.api.v1.mypage.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import run.attraction.api.v1.archive.ReadBox;
import run.attraction.api.v1.mypage.service.archive.article.MypageArticleService;
import run.attraction.api.v1.mypage.service.calendar.MypageCalendarService;
import run.attraction.api.v1.mypage.service.dto.archive.article.MypageArticle;
import run.attraction.api.v1.mypage.service.dto.calendar.CalendarDay;
import run.attraction.api.v1.mypage.service.dto.calendar.CalendarResponseDto;
import run.attraction.api.v1.mypage.service.dto.userDetail.UserDetailDto;
import run.attraction.api.v1.mypage.service.user.MypageUserService;

@Service
@RequiredArgsConstructor
public class MypageService {
  private final MypageUserService mypageUserService;
  private final MypageCalendarService calendarService;
  private final MypageArticleService articleService;

  public UserDetailDto getUserDetails(String email){
    return mypageUserService.getUserDetails(email);
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
}
