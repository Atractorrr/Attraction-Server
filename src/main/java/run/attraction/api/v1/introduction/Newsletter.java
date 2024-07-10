package run.attraction.api.v1.introduction;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Getter;
import lombok.NoArgsConstructor;
import run.attraction.api.v1.archive.AuditableEntity;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
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

  @Default
  @Column(nullable = false, columnDefinition = "BOOLEAN DEFAULT FALSE")
  private boolean isAutoSubscribeEnabled = false;

  @Default
  @Column(nullable = false, columnDefinition = "BOOLEAN DEFAULT FALSE")
  private boolean hasConfirmationEmail = false;

  @Default
  @Column(nullable = false, columnDefinition = "BOOLEAN DEFAULT TRUE")
  private boolean isAgreePersonalInfoCollection = true;

  @Default
  @Column(nullable = false, columnDefinition = "BOOLEAN DEFAULT FALSE")
  private boolean isAgreeAdInfoReception = false;

  private String nickname;

  @Default
  @Column(nullable = false, columnDefinition = "BOOLEAN DEFAULT FALSE")
  private boolean isDeleted = false;
}
