package run.attraction.api.v1.rank.repository;

import java.util.List;
import java.time.LocalDate;
import org.springframework.data.jpa.repository.JpaRepository;
import run.attraction.api.v1.rank.ConsistencyRank;
import run.attraction.api.v1.rank.ExtensiveRank;

public interface ConsistencyRankRepository extends JpaRepository<ConsistencyRank, Long> {
  List<ConsistencyRank> findAllByCreatedAt(LocalDate createdAt);
}
