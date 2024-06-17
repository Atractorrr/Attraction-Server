package run.attraction.api.v1.gmail.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.kafka.support.ProducerListener;
import org.springframework.stereotype.Component;
import run.attraction.api.v1.gmail.event.SubscribeVo;

@Component
@Slf4j
public class SubscribeProducerListener implements ProducerListener<String, SubscribeVo> {
  @Override
  public void onSuccess(ProducerRecord<String, SubscribeVo> producerRecord, RecordMetadata recordMetadata) {
    ProducerListener.super.onSuccess(producerRecord, recordMetadata);
    log.info("message body -> {}", producerRecord.value());
    log.info("message header -> {}", producerRecord.headers());
    log.info("user={} topic={}",producerRecord.key(), recordMetadata.toString());
  }

  @Override
  public void onError(ProducerRecord<String, SubscribeVo> producerRecord, RecordMetadata recordMetadata,
                      Exception exception) {
    ProducerListener.super.onError(producerRecord, recordMetadata, exception);
    log.info("error!!!");
    log.info("error!!! user={} topic={}",producerRecord.key(), recordMetadata.toString());
  }
}
