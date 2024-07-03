package run.attraction.api.v1.introduction.service;

import io.micrometer.core.annotation.Counted;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import run.attraction.api.v1.introduction.Category;
import run.attraction.api.v1.introduction.Newsletter;
import run.attraction.api.v1.introduction.Subscription;
import run.attraction.api.v1.introduction.UserSubscribedNewsletterCategory;
import run.attraction.api.v1.introduction.exception.ErrorMessages;
import run.attraction.api.v1.introduction.repository.NewsletterRepository;
import run.attraction.api.v1.introduction.repository.SubscriptionRepository;
import run.attraction.api.v1.introduction.repository.UserSubscribedNewsletterCategoryRepository;
import run.attraction.api.v1.user.repository.UserDetailRepository;

@Slf4j
@Service
@RequiredArgsConstructor
public class SubscriptionService {

  private final SubscriptionRepository subscriptionRepository;
  private final NewsletterRepository newsletterRepository;
  private final KafkaProducerService kafkaProducerService;
  private final UserSubscribedNewsletterCategoryRepository userSubscribedNewsletterCategoryRepository;
  private final UserDetailRepository userDetailRepository;

  @Transactional(readOnly = true)
  public Boolean isSubscribed(String userEmail, Long newsletterId ) {
    return subscriptionRepository.existsByUserEmailAndNewsletterId(userEmail, newsletterId);
  }

  @Transactional(readOnly = true)
  public Newsletter getNewsletter(Long newsletterId) {
    return newsletterRepository.findById(newsletterId)
        .orElseThrow(() -> new IllegalArgumentException(ErrorMessages.NOT_EXIST_NEWSLETTER.getViewName()));
  }

  @Counted("subscription.service")
  @Transactional
  public void subscribeNewsletter(String userEmail, Newsletter newsletter) {
    if (subscriptionRepository.existsByUserEmailAndNewsletterId(userEmail, newsletter.getId())) {
      throw new IllegalArgumentException(ErrorMessages.ALREADY_EXIST_NEWSLETTER.getViewName());
    }

    subscriptionRepository.save(subscribeNewsletterMethod(userEmail, newsletter.getId()));
    saveUserSubscribedNewsletterCategory(userEmail, newsletter.getCategory());
  }

  private Subscription subscribeNewsletterMethod(String userEmail, Long newsletterId) {
    return Subscription.builder()
        .userEmail(userEmail)
        .newsletterId(newsletterId)
        .build();
  }

  private void saveUserSubscribedNewsletterCategory(String userEmail, Category category) {
    UserSubscribedNewsletterCategory userSubscribedNewsletterCategory = userSubscribedNewsletterCategoryRepository.findByUserEmail(
        userEmail).orElseGet(() -> {
      var newCategory = createUserSubscribedNewsletterCategory(userEmail, category);
      userSubscribedNewsletterCategoryRepository.save(newCategory);
      return newCategory;
    });

    if(hasCategory(userSubscribedNewsletterCategory,category)) {
      return;
    }

    userSubscribedNewsletterCategory.getCategories().add(category);
    userSubscribedNewsletterCategoryRepository.save(userSubscribedNewsletterCategory);
  }

  private UserSubscribedNewsletterCategory createUserSubscribedNewsletterCategory(String userEmail, Category category) {
    return UserSubscribedNewsletterCategory.builder()
        .userEmail(userEmail)
        .categories(new ArrayList<>(List.of(category)))
        .build();
  }

  private boolean hasCategory(UserSubscribedNewsletterCategory userSubscribedNewsletterCategory, Category category) {
    return userSubscribedNewsletterCategory.getCategories().contains(category);
  }

  public void sendKafkaMessageForSubscription(String userEmail, Newsletter newsletter) {
    if(newsletter.isAutoSubscribeEnabled()) {
      String nickname = userDetailRepository.findNicknameByEmail(userEmail)
          .orElseThrow(() -> new IllegalArgumentException("회원정보에 없는 유저입니다"));

      log.info("카프카 메시지 전송 시작");
      kafkaProducerService.sendKafkaMessage(userEmail, nickname, newsletter.getSubscribeLink(), true, false);
      log.info("카프카 메시지 전송 종료");
    }
  }

  @Transactional(readOnly = true)
  public List<?> getUserSubscribedNewsletterCategories(String userEmail) {
    return userSubscribedNewsletterCategoryRepository.findByUserEmail(userEmail)
        .map(UserSubscribedNewsletterCategory::getCategories)
        .orElseGet(ArrayList::new);
  }
}
