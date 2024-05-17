package run.attraction.api.v1.auth.token;

import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

@Getter
@RedisHash(value = "logoutAccessToken")    //default 키값 설정
public class LogoutAccessToken {

  @Id
  private String id;

  @TimeToLive   // 주의 ) 초단위로 적용해야함.
  private Long expiration;

  @Builder
  private LogoutAccessToken(String id, Long expiration) {
    this.id = id;
    this.expiration = expiration;
  }
}
