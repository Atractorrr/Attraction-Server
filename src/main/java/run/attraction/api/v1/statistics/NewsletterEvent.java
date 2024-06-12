package run.attraction.api.v1.statistics;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import run.attraction.api.v1.archive.AuditableEntity;
import run.attraction.api.v1.user.Occupation;

@Getter
@Entity
@Table(name = "statistics_newsletter_event")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class NewsletterEvent extends AuditableEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "newsletter_id", nullable = false)
  private Long newsletterId;

  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  private Occupation occupation;

  @Column(name = "age_group",nullable = false)
  @Enumerated(EnumType.STRING)
  private AgeGroup ageGroup;

  @Builder
  private NewsletterEvent(Long newsletterId, Occupation occupation, AgeGroup ageGroup) {
    this.newsletterId = newsletterId;
    this.occupation = occupation;
    this.ageGroup = ageGroup;
  }
}
