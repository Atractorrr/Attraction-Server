package run.attraction.api.v1.statistics;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import run.attraction.api.v1.introduction.repository.NewsletterRepository;
import run.attraction.api.v1.statistics.repository.NewsletterEventRepository;
import run.attraction.api.v1.user.Occupation;

@DataJpaTest
@ActiveProfiles("test")
@TestPropertySource("classpath:application-test.yml")
public class NewsletterEventRepositoryTest {

  @Autowired
  private NewsletterEventRepository repository;

  @Value("${spring.datasource.url}")
  private String datasourceUrl;
  @Autowired
  private NewsletterRepository newsletterRepository;

  @BeforeEach
  void setUp(){
    NewsletterEvent event1 = NewsletterEvent.builder()
        .newsletterId(1L)
        .occupation(Occupation.STUDENT)
        .ageGroup(AgeGroup.TWENTIES)
        .build();

    NewsletterEvent event2 = NewsletterEvent.builder()
        .newsletterId(2L)
        .occupation(Occupation.STUDENT)
        .ageGroup(AgeGroup.TWENTIES)
        .build();

    NewsletterEvent event3 = NewsletterEvent.builder()
        .newsletterId(3L)
        .occupation(Occupation.SELFEMPLOY)
        .ageGroup(AgeGroup.FORTIES)
        .build();

    repository.saveAll(List.of(event1,event2,event3));
  }

  @Test
  @DisplayName("Occupation 탐색 테스트")
  void findByOccupationTest(){
    // given
    String string = "STUDENT";
    // when
    List<NewsletterEvent> events = repository.findByOccupation(Occupation.valueOf(string));

    // then
    assertEquals(events.size(),2);

  }

  @Test
  @DisplayName("AgeGroup 탐색 테스트")
  void findByAgeGroupTest(){
    // given
    final AgeGroup ageGroup = AgeGroup.TWENTIES;
    // when
    List<NewsletterEvent> events = repository.findByAgeGroup(ageGroup);

    // then
    assertEquals(events.size(),2);
  }

}

