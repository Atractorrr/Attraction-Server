package run.attraction.api.v1.introduction;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import run.attraction.api.v1.archive.AuditableEntity;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Newsletter extends AuditableEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, length = 100)
  private String email;

  @Column(nullable = false, length = 30)
  private String name;

  @Column(nullable = false)
  private String description;

  @Column(nullable = false)
  private String uploadDays;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private Category category;

  @Column(nullable = false)
  private String mainLink;

  @Column(nullable = false)
  private String subscribeLink;

  @Column(nullable = false)
  private String thumbnailUrl;


  private String nickname;

  @Column(name = "is_deleted", nullable = false, columnDefinition = "BOOLEAN DEFAULT FALSE")
  private boolean isDeleted;

  @Builder
  private Newsletter(
      Long id,
      String email,
      String name,
      String description,
      Category category,
      String mainLink,
      String subscribeLink,
      String thumbnailUrl,
      String uploadDays,
      String nickname
  ) {
    this.id = id;
    this.email = email;
    this.name = name;
    this.description = description;
    this.category = category;
    this.mainLink = mainLink;
    this.subscribeLink = subscribeLink;
    this.thumbnailUrl = thumbnailUrl;
    this.uploadDays = uploadDays;
    this.nickname = nickname;
  }
}
