//package run.attraction.api.v1.bookmark.repository;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//import java.util.Arrays;
//import java.util.List;
//import java.util.Optional;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.transaction.annotation.Transactional;
//import run.attraction.api.v1.bookmark.Bookmark;
//
//@DataJpaTest
//@ActiveProfiles("test")
//@Transactional
//class BookmarkRepositoryTest {
//
//  @Autowired
//  BookmarkRepository bookmarkRepository;
//
//  @Autowired
//  private TestEntityManager entityManager;
//
//  private String userEmail;
//  private Bookmark bookmark;
//
//  @BeforeEach
//  void setUp() {
//    userEmail = "test_user@example.com";
//    bookmark = Bookmark.builder()
//        .userEmail(userEmail)
//        .articleIds(Arrays.asList(1L, 2L, 3L))
//        .build();
//    entityManager.persist(bookmark);
//    entityManager.flush();
//  }
//
//  @Test
//  void findBookmarkArticleIdsByUserEmail_북마크된아티클id찾기() {
//    // when
//    Optional<List<Long>> foundArticleIds = bookmarkRepository.findBookmarkArticleIdsByUserEmail(userEmail);
//
//    // then
//    assertThat(foundArticleIds).isPresent();
//    assertThat(foundArticleIds.get()).containsExactly(1L, 2L, 3L);
//  }
//
//  @Test
//  void findBookmarkArticleIdsByUserEmail_사용자이메일로북마크찾기() {
//    // when
//    Optional<Bookmark> foundBookmark = bookmarkRepository.findByUserEmail(userEmail);
//
//    // then
//    assertThat(foundBookmark).isPresent();
//    assertThat(foundBookmark.get().getUserEmail()).isEqualTo(userEmail);
//  }
//
//  @Test
//  void findBookmarkArticleIdsByUserEmail_사용자이메일로북마크기사아이디찾기_북마크없음() {
//    // given
//    String nonExistentUserEmail = "no_bookmark@example.com";
//
//    // when
//    Optional<List<Long>> foundArticleIds = bookmarkRepository.findBookmarkArticleIdsByUserEmail(nonExistentUserEmail);
//
//    // then
//    assertThat(foundArticleIds).isPresent();
//    assertThat(foundArticleIds.get()).isEmpty();
//  }
//
//  @Test
//  void findBookmarkArticleIdsByUserEmail_사용자이메일로북마크찾기_북마크없음() {
//    // given
//    String nonExistentUserEmail = "no_bookmark@example.com";
//
//    // when
//    Optional<Bookmark> foundBookmark = bookmarkRepository.findByUserEmail(nonExistentUserEmail);
//
//    // then
//    assertThat(foundBookmark).isNotPresent();
//  }
//}