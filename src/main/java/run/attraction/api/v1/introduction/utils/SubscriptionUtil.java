package run.attraction.api.v1.introduction.utils;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Component;
import run.attraction.api.v1.introduction.repository.NewsletterRepository;
import run.attraction.api.v1.introduction.repository.SubscriptionRepository;

@Component
public class SubscriptionUtil {

  public List<String> getNewsletterEmailsSubscribedByUser (String userEmail,
                                                           SubscriptionRepository subscriptionRepository,
                                                           NewsletterRepository newsletterRepository) {
    List<Long> newsletterIds = subscriptionRepository.findNewsletterIdsByUserEmailWithoutIsDeleted(userEmail);

    if (newsletterIds.isEmpty()) {
      return List.of();  // 비어 있는 경우 빈 리스트 반환
    }

    return newsletterIds.stream()
        .map(newsletterRepository::findById)
        .filter(Optional::isPresent)
        .map(newsletter -> newsletter.get().getEmail())
        .toList();
  }
}
