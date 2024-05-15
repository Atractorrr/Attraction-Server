package run.attraction.api.v1.introduction;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Newsletter {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, length = 20)
  private String name;

  @Column(nullable = false)
  private String description;

  @OneToMany(mappedBy = "newsletter", cascade = CascadeType.ALL)
  @JsonIgnore
  @Builder.Default
  private List<Article> articles = new ArrayList<>();

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private UploadDays uploadDays;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private Category category;

  @Column(nullable = false)
  private String mainLink;

  @Column(nullable = false)
  private String subscriptionLink;

  @Column(nullable = false)
  private String thumbnail;
}
