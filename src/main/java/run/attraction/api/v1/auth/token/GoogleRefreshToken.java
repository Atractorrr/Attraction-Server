package run.attraction.api.v1.auth.token;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "google_refresh_token")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GoogleRefreshToken {

  @Id
  @Column(name = "email", length = 100)
  private String email;

  @Column(name = "refresh_token")
  private String token;

  @Column(name = "should_reissue_token", nullable = false, columnDefinition = "TINYINT(1) default 0")
  private boolean shouldReissueToken;

  @Builder
  private GoogleRefreshToken(String email, String token) {
    this.email = email;
    this.token = token;
  }

  public void updateState(boolean shouldReissueToken){
    this.shouldReissueToken=shouldReissueToken;
  }

  public void updateStateAndToken(boolean shouldReissueToken, String token){
    this.token = token;
    this.shouldReissueToken=shouldReissueToken;
  }

}
