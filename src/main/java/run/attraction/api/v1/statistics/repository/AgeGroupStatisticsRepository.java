package run.attraction.api.v1.statistics.repository;

import java.util.List;
import java.time.LocalDate;
import org.springframework.data.jpa.repository.JpaRepository;
import run.attraction.api.v1.statistics.AgeGroupStatistics;

public interface AgeGroupStatisticsRepository extends JpaRepository<AgeGroupStatistics, Long> {
   List<AgeGroupStatistics> findAllByCreatedAt(LocalDate createdAt);
}
