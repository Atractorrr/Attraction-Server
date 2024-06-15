package run.attraction.api.v1.gmail.event;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.event.EventListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class UserMailEventHandler {
  private static final String LOGIN_TOPIC_NAME = "dev.login-gmail.jira-314.string";
  private static final String SUBSCRIBE_TOPIC_NAME = "dev.subscribe-gmail.jira-315.json";

  @Qualifier("login")
  private final KafkaTemplate<String, String> loginKafkaTemplate;
  @Qualifier("subscribe")
  private final KafkaTemplate<String, SubscribeVo> subscribeKafkaTemplate;

  @EventListener
  public void loggedEvent(UserLoggedEvent loggedEvent) {
    log.info("로그인 event 진입");
    ProducerRecord<String, String> record = new ProducerRecord<>(LOGIN_TOPIC_NAME, loggedEvent.email(), loggedEvent.token());
    loginKafkaTemplate.send(record);

    log.info("로그인 event 처리 완료");
  }

  @EventListener
  public void subscribedEvent(UserSubscribedEvent subscribedEvent) {
    log.info("구독 event 진입");
    ProducerRecord<String, SubscribeVo> record = new ProducerRecord<>(
        SUBSCRIBE_TOPIC_NAME,
        subscribedEvent.newsletterEmail(),
        new SubscribeVo(subscribedEvent.userEmail(), subscribedEvent.token())
    );

    subscribeKafkaTemplate.send(record);
    log.info("구독 event 처리 완료");
  }
}
