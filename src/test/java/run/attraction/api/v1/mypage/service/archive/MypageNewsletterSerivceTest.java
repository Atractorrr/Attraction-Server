package run.attraction.api.v1.mypage.archive.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.TestPropertySource;
import run.attraction.api.v1.archive.Subscribe;
import run.attraction.api.v1.archive.repository.SubscribeRepository;
import run.attraction.api.v1.introduction.Newsletter;
import run.attraction.api.v1.introduction.repository.NewsletterRepository;
import run.attraction.api.v1.mypage.service.archive.newsletter.MypageNewsletterServiceImpl;
import run.attraction.api.v1.mypage.service.dto.archive.newsletter.MypageNewsletterDetail;

public class MypageNewsletterSerivceTest {
  @Mock
  private SubscribeRepository subscribeRepository;

  @Mock
  private NewsletterRepository newsletterRepository;

  @InjectMocks
  private MypageNewsletterServiceImpl mypageNewsletterService;

  private List<Newsletter> newsletters;
  private Subscribe subscribe;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.initMocks(this);

    final Newsletter newsletter1 = Newsletter.builder()
        .id(1L)
        .name("뉴닉")
        .thumbnailUrl("뉴닉썸네일Url")
        .build();

    final Newsletter newsletter2 = Newsletter.builder()
        .id(2L)
        .name("까탈로그")
        .thumbnailUrl("까탈로그썸네일Url")
        .build();

    newsletters = List.of(newsletter1, newsletter2);

    subscribe = Subscribe.builder()
        .id(1L)
        .userEmail("test@gmail.com")
        .newsletterIds(List.of(newsletter1.getId(), newsletter2.getId()))
        .build();

    when(subscribeRepository.findByUserEmail("test@gmail.com")).thenReturn(Optional.of(subscribe));
    when(newsletterRepository.findNewslettersByNewsletterIds(subscribe.getNewsletterIds())).thenReturn(newsletters);
  }

  @Test
  @DisplayName("마이페이지 구독리스트 API 테스트")
  void getUserCalendar(){
    //Given
    String email = "test@gmail.com";

    //When
    final List<MypageNewsletterDetail> subscribes = mypageNewsletterService.getSubscribesByEmail(email);

    //Then
    assertEquals(subscribes.size(),2);
    assertEquals(subscribes.stream().map(MypageNewsletterDetail::id).toList(),List.of(1L,2L));
    assertEquals(subscribes.stream().map(MypageNewsletterDetail::title).toList(),List.of("뉴닉","까탈로그"));
    assertEquals(subscribes.stream().map(MypageNewsletterDetail::thumbnailUrl).toList(),List.of("뉴닉썸네일Url","까탈로그썸네일Url"));
  }

}