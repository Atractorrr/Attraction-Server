package run.attraction.api.v1.archive;

import com.querydsl.core.annotations.QueryProjection;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReadBox {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private Long articleId;

  @Column(nullable = false)
  private Long userId;

  @Setter
  @Column(nullable = false)
  private int percentage;


  private Date updateAt;

  @QueryProjection
  public ReadBox(Long id, Long articleId, Long userId, int percentage) {
    this.id = id;
    this.articleId = articleId;
    this.userId = userId;
    this.percentage = percentage;
  }
}
