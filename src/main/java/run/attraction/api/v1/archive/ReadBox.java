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

  @Column(nullable = false)
  @Builder.Default
  private int readPercentage = 0;

  private LocalDate readDate;

  @QueryProjection
  public ReadBox(Long id, Long articleId, String userEmail, int readPercentage) {
    this.id = id;
    this.articleId = articleId;
    this.userEmail = userEmail;
    this.readPercentage = readPercentage;
  }

  public ReadBox(String userEmail, Long articleId) {
    this.articleId = articleId;
    this.userEmail = userEmail;
  }

  public void updateReadPercentagePercentage(int readPercentage) {
    this.readPercentage = readPercentage;
    if(isFullReadPercentage(readPercentage)) {
      updateReadDate();
    }
  }

  private boolean isFullReadPercentage(int readPercentage) {
    return readPercentage == PULL_PERCENTAGE;
  }

  private void updateReadDate() {
      this.readDate = LocalDate.now();
  }
}
