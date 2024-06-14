package run.attraction.api.v1.bookmark;


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
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class Bookmark {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;

  @Column(nullable = false)
  String userEmail;

  @Column(nullable = false, name = "article_id")
  @ElementCollection
  @CollectionTable(name = "bookmark_article_ids", joinColumns = @JoinColumn(name = "bookmark_id"))
  @Builder.Default
  List<Long> articleIds = new ArrayList<>();

  public Bookmark(String userEmail) {
    this.userEmail = userEmail;
    this.articleIds = new ArrayList<>();
  }
}
