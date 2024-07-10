package run.attraction.api.v1.bookmark.repository;


import io.lettuce.core.dynamic.annotation.Param;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import run.attraction.api.v1.bookmark.Bookmark;

public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {

  @Query("""
      SELECT b.articleIds
      FROM Bookmark b 
      where b.userEmail = :userEmail
    """)
  Optional<List<Long>> findBookmarkArticleIdsByUserEmail(@Param("userEmail") String userEmail);

  Optional<Bookmark> findByUserEmail(String userEmail);
}
