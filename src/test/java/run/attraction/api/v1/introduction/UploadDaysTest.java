package run.attraction.api.v1.introduction;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.Test;

class UploadDaysTest {
  @Test
  public void testViewName() {
    assertEquals("월", UploadDays.MON.getViewName());
    assertEquals("화", UploadDays.TUE.getViewName());
    assertEquals("수", UploadDays.WED.getViewName());
    assertEquals("목", UploadDays.THU.getViewName());
    assertEquals("금", UploadDays.FRI.getViewName());
    assertEquals("토", UploadDays.SAT.getViewName());
    assertEquals("일", UploadDays.SUN.getViewName());
  }

  @Test
  public void testWeekDay() {
    List<UploadDays> weekDays = UploadDays.MON.weekDay();
    assertEquals(5, weekDays.size());
    assertTrue(weekDays.contains(UploadDays.MON));
    assertTrue(weekDays.contains(UploadDays.TUE));
    assertTrue(weekDays.contains(UploadDays.WED));
    assertTrue(weekDays.contains(UploadDays.THU));
    assertTrue(weekDays.contains(UploadDays.FRI));
  }

  @Test
  public void testWeekEnd() {
    List<UploadDays> weekEnds = UploadDays.SAT.weekEnd();
    assertEquals(2, weekEnds.size());
    assertTrue(weekEnds.contains(UploadDays.SAT));
    assertTrue(weekEnds.contains(UploadDays.SUN));
  }
}