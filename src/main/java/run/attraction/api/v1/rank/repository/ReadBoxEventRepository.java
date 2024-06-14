package run.attraction.api.v1.rank.repository;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import run.attraction.api.v1.rank.ReadBoxEvent;

public interface ReadBoxEventRepository extends JpaRepository<ReadBoxEvent, String> {
  @Query(value = """
        SELECT rbe.user_email, rbe.consistency_value, rbe.modified_at
        FROM consistency_rank_readbox_event rbe 
        WHERE rbe.modified_at BETWEEN :startDate AND :endDate
        ORDER BY rbe.consistency_value DESC
        LIMIT 10
    """, nativeQuery = true)
  List<Object[]> findTop10ConsistencyUserEmail(LocalDateTime startDate,
                                              LocalDateTime endDate);
}
