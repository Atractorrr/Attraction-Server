//package run.attraction.api.v1.repository;
//
//import org.assertj.core.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mock;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.test.context.TestPropertySource;
//import run.attraction.api.v1.introduction.Category;
//import run.attraction.api.v1.introduction.Newsletter;
//import run.attraction.api.v1.introduction.repository.NewsletterRepository;
//import java.util.List;
//
//@DataJpaTest
//@ActiveProfiles("test")
//@TestPropertySource("classpath:application-test.yml")
//public class NewsletterRepositoryTest {
//
//  @Autowired
//  private NewsletterRepository newsletterRepository;
//
//  @Value("${spring.datasource.url}")
//  private String datasourceUrl;
//
//  void setUp() {
//    Newsletter newsletter = Newsletter.builder()
//        .email("test@gmail.com")
//        .name("뉴닉")
//        .description("뉴닉보세요")
//        .category(Category.CURRENT_AFFAIRS_SOCIETY)
//        .mainLink("https://test.com")
//        .subscribeLink("https://test2.com")
//        .thumbnailUrl("https://test3.com")
//        .uploadDays("매주 월요일")
//        .nickname("뉴닉발송팀")
//        .build();
//
//    newsletterRepository.save(newsletter);
//  }
//
//  @Test
//  void NewsletterRepository_가_제대로_연결되었다(){
//    // given
//
//    // when
//    List<Object[]> byCategory = newsletterRepository.findByCategory(Category.CURRENT_AFFAIRS_SOCIETY);
//
//    // then
//    Assertions.assertThat(byCategory).isNotNull();
//
//  }
//}
