package run.attraction.api.v1.introduction.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import run.attraction.api.v1.introduction.Category;
import run.attraction.api.v1.introduction.UserSubscribedNewsletterCategory;

@DataJpaTest
@ActiveProfiles("test")
@Transactional
public class UserSubscribedNewsletterCategoryRepositoryTest {

  @Autowired
  private UserSubscribedNewsletterCategoryRepository userSubscribedNewsletterCategoryRepository;

  @BeforeEach
  void setUp() {
    userSubscribedNewsletterCategoryRepository.deleteAll();

    UserSubscribedNewsletterCategory category1 = UserSubscribedNewsletterCategory.builder()
        .userEmail("user1@gmail.com")
        .categories(Arrays.asList(Category.CURRENT_AFFAIRS_SOCIETY, Category.TREND_LIFE))
        .build();

    UserSubscribedNewsletterCategory category2 = UserSubscribedNewsletterCategory.builder()
        .userEmail("user2@gmail.com")
        .categories(List.of(Category.TREND_LIFE))
        .build();

    userSubscribedNewsletterCategoryRepository.save(category1);
    userSubscribedNewsletterCategoryRepository.save(category2);
  }

  @Test
  void findByUserEmail_성공() {
    // given
    String userEmail = "user1@gmail.com";

    // when
    Optional<UserSubscribedNewsletterCategory> foundCategory = userSubscribedNewsletterCategoryRepository.findByUserEmail(userEmail);

    // then
    assertThat(foundCategory).isPresent();
    assertThat(foundCategory.get().getUserEmail()).isEqualTo(userEmail);
    assertThat(foundCategory.get().getCategories()).containsExactlyInAnyOrder(Category.CURRENT_AFFAIRS_SOCIETY, Category.TREND_LIFE);
  }

  @Test
  void findByUserEmail_실패_잘못된_이메일() {
    // given
    String userEmail = "nonexistentuser@gmail.com";

    // when
    Optional<UserSubscribedNewsletterCategory> foundCategory = userSubscribedNewsletterCategoryRepository.findByUserEmail(userEmail);

    // then
    assertThat(foundCategory).isNotPresent();
  }
}