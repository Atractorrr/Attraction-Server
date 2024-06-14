//package run.attraction.api.v1.gmail.event;
//
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.kafka.clients.producer.KafkaProducer;
//import org.apache.kafka.clients.producer.ProducerRecord;
//import org.springframework.context.event.EventListener;
//import org.springframework.stereotype.Component;
//
//@Component
//@Slf4j
//@RequiredArgsConstructor
//public class UserMailEventHandler {
//  private static final String LOGIN_TOPIC_NAME = "dev.login-gmail.jira-314.string";
//  private static final String SUBSCRIBE_TOPIC_NAME = "dev.subscribe-gmail.jira-315.string";
//
//  private final KafkaProducer<String, String> producer;
//
//  @EventListener
//  public void loggedEvent(UserLoggedEvent loggedEvent) {
//    log.info("로그인 event 진입");
//    ProducerRecord<String, String> record = new ProducerRecord<>(LOGIN_TOPIC_NAME, loggedEvent.email(), loggedEvent.token());
//    producer.send(record);
//    producer.flush();
//    producer.close();
//    log.info("로그인 event 처리 완료");
//  }
//
//  @EventListener
//  public void subscribedEvent(UserSubscribedEvent subscribedEvent) {
//    log.info("구독 event 진입");
//    ProducerRecord<String, String> record = new ProducerRecord<>(SUBSCRIBE_TOPIC_NAME, subscribedEvent.newsletterEmail(), subscribedEvent.token());
//    producer.send(record);
//    producer.flush();
//    producer.close();
//    log.info("구독 event 처리 완료");
//  }
//}
