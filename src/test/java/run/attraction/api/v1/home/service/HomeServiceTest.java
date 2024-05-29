package run.attraction.api.v1.home.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import run.attraction.api.v1.home.service.newsletter.HomeNewsletterServiceImpl;
import run.attraction.api.v1.user.Occupation;
import run.attraction.api.v1.user.User;
import run.attraction.api.v1.user.UserValidator;
import run.attraction.api.v1.user.repository.UserRepository;

public class HomeServiceTest {
  @Mock
  private UserRepository userRepository;

  @InjectMocks
  private HomeNewsletterServiceImpl homeNewsletterService;

  @InjectMocks
  private UserValidator userValidator;

  private User user;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.initMocks(this);

    user = User.builder()
        .email("test@gmail.com")
        .profileImg("beforeProfileImg")
        .backgroundImg("beforeBackgroundImg")
        .createdAt(LocalDate.of(2024,1,1))
        .updateAt(LocalDate.of(2024,1,1))
        .build();

    user.addExtraDetails(
        userValidator,
        "Kim",
        List.of("TREND_LIFE","ENTERTAINMENT","BUSINESS_FINANCIAL_TECHNOLOGY"),
        LocalDate.of(1999,10,12),
        LocalDate.of(2024,6,1),
        Occupation.SERVICE);

    when(userRepository.findById("test@gmail.com")).thenReturn(Optional.of(user));
  }

  @Test
  @DisplayName("메이페이지 카테고리 API 테스트(email이 있는 경우)")
  void getCategoriesByEamilTest() {
    //Given
    String email = "test@gmail.com";

    //When
    final List<String> categoriesByEmail = homeNewsletterService.getUserCategories(email);
    System.out.println(categoriesByEmail);
    //then
    assertEquals(categoriesByEmail.size(), 13);
    assertTrue(categoriesByEmail.subList(1,4).containsAll(List.of("TREND_LIFE","ENTERTAINMENT","BUSINESS_FINANCIAL_TECHNOLOGY")));
  }

  @Test
  @DisplayName("메이페이지 카테고리 API 테스트(email이 null인 경우)")
  void getCategoriesByNullTest() {

    //When
    final List<String> categoriesByEmail = homeNewsletterService.getDefaultCategories();
    System.out.println(categoriesByEmail);

    //then
    assertEquals(categoriesByEmail.size(), 13);
  }

}
