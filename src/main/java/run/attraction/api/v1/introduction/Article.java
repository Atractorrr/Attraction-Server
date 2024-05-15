package run.attraction.api.v1.introduction;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Article {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  private Newsletter newsletter;

  @Column(nullable = false, length = 100)
  private String title;

  @Column(nullable = false)
  private String thumbnail;

  @Column(nullable = false)
  private String contentUrl;

  @Column(nullable = false)
  private int readingTime;

  @Column(nullable = false)
  private Date receivedAt;

}
