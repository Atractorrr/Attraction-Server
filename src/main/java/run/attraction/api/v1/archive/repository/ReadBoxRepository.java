package run.attraction.api.v1.archive.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import run.attraction.api.v1.archive.ReadBox;

public interface ReadBoxRepository extends JpaRepository<ReadBox, Long> {
  @Query("SELECT rb FROM ReadBox rb WHERE rb.userEmail = :userEmail AND rb.readPercentage = 100")
  List<ReadBox> findCompletedReadBoxByEmail(String userEmail);

  @Query("SELECT rb FROM ReadBox rb WHERE rb.userEmail = :userEmail AND rb.articleId = :articleId")
  Optional<ReadBox> findByUserEmailAndArticleId(String userEmail, Long articleId);

  List<ReadBox> findByUserEmail(String email);

  @Query("""  
    SELECT rb.userEmail, COUNT(rb)
    FROM ReadBox rb 
    WHERE rb.readDate BETWEEN :startDate AND :endDate
    AND rb.readDate <> :excludeDate
    AND rb.readPercentage = 100 
    GROUP BY rb.userEmail
    ORDER BY COUNT(rb) DESC
  """)
  List<Object[]> findTop10ExtensiveUsers(LocalDate startDate, LocalDate endDate,LocalDate excludeDate);

}
