package run.attraction.api.v1.statistics;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import run.attraction.api.v1.user.Occupation;


@Getter
@Entity
@Table(name = "occupation_statistics")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OccupationStatistics {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private Long newsletterId;

  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  private Occupation occupation;

  @Column(name = "created_at",nullable = false)
  private LocalDate createdAt;

  @Builder
  private OccupationStatistics(Long newsletterId,Occupation occupation, LocalDate createdAt) {
    this.newsletterId = newsletterId;
    this.occupation = occupation;
    this.createdAt = createdAt;
  }

}
