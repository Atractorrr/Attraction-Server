package run.attraction.api.v1.rank;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import run.attraction.api.v1.archive.AuditableEntity;

@Getter
@Entity
@Table(name = "consistency_rank_readbox_event")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class ReadBoxEvent extends AuditableEntity{

  @Id
  @Column(name = "user_email", length = 100, nullable = false)
  private String email;

  @Column(name = "consistency_value", nullable = false)
  private int consistencyValue;

  @Builder
  private ReadBoxEvent(String email, int consistencyValue){
    this.email = email;
    this.consistencyValue = consistencyValue;
  }

  public void updateConsistencyValue(int consistencyValue){
    this.consistencyValue = consistencyValue;
  }

}
