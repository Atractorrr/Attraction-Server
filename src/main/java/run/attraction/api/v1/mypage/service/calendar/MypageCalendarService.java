package run.attraction.api.v1.mypage.service.calendar;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import run.attraction.api.v1.archive.ReadBox;

public interface MypageCalendarService {
  Map<LocalDate,Integer> getUserCalendar(String email);
}
