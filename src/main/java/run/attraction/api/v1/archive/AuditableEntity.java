package run.attraction.api.v1.archive;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import java.time.LocalDateTime;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
public class AuditableEntity {

  @CreatedDate
  @Column(updatable = false)
  private LocalDateTime createAt;

  @LastModifiedDate
  private LocalDateTime updateAt;
}
