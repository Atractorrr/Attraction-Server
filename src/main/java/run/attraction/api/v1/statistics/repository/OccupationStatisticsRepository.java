package run.attraction.api.v1.statistics.repository;

import java.time.LocalDate;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import run.attraction.api.v1.statistics.OccupationStatistics;

public interface OccupationStatisticsRepository extends CrudRepository<OccupationStatistics, Long> {
  List<OccupationStatistics> findAllByCreatedAt(LocalDate createdAt);
}
