package run.attraction.api.v1.archive;

import com.querydsl.core.annotations.QueryProjection;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
public class ReadBox extends AuditableEntity {

  private static int PULL_PERCENTAGE = 100;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private Long articleId;

  @Column(nullable = false)
  private String userEmail;

  @Setter
  @Column(nullable = false)
  private int percentage;

  @Setter
  private LocalDate readDate;

  @QueryProjection
  public ReadBox(Long id, Long articleId, String userEmail, int percentage) {
    this.id = id;
    this.articleId = articleId;
    this.userEmail = userEmail;
    this.percentage = percentage;
  }
}
