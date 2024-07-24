package run.attraction.api.v1.introduction.service;

import io.micrometer.core.annotation.Counted;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import run.attraction.api.v1.introduction.Category;
import run.attraction.api.v1.introduction.Newsletter;
import run.attraction.api.v1.introduction.Subscription;
import run.attraction.api.v1.introduction.UserSubscribedNewsletterCategory;
import run.attraction.api.v1.introduction.dto.response.SubscribedNewsletterResponse;
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
    Optional<UserSubscribedNewsletterCategory> userSubscribedNewsletterCategory = userSubscribedNewsletterCategoryRepository.findByUserEmailAndCategory(
        userEmail, category);

    if(userSubscribedNewsletterCategory.isPresent()) {
      userSubscribedNewsletterCategory.get().increaseCategoryCount();

      userSubscribedNewsletterCategoryRepository.save(userSubscribedNewsletterCategory.get());
    }else{
      var newCategorySubscribedByUser = createUserSubscribedNewsletterCategory(userEmail, category);

      userSubscribedNewsletterCategoryRepository.save(newCategorySubscribedByUser);
    }
  }

  private UserSubscribedNewsletterCategory createUserSubscribedNewsletterCategory(String userEmail, Category category) {
    return UserSubscribedNewsletterCategory.builder()
        .userEmail(userEmail)
        .category(category)
        .build();
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
  public List<Category> getUserSubscribedNewsletterCategories(String userEmail) {
    return userSubscribedNewsletterCategoryRepository.findByUserEmail(userEmail)
        .stream()
        .map(UserSubscribedNewsletterCategory::getCategory)
        .toList();
  }

  public List<SubscribedNewsletterResponse> getUserSubscribedNewsletters(String userEmail) {
    List<Long> subscribedNewslettersByUser = subscriptionRepository.findByUserEmail(userEmail)
        .stream()
        .map(Subscription::getNewsletterId)
        .toList();
    List<Newsletter> newsletters = newsletterRepository.findAllById(subscribedNewslettersByUser);

    return newsletters
        .stream()
        .map(SubscribedNewsletterResponse::new)
        .toList();
  }

  public void unsubscribeNewsletter(String userEmail, Long newsletterId) {
    Subscription subscription = subscriptionRepository.findByUserEmailAndNewsletterId(userEmail,
        newsletterId).orElseThrow(() -> new IllegalArgumentException("구독 정보가 없는 뉴스레터 입니다"));

    subscription.unsubscribeNewsletter();
    subscriptionRepository.save(subscription);
    unsubscribeNewsletterCategory(userEmail, newsletterId);
  }

  private void unsubscribeNewsletterCategory(String userEmail, Long newsletterId) {
    Newsletter newsletter = newsletterRepository.findById(newsletterId)
        .orElseThrow(() -> new IllegalArgumentException(ErrorMessages.NOT_EXIST_NEWSLETTER.getViewName()));
    var userSubscribedNewsletterCategory = userSubscribedNewsletterCategoryRepository.findByUserEmailAndCategory(
        userEmail, newsletter.getCategory()).orElseThrow(() -> new IllegalArgumentException("구독 기록이 없는 뉴스레터의 카테고리입니다"));

    userSubscribedNewsletterCategory.decreaseCategoryCount();
    userSubscribedNewsletterCategoryRepository.save(userSubscribedNewsletterCategory);
  }
}
