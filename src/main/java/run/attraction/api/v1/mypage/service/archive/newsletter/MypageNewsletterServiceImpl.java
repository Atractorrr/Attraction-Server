package run.attraction.api.v1.mypage.service.archive.newsletter;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import run.attraction.api.v1.introduction.Subscription;
import run.attraction.api.v1.introduction.exception.ErrorMessages;
import run.attraction.api.v1.introduction.repository.SubscriptionRepository;
import run.attraction.api.v1.introduction.repository.NewsletterRepository;
import run.attraction.api.v1.mypage.service.dto.archive.newsletter.MypageNewsletterDetail;

@Component
@RequiredArgsConstructor
public class MypageNewsletterServiceImpl implements MypageNewsletterService {
  private final SubscriptionRepository subscriptionRepository;
  private final NewsletterRepository newsletterRepository;

  @Override
  public List<MypageNewsletterDetail> getSubscribesByEmail(String email) {
    final List<Subscription> subscriptions = subscriptionRepository.findByUserEmail(email);

    if(subscriptions.isEmpty()) {
      return List.of();
    }

    return subscriptions
        .stream()
        .map(subscription -> newsletterRepository.findById(subscription.getNewsletterId())
            .map(newsletter -> new MypageNewsletterDetail(
                newsletter.getId(),
                newsletter.getThumbnailUrl(),
                newsletter.getName()))
            .orElseThrow(() -> new IllegalArgumentException(ErrorMessages.NOT_EXIST_NEWSLETTER.getViewName()))).toList();
  }
}