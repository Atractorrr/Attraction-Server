package run.attraction.api.v1.introduction.utils;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Component;
import run.attraction.api.v1.introduction.repository.NewsletterRepository;
import run.attraction.api.v1.introduction.repository.SubscribeRepository;

@Component
public class SubscriptionUtil {

  public List<String> getNewsletterEmailsSubscribedByUser(String userEmail, SubscribeRepository subscribeRepository,
                                                       NewsletterRepository newsletterRepository) {
    return subscribeRepository.findByUserEmail(userEmail)
        .map(subscription -> subscription.getNewsletterIds()
            .stream()
            .map(newsletterRepository::findById)
            .filter(Optional::isPresent)
            .map(newsletter -> newsletter.get().getEmail())
            .toList())
        .orElseGet(List::of);
  }
}
