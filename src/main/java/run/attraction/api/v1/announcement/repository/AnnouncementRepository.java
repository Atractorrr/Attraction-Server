package run.attraction.api.v1.announcement.repository;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import run.attraction.api.v1.announcement.Post;

public interface AnnouncementRepository extends JpaRepository<Post, Long> {

    @Query("""
            SELECT p 
            FROM Post p
            WHERE p.id < :postId
            ORDER BY p.id DESC
            LIMIT 1
            """)
    Optional<Post> findTopByIdLessThan(Long postId);


    @Query("""
                SELECT p
                FROM Post p
                WHERE p.id > :postId
                ORDER BY p.id ASC
                LIMIT 1
            """)
    Optional<Post> findTopByIdGreaterThan(Long postId);

    @Query("""
            SELECT p
            FROM Post p
            WHERE p.isPinned = false
            ORDER BY p.createdAt DESC 
            """)
    Page<Post> findAllWithoutPinned(Pageable pageable);

    @Query("""
            SELECT p
            FROM Post p
            WHERE p.isPinned = true
            ORDER BY p.createdAt DESC 
            """)
    Page<Post> findAllWithPinned(Pageable pageable);

    @Query("""
            SELECT p
            FROM Post p
            WHERE (
                    (:searchType = 'TITLE' AND LOWER(p.title) LIKE LOWER(CONCAT('%', :searchQuery, '%'))) OR
                    (:searchType = 'CONTENT' AND LOWER(p.content) LIKE LOWER(CONCAT('%', :searchQuery, '%'))) OR
                    (:searchType = 'TITLE_CONTENT' AND
                        (LOWER(p.title) LIKE LOWER(CONCAT('%', :searchQuery, '%')) OR LOWER(p.content) LIKE LOWER(CONCAT('%', :searchQuery, '%')))
                    )
                )
            ORDER BY p.createdAt DESC
            """)
    Page<Post> findPostsBySearchQuery(Pageable pageable, String searchType, String searchQuery);
}
