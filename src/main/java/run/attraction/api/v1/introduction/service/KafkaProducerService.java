package run.attraction.api.v1.introduction.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import run.attraction.api.v1.introduction.event.AutoSubscribeVo;


@Service
@Slf4j
public class KafkaProducerService {
  private final KafkaTemplate<String, AutoSubscribeVo> autoSubscribeVoKafkaTemplate;
  private final String autoSubscribeTopic;

  @Autowired
  public KafkaProducerService(@Qualifier("auto-subscribe") KafkaTemplate<String, AutoSubscribeVo> autoSubscribeVoKafkaTemplate,
                              @Value("${kafka.topic.auto-subscribe}") String autoSubscribeTopic) {
    this.autoSubscribeVoKafkaTemplate = autoSubscribeVoKafkaTemplate;
    this.autoSubscribeTopic = autoSubscribeTopic;
  }

  public void sendKafkaMessage(String userEmail, String userName, String subscribeUrl, boolean isAgreePersonalInfoCollection, boolean isAgreeAdInfoReception) {
    AutoSubscribeVo message = new AutoSubscribeVo(userEmail, userName, subscribeUrl, isAgreePersonalInfoCollection, isAgreeAdInfoReception);
    try {
      autoSubscribeVoKafkaTemplate.send(autoSubscribeTopic, userEmail, message);
    } catch (Exception e) {
      log.error("Faild to send message", e);
    }
  }
}