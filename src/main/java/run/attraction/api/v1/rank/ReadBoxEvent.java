package run.attraction.api.v1.rank;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "consistency_rank_readbox_event")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReadBoxEvent{

  @Id
  @Column(name = "user_email", length = 100, nullable = false)
  private String email;

  @Column(name = "consistency_value", nullable = false)
  private int consistencyValue;

  @Column(name = "created_at")
  private LocalDateTime createdAt;

  @Column(name = "modified_at")
  private LocalDateTime modifiedAt;

  @Builder
  private ReadBoxEvent(String email, int consistencyValue, LocalDateTime createdAt, LocalDateTime modifiedAt) {
    this.email = email;
    this.consistencyValue = consistencyValue;
    this.createdAt = createdAt;
    this.modifiedAt = modifiedAt;
  }

  public void updateConsistencyValue(int consistencyValue){
    this.consistencyValue = consistencyValue;
    this.modifiedAt = LocalDateTime.now();
  }

}
