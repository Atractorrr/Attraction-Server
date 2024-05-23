package run.attraction.api.v1.archive;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDate;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class Article {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, length = 100)
  private String newsletterEmail;

  @Column(nullable = false, length = 100)
  private String userEmail;

  @Column(nullable = false, length = 100)
  private String title;

  @Column(nullable = false)
  private String thumbnailUrl;

  @Column(nullable = false)
  private String contentUrl;

  @Column(nullable = false)
  private int readingTime;

  @Column(nullable = false)
  private String contentSummary;

  @Column(nullable = false)
  boolean isDeleted = false;

  @Column(nullable = false)
  private Date receivedAt;

  @Column(nullable = false)
  LocalDate createAt;
}
