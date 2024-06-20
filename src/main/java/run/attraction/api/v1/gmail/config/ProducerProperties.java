package run.attraction.api.v1.gmail.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "kafka.producer")
public record ProducerProperties(
    String bootstrapServers,
    String requestTimeoutMs,
    String deliveryTimeoutMs,
    String retries,
    String maxBlockMs,
    String acks
) {
}
