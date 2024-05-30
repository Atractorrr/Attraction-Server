package run.attraction.api.v1.archive;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import run.attraction.api.v1.introduction.Newsletter;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Subscribe {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @OneToMany(fetch = FetchType.LAZY)
  @JoinColumn(name = "subscribe_id")
  @Builder.Default
  private List<Newsletter> newsletters = new ArrayList<>();

  @Column(nullable = false)
  private String userEmail;

  public Subscribe(String userEmail) {
    this.userEmail = userEmail;
  }

  public void saveNewsletter(Newsletter newsletter) {
    newsletters.add(newsletter);
  }
}
