package run.attraction.api.v1.gmail.event;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.EventListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class UserMailEventHandler {

  @Value("${kafka.topic.subscribe}")
  private String subscribeTopicName;

  @Qualifier("subscribe")
  private final KafkaTemplate<String, SubscribeVo> subscribeKafkaTemplate;

  @Async("threadPoolTaskExecutor")
  @EventListener
  public void subscribedEvent(UserSubscribedEvent subscribedEvent) {
    log.info("구독 event 진입");
    ProducerRecord<String, SubscribeVo> record = new ProducerRecord<>(
        subscribeTopicName,
        subscribedEvent.userEmail(),
        new SubscribeVo(subscribedEvent.newsletterEmail(), subscribedEvent.token())
    );

    subscribeKafkaTemplate.send(record);
    subscribeKafkaTemplate.flush();
    subscribeKafkaTemplate.destroy();
    log.info("구독 event 처리 완료");
  }
}