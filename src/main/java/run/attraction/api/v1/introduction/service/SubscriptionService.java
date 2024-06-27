package run.attraction.api.v1.introduction.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import run.attraction.api.v1.introduction.repository.SubscribeRepository;

@Service
@RequiredArgsConstructor
public class SubscriptionService {

  private final SubscribeRepository subscribeRepository;

  @Transactional(readOnly = true)
  public Boolean isSubscribed(String userEmail, Long newsletterId ) {
    return subscribeRepository.existsByUserEmailAndNewsletterId(userEmail, newsletterId);
  }
}
