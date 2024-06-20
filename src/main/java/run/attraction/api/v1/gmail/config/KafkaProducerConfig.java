package run.attraction.api.v1.gmail.config;

import java.util.HashMap;
import java.util.Map;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;
import run.attraction.api.v1.gmail.event.SubscribeVo;

@Configuration
@EnableConfigurationProperties({ProducerProperties.class})
public class KafkaProducerConfig {
  private final ProducerProperties properties;

  public KafkaProducerConfig(ProducerProperties properties) {
    this.properties = properties;
  }

  @Bean
  public ProducerFactory<String, SubscribeVo> subscribeProducerFactory() {

    Map<String, Object> props = new HashMap<>();
    props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, properties.bootstrapServers());
    props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
    props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class.getName());
    props.put(ProducerConfig.REQUEST_TIMEOUT_MS_CONFIG, properties.requestTimeoutMs());
    props.put(ProducerConfig.DELIVERY_TIMEOUT_MS_CONFIG, properties.deliveryTimeoutMs());
    props.put(ProducerConfig.RETRIES_CONFIG, properties.retries());
    props.put(ProducerConfig.MAX_BLOCK_MS_CONFIG, properties.maxBlockMs());
    props.put(ProducerConfig.ACKS_CONFIG, properties.acks());

    return new DefaultKafkaProducerFactory<>(props);
  }

  @Bean(name = "subscribe")
  public KafkaTemplate<String, SubscribeVo> subscribeKafkaTemplate(SubscribeProducerListener subscribeProducerListener) {
    final KafkaTemplate<String, SubscribeVo> subscribeKafkaTemplate = new KafkaTemplate<>(subscribeProducerFactory());
    subscribeKafkaTemplate.setProducerListener(subscribeProducerListener);
    return subscribeKafkaTemplate;
  }
}
