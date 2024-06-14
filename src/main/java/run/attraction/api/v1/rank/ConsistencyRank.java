package run.attraction.api.v1.rank;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "consistency_rank")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ConsistencyRank implements Rank{
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String email;

  @Column(nullable = false)
  private int value;

  @Column(name = "created_at",nullable = false)
  private LocalDate createdAt;

  @Builder
  private ConsistencyRank(String email, int value, LocalDate createdAt) {
    this.email = email;
    this.value = value;
    this.createdAt = createdAt;
  }

  @Override
  public String getEmail() {
    return email;
  }

  @Override
  public int getValue() {
    return value;
  }

}
