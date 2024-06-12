package run.attraction.api.v1.introduction;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
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

  @ElementCollection
  @Enumerated(EnumType.STRING)
  @Builder.Default
  List<Category> categories = new ArrayList<>();
}
