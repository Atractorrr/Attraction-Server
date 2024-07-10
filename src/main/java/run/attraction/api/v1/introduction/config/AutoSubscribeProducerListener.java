package run.attraction.api.v1.introduction.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.kafka.support.ProducerListener;
import org.springframework.stereotype.Component;
import run.attraction.api.v1.introduction.event.AutoSubscribeVo;

@Component
@Slf4j
public class AutoSubscribeProducerListener implements ProducerListener<String, AutoSubscribeVo> {
  @Override
  public void onSuccess(ProducerRecord<String, AutoSubscribeVo> producerRecord, RecordMetadata recordMetadata) {
    ProducerListener.super.onSuccess(producerRecord, recordMetadata);
    log.info("message body -> {}", producerRecord.value());
    log.info("message header -> {}", producerRecord.headers());
    log.info("data={} topic={}",producerRecord.key(), recordMetadata.toString());
  }

  @Override
  public void onError(ProducerRecord<String, AutoSubscribeVo> producerRecord, RecordMetadata recordMetadata,
                      Exception exception) {
    ProducerListener.super.onError(producerRecord, recordMetadata, exception);
    log.error("error data={} topic={}",producerRecord.key(), recordMetadata.toString());
  }
}
