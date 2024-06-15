package run.attraction.api.v1.gmail.config;

import java.util.Properties;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;
import run.attraction.api.v1.gmail.event.SubscribeVo;

@Configuration
public class KafkaProducerConfig {
  @Bean
  public ProducerFactory<String, String> loginProducerFactory() {

    final Properties configs = new Properties();
    configs.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
    configs.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
    configs.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
    configs.put(ProducerConfig.ACKS_CONFIG, "1");

    return new DefaultKafkaProducerFactory(configs);
  }

  @Bean
  public ProducerFactory<String, SubscribeVo> subscribeProducerFactory() {

    final Properties configs = new Properties();
    configs.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
    configs.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
    configs.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class.getName());
    configs.put(ProducerConfig.ACKS_CONFIG, "1");

    return new DefaultKafkaProducerFactory(configs);
  }

  @Bean(name = "login")
  public KafkaTemplate<String, String> loginKafkaTemplate() {
    return new KafkaTemplate<>(loginProducerFactory());
  }

  @Bean(name = "subscribe")
  public KafkaTemplate<String, SubscribeVo> subscribeKafkaTemplate() {
    return new KafkaTemplate<>(subscribeProducerFactory());
  }
}
