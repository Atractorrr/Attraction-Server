package run.attraction.api.v1.introduction;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Getter;
import lombok.NoArgsConstructor;
import run.attraction.api.v1.archive.AuditableEntity;

@Getter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class UserSubscribedNewsletterCategory extends AuditableEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;

  @Column(nullable = false)
  String userEmail;

  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  Category category;

  @Column(nullable = false)
  @Default
  int categoryCount = 0;

  public void increaseCategoryCount() {
    this.categoryCount++;
  }

  public void decreaseCategoryCount() {
    if (this.categoryCount > 0) {
      this.categoryCount--;
    }
  }
}
