package run.attraction.api.v1.statistics;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class AgeGroupTest {
  @Test
  @DisplayName("calculateAge 테스트")
  void calculateAgeTest() {
    //when
    LocalDate birthDate = LocalDate.now().minusYears(32);
    //given
    //then
    assertEquals(AgeGroup.calculateAge(birthDate),AgeGroup.THIRTIES);
  }
}
