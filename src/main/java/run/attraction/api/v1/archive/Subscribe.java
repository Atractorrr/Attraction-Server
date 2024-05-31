package run.attraction.api.v1.archive;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.JoinColumn;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Subscribe {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;


  @ElementCollection
  @CollectionTable(name = "newsletter_ids", joinColumns = @JoinColumn(name = "subscribe_id"))
  @Column(nullable = false, name = "newsletter_id")
  @Builder.Default
  private List<Long> newsletterIds = new ArrayList<>();

  @Column(nullable = false)
  private String userEmail;

  public Subscribe(String userEmail) {
    this.userEmail = userEmail;
  }

  public void saveNewsletterId(Long newsletterId) {
    newsletterIds.add(newsletterId);
  }
}
