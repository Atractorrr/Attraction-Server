package run.attraction.api.v1.mypage.service.archive;

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
import run.attraction.api.v1.introduction.Subscription;
import run.attraction.api.v1.introduction.repository.SubscriptionRepository;
import run.attraction.api.v1.introduction.Newsletter;
import run.attraction.api.v1.introduction.repository.NewsletterRepository;
import run.attraction.api.v1.mypage.service.archive.newsletter.MypageNewsletterServiceImpl;
import run.attraction.api.v1.mypage.service.dto.archive.newsletter.MypageNewsletterDetail;

public class MypageNewsletterSerivceTest {
  @Mock
  private SubscriptionRepository subscriptionRepository;

  @Mock
  private NewsletterRepository newsletterRepository;

  @InjectMocks
  private MypageNewsletterServiceImpl mypageNewsletterService;

  private List<Newsletter> newsletters;
  private Subscription subscription1;
  private Subscription subscription2;

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

    subscription1 = Subscription.builder()
        .id(1L)
        .userEmail("test@gmail.com")
        .newsletterId(newsletter1.getId())
        .build();

    subscription2 = Subscription.builder()
        .id(2L)
        .userEmail("test@gmail.com")
        .newsletterId(newsletter2.getId())
        .build();

    when(subscriptionRepository.findByUserEmail("test@gmail.com")).thenReturn(List.of(subscription1, subscription2));
    when(newsletterRepository.findById(newsletter1.getId())).thenReturn(Optional.of(newsletter1));
    when(newsletterRepository.findById(newsletter2.getId())).thenReturn(Optional.of(newsletter2));
  }

//  @Test
//  @DisplayName("마이페이지 구독리스트 API 테스트")
//  void getUserCalendar(){
//    //Given
//    String email = "test@gmail.com";
//
//    //When
//    final List<MypageNewsletterDetail> subscribes = mypageNewsletterService.getSubscribesByEmail(email);
//
//    //Then
//    assertEquals(2, subscribes.size());
//    assertEquals(List.of(1L, 2L), subscribes.stream().map(MypageNewsletterDetail::id).toList());
//    assertEquals(List.of("뉴닉", "까탈로그"), subscribes.stream().map(MypageNewsletterDetail::title).toList());
//    assertEquals(List.of("뉴닉썸네일Url", "까탈로그썸네일Url"), subscribes.stream().map(MypageNewsletterDetail::thumbnailUrl).toList());
//  }

}