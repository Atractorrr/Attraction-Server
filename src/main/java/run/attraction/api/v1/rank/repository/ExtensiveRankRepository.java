package run.attraction.api.v1.rank.repository;

import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import run.attraction.api.v1.rank.ExtensiveRank;

public interface ExtensiveRankRepository extends JpaRepository<ExtensiveRank, Long> {
  List<ExtensiveRank> findAllByCreatedAt(LocalDate createdAt);
}
