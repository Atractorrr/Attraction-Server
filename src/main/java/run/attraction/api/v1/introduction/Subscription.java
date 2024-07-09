package run.attraction.api.v1.introduction;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Getter;
import lombok.NoArgsConstructor;
import run.attraction.api.v1.archive.AuditableEntity;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Subscription extends AuditableEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private Long newsletterId;

  @Column(nullable = false)
  private String userEmail;

  private String unsubscribeUrl;

  @Default
  @Column(nullable = false, columnDefinition = "BOOLEAN DEFAULT FALSE")
  private boolean isDeleted = false;

  public void saveNewsletterId(Long newsletterId) {
    this.newsletterId = newsletterId;
  }

  public void unsubscribeNewsletter() {
    isDeleted = true;
  }
}
