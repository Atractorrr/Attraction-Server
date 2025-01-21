package run.attraction.api.v1.mypage.service.calendar;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import run.attraction.api.v1.archive.ReadBox;
import run.attraction.api.v1.archive.repository.ReadBoxRepository;

public class MypageCalendarServiceTest {
    @Mock
    private ReadBoxRepository readBoxRepository;

    @InjectMocks
    private MypageCalendarServiceImpl mypageCalendarServiceImpl;

    private List<ReadBox> readBoxesForTest1;
    private List<ReadBox> readBoxesForTest2;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        ReadBox readBox1 = ReadBox.builder()
                .id(1L)
                .articleId(1L)
                .userEmail("test1@gmail.com")
                .readPercentage(100)
                .readDate(LocalDate.of(2024, 2, 1))
                .build();

        ReadBox readBox2 = ReadBox.builder()
                .id(3L)
                .articleId(3L)
                .userEmail("test1@gmail.com")
                .readPercentage(100)
                .readDate(LocalDate.of(2024, 2, 1))
                .build();

        ReadBox readBox3 = ReadBox.builder()
                .id(4L)
                .articleId(4L)
                .userEmail("test1@gmail.com")
                .readPercentage(100)
                .readDate(LocalDate.of(2024, 2, 5))
                .build();

        ReadBox readBox4 = ReadBox.builder()
                .id(4L)
                .articleId(1L)
                .userEmail("test2@gmail.com")
                .readPercentage(100)
                .readDate(LocalDate.of(2024, 1, 1))
                .build();

        ReadBox readBox5 = ReadBox.builder()
                .id(5L)
                .articleId(2L)
                .userEmail("test2@gmail.com")
                .readPercentage(100)
                .readDate(LocalDate.of(2024, 1, 1))
                .build();

        readBoxesForTest1 = List.of(readBox1, readBox2, readBox3);
        readBoxesForTest2 = List.of(readBox4, readBox5);

        when(readBoxRepository.findCompletedReadBoxByEmail("test1@gmail.com")).thenReturn(readBoxesForTest1);
        when(readBoxRepository.findCompletedReadBoxByEmail("test2@gmail.com")).thenReturn(readBoxesForTest2);
    }

    @Test
    @DisplayName("마이페이지 잔디밭 API 테스트")
    void getUserCalendar() {
        //Given
        String email1 = "test1@gmail.com";
        String email2 = "test2@gmail.com";
        //When
        final Map<LocalDate, Integer> userCalendar1 = mypageCalendarServiceImpl.getUserCalendar(email1);
        final Map<LocalDate, Integer> userCalendar2 = mypageCalendarServiceImpl.getUserCalendar(email2);
        //Then
        // 1월 1일 / 2월 1일 / 2월 5일 / 12월 31일 : 4개
        assertEquals(userCalendar1.size(), 4);
        assertEquals(userCalendar1.get(LocalDate.of(2024, 2, 1)), 2);
        // 1월 1일 / 12월 31일 : 2개

        assertEquals(userCalendar2.size(), 3);
    }
}
