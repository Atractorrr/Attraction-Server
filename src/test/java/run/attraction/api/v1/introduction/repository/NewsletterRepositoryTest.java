//package run.attraction.api.v1.introduction.repository;
//
//import static org.assertj.core.api.Assertions.*;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//
//import java.util.Arrays;
//import java.util.stream.Collectors;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.dao.DataIntegrityViolationException;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.transaction.annotation.Transactional;
//import run.attraction.api.v1.introduction.Category;
//import run.attraction.api.v1.introduction.Newsletter;
//import java.util.List;
//
//@DataJpaTest
//@ActiveProfiles("test")
//@Transactional
//public class NewsletterRepositoryTest {
//
//  @Autowired
//  private NewsletterRepository newsletterRepository;
//
//  @BeforeEach
//  void setUp() {
//    newsletterRepository.deleteAll();
//
//    Newsletter newsletter1 = Newsletter.builder()
//        .email("test1@gmail.com")
//        .name("뉴닉")
//        .description("뉴닉보세요")
//        .category(Category.CURRENT_AFFAIRS_SOCIETY)
//        .mainLink("https://test.com")
//        .subscribeLink("https://test2.com")
//        .thumbnailUrl("https://test3.com")
//        .uploadDays("매주 월요일")
//        .nickname("뉴닉발송팀")
//        .isAgreeAdInfoReception(false)
//        .isAgreePersonalInfoCollection(true)
//        .isAutoSubscribeEnabled(true)
//        .build();
//
//    Newsletter newsletter2 = Newsletter.builder()
//        .email("test2@gmail.com")
//        .name("캐릿")
//        .description("캐릿보세요")
//        .category(Category.CURRENT_AFFAIRS_SOCIETY)
//        .mainLink("https://test8.com")
//        .subscribeLink("https://test9.com")
//        .thumbnailUrl("https://test9.com")
//        .uploadDays("매주 화요일")
//        .nickname("캐릿발송팀")
//        .isAgreeAdInfoReception(false)
//        .isAgreePersonalInfoCollection(true)
//        .isAutoSubscribeEnabled(true)
//        .build();
//
//    Newsletter newsletter3 = Newsletter.builder()
//        .email("test3@gmail.com")
//        .name("트렌드")
//        .description("트렌드보세요")
//        .category(Category.TREND_LIFE)
//        .mainLink("https://test3.com")
//        .subscribeLink("https://test3.com/subscribe")
//        .thumbnailUrl("https://test3.com/thumbnail")
//        .uploadDays("매주 수요일")
//        .nickname("트렌드발송팀")
//        .isAgreeAdInfoReception(false)
//        .isAgreePersonalInfoCollection(true)
//        .isAutoSubscribeEnabled(true)
//        .build();
//
//    newsletterRepository.save(newsletter1);
//    newsletterRepository.save(newsletter2);
//    newsletterRepository.save(newsletter3);
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
//    assertThat(byCategory).isNotNull();
//    assertThat(byCategory.size()).isEqualTo(2);
//  }
//
//  @Test
//  void countByCategoryAndIdNot_가_올바르게_동작한다() {
//    // given
//    List<Newsletter> newsletters = newsletterRepository.findAll();
//    Long excludedId = newsletters.get(0).getId();
//    Category category = Category.CURRENT_AFFAIRS_SOCIETY;
//
//    // when
//    int count = newsletterRepository.countByCategoryAndIdNot(excludedId, category);
//
//    // then
//    assertThat(count).isEqualTo(1);
//  }
//
//  @Test
//  void countByCategoryAndIdNot_가_올바르게_동작하지_않을_때() {
//    // given
//    List<Newsletter> newsletters = newsletterRepository.findAll();
//    assertThat(newsletters).isNotEmpty(); // 저장된 뉴스레터가 있는지 확인
//
//    Long excludedId = newsletters.get(0).getId(); // 첫 번째 뉴스레터의 ID를 제외함
//    Category category = Category.BUSINESS_FINANCIAL_TECHNOLOGY; // 잘못된 카테고리 제공
//
//    // when
//    int count = newsletterRepository.countByCategoryAndIdNot(excludedId, category);
//
//    // then
//    assertThat(count).isEqualTo(0); // 잘못된 카테고리이므로 결과는 0이어야 함
//  }
//
//  @Test
//  void findByCategoryAndIdNotWithOffset_가_올바르게_동작할_때() {
//    // given
//    List<Newsletter> newsletters = newsletterRepository.findAll();
//    assertThat(newsletters).isNotEmpty(); // 저장된 뉴스레터가 있는지 확인
//
//    Long excludedId = newsletters.get(0).getId();
//    String category = Category.CURRENT_AFFAIRS_SOCIETY.name(); // 카테고리 문자열로 변환
//    int size = 2;
//    int offset = 0;
//
//    // when
//    List<Newsletter> result = newsletterRepository.findByCategoryAndIdNotWithOffset(excludedId, category, size, offset);
//
//    // then
//    assertThat(result).isNotNull();
//    assertThat(result.size()).isEqualTo(1); // 페이징 조건에 맞는 결과 확인
//    for (Newsletter newsletter : result) {
//      assertThat(newsletter.getId()).isNotEqualTo(excludedId); // 제외된 ID가 결과에 없는지 확인
//      assertThat(newsletter.getCategory()).isEqualTo(Category.CURRENT_AFFAIRS_SOCIETY); // 카테고리가 맞는지 확인
//    }
//  }
//
////  @Test
////  void findByCategoryAndIdNotWithOffset_가_올바르게_동작하지_않을_때_잘못된_카테고리() {
////    // given
////    List<Newsletter> newsletters = newsletterRepository.findAll();
////    assertThat(newsletters).isNotEmpty(); // 저장된 뉴스레터가 있는지 확인
////
////    Long excludedId = newsletters.get(0).getId();
////    String category = "INVALID_CATEGORY"; // 존재하지 않는 카테고리 제공
////    int size = 2;
////    int offset = 0;
////
////    // when
////    List<Newsletter> result = newsletterRepository.findByCategoryAndIdNotWithOffset(excludedId, category, size, offset);
////
////    // then
////    assertThat(result).isEmpty(); // 잘못된 카테고리이므로 결과는 빈 리스트여야 함
////  }
//
//  @Test
//  void findByCategoryAndIdNotWithOffset_가_올바르게_동작하지_않을_때_잘못된_ID() {
//    // given
//    List<Newsletter> newsletters = newsletterRepository.findAll();
//    assertThat(newsletters).isNotEmpty(); // 저장된 뉴스레터가 있는지 확인
//
//    Long excludedId = Long.MAX_VALUE; // 존재하지 않는 ID 제공
//    String category = Category.CURRENT_AFFAIRS_SOCIETY.name();
//    int size = 2;
//    int offset = 0;
//
//    // when
//    List<Newsletter> result = newsletterRepository.findByCategoryAndIdNotWithOffset(excludedId, category, size, offset);
//
//    // then
//    assertThat(result).isNotNull();
//    assertThat(result.size()).isEqualTo(2); // 결과는 페이징 크기만큼 반환
//    for (Newsletter newsletter : result) {
//      assertThat(newsletter.getCategory()).isEqualTo(Category.CURRENT_AFFAIRS_SOCIETY); // 카테고리가 맞는지 확인
//    }
//  }
//
//  @Test
//  void findByCategoryAndIdNotWithOffset_가_올바르게_동작하지_않을_때_잘못된_사이즈() {
//    // given
//    List<Newsletter> newsletters = newsletterRepository.findAll();
//    assertThat(newsletters).isNotEmpty(); // 저장된 뉴스레터가 있는지 확인
//
//    Long excludedId = newsletters.get(0).getId();
//    String category = Category.CURRENT_AFFAIRS_SOCIETY.name();
//    int size = -1; // 잘못된 사이즈 제공
//    int offset = 0;
//
//    // when
//    assertThrows(DataIntegrityViolationException.class, () -> {
//      newsletterRepository.findByCategoryAndIdNotWithOffset(excludedId, category, size, offset);
//    }); // 잘못된 사이즈로 예외 발생 확인
//  }
//
//  @Test
//  void findByCategoryAndIdNotWithOffset_가_올바르게_동작하지_않을_때_잘못된_오프셋() {
//    // given
//    List<Newsletter> newsletters = newsletterRepository.findAll();
//    assertThat(newsletters).isNotEmpty(); // 저장된 뉴스레터가 있는지 확인
//
//    Long excludedId = newsletters.get(0).getId();
//    String category = Category.CURRENT_AFFAIRS_SOCIETY.name();
//    int size = 2;
//    int offset = newsletters.size() + 1; // 존재하지 않는 오프셋 제공
//
//    // when
//    List<Newsletter> result = newsletterRepository.findByCategoryAndIdNotWithOffset(excludedId, category, size, offset);
//
//    // then
//    assertThat(result).isEmpty(); // 잘못된 오프셋이므로 결과는 빈 리스트여야 함
//  }
//
//  @Test
//  void findNewslettersByNewsletterEmails_가_올바르게_동작할_때() {
//    // given
//    List<String> emails = List.of("test1@gmail.com", "test2@gmail.com");
//
//    // when
//    List<Object[]> result = newsletterRepository.findNewslettersByNewsletterEmails(emails);
//
//    // then
//    assertThat(result).isNotNull();
//    assertThat(result.size()).isEqualTo(2);
//
//    for (Object[] objects : result) {
//      String email = (String) objects[0];
//      Newsletter newsletter = (Newsletter) objects[1];
//
//      assertThat(email).isIn(emails);
//      assertThat(newsletter.getEmail()).isEqualTo(email);
//    }
//  }
//
//  @Test
//  void findNewslettersByNewsletterEmails_가_올바르게_동작하지_않을_때() {
//    // given
//    List<String> emails = List.of("invalid1@gmail.com", "invalid2@gmail.com");
//
//    // when
//    List<Object[]> result = newsletterRepository.findNewslettersByNewsletterEmails(emails);
//
//    // then
//    assertThat(result).isNotNull();
//    assertThat(result.size()).isEqualTo(0); // 잘못된 이메일이므로 결과는 빈 리스트여야 함
//  }
//
//  @Test
//  void findByCategory_가_올바르게_동작할_때() {
//    // given
//    Category category = Category.CURRENT_AFFAIRS_SOCIETY;
//
//    // when
//    List<Object[]> result = newsletterRepository.findByCategory(category);
//
//    // then
//    assertThat(result).isNotNull();
//    assertThat(result.size()).isEqualTo(2);
//
//    for (Object[] objects : result) {
//      Long id = (Long) objects[0];
//      Newsletter newsletter = (Newsletter) objects[1];
//
//      assertThat(newsletter.getCategory()).isEqualTo(category);
//      assertThat(newsletter.getId()).isEqualTo(id);
//    }
//  }
//
//  @Test
//  void findByCategory_가_올바르게_동작하지_않을_때() {
//    // given
//    Category category = Category.DESIGN; // 저장된 뉴스레터에는 없는 카테고리
//
//    // when
//    List<Object[]> result = newsletterRepository.findByCategory(category);
//
//    // then
//    assertThat(result).isNotNull();
//    assertThat(result).isEmpty(); // 해당 카테고리의 뉴스레터가 없으므로 결과는 빈 리스트여야 함
//  }
//
//  @Test
//  void findNewsletterRandom_성공() {
//    // given
//    List<Long> allIds = newsletterRepository.findAll().stream().map(Newsletter::getId).collect(Collectors.toList());
//    List<Long> excludedIds = Arrays.asList(allIds.get(0)); // Exclude the first ID
//    int size = 2;
//
//    // when
//    List<Newsletter> newsletters = newsletterRepository.findNewsletterRandom(excludedIds, size);
//
//    // then
//    assertThat(newsletters).hasSize(size);
//
//    newsletters.forEach(newsletter -> {
//      assertThat(excludedIds).doesNotContain(newsletter.getId());
//    });
//
//    List<String> existingEmails = Arrays.asList("test2@gmail.com", "test3@gmail.com");
//    newsletters.forEach(newsletter -> {
//      assertThat(existingEmails).contains(newsletter.getEmail());
//    });
//  }
//
//  @Test
//  void findNewsletterRandom_실패_모두제외된경우() {
//    // given
//    List<Long> allIds = newsletterRepository.findAll().stream().map(Newsletter::getId).collect(Collectors.toList());
//    int size = 2;
//
//    // when
//    List<Newsletter> newsletters = newsletterRepository.findNewsletterRandom(allIds, size);
//
//    // then
//    assertThat(newsletters).isEmpty();
//  }
//
//  @Test
//  void findNewsletterRandom_실패_요청사이즈0() {
//    // given
//    List<Long> excludedIds = Arrays.asList(1L);
//    int size = 0;
//
//    // when
//    List<Newsletter> newsletters = newsletterRepository.findNewsletterRandom(excludedIds, size);
//
//    // then
//    assertThat(newsletters).isEmpty();
//  }
//
//  @Test
//  void findNewslettersByNewsletterIds_성공() {
//    // given
//    List<Long> newsletterIds = newsletterRepository.findAll().stream().map(Newsletter::getId).collect(Collectors.toList());
//    int expectedSize = newsletterIds.size();
//
//    // when
//    List<Newsletter> newsletters = newsletterRepository.findNewslettersByNewsletterIds(newsletterIds);
//
//    // then
//    assertThat(newsletters).hasSize(expectedSize);
//
//    List<Long> retrievedIds = newsletters.stream().map(Newsletter::getId).collect(Collectors.toList());
//    assertThat(retrievedIds).containsExactlyInAnyOrderElementsOf(newsletterIds);
//  }
//
//  @Test
//  void findNewslettersByNewsletterIds_실패_없는_ID포함() {
//    // given
//    List<Long> existingIds = newsletterRepository.findAll().stream().map(Newsletter::getId).collect(Collectors.toList());
//    List<Long> newsletterIds = Arrays.asList(999L, 1000L);
//
//    // when
//    List<Newsletter> newsletters = newsletterRepository.findNewslettersByNewsletterIds(newsletterIds);
//
//    // then
//    assertThat(newsletters).isEmpty();
//  }
//
//  @Test
//  void findNewslettersByNewsletterIds_성공_일부_없는_ID포함() {
//    // given
//    List<Long> existingIds = newsletterRepository.findAll().stream().map(Newsletter::getId).toList();
//    List<Long> newsletterIds = Arrays.asList(existingIds.get(0), 999L);
//
//    // when
//    List<Newsletter> newsletters = newsletterRepository.findNewslettersByNewsletterIds(newsletterIds);
//
//    // then
//    assertThat(newsletters).hasSize(1);
//
//    List<Long> retrievedIds = newsletters.stream().map(Newsletter::getId).collect(Collectors.toList());
//    assertThat(retrievedIds).containsExactly(existingIds.get(0));
//  }
//}