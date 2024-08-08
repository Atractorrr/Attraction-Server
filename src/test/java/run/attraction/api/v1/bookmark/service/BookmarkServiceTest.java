// package run.attraction.api.v1.bookmark.service;

// import static org.assertj.core.api.Assertions.assertThat;
// import static org.assertj.core.api.Assertions.assertThatThrownBy;

// import java.util.Optional;
// import org.junit.jupiter.api.AfterEach;
// import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.data.domain.Page;
// import org.springframework.data.domain.Sort;
// import org.springframework.test.context.ActiveProfiles;
// import org.springframework.test.context.jdbc.Sql;
// import org.springframework.transaction.annotation.Transactional;
// import run.attraction.api.v1.archive.dto.ArticleDTO;
// import run.attraction.api.v1.bookmark.Bookmark;
// import run.attraction.api.v1.bookmark.dto.BookmarkArticleRequest;
// import run.attraction.api.v1.bookmark.repository.BookmarkRepository;
// import run.attraction.api.v1.introduction.exception.ErrorMessages;

// @SpringBootTest
// @Sql("/sql/bookmark/bookmark-service-test-data.sql")
// @ActiveProfiles("test")
// class BookmarkServiceTest {

//   @Autowired
//   private BookmarkService bookmarkService;

//   @Autowired
//   private BookmarkRepository bookmarkRepository;

//   @AfterEach
//   void setUp() {
//     bookmarkRepository.deleteAll(); // 데이터베이스 초기화
//   }

//   @Test
//   void isArticleBookmarked_북마크가_없으면_false_반환() {
//     //given
//     String userEmail = "no_bookmark@example.com";
//     Long articleId = 1L;

//     //when
//     boolean isBookmarked = bookmarkService.isArticleBookmarked(userEmail, articleId);

//     //then
//     assertThat(isBookmarked).isFalse();
//   }

//   @Test
//   void isArticleBookmarked_북마크가_있고_articleId가_담겨있으면_true_반환() {
//     //given
//     String userEmail = "user3@gmail.com";
//     Long articleId = 13L;

//     //when
//     boolean isBookmarked = bookmarkService.isArticleBookmarked(userEmail, articleId);

//     //then
//     assertThat(isBookmarked).isTrue();
//   }

//   @Test
//   void isArticleBookmarked_북마크가_있고_articleId가_담겨있지_않으면_false_반환() {
//     //given
//     String userEmail = "user3@gmail.com";
//     Long articleId = 1L;

//     //when
//     boolean isBookmarked = bookmarkService.isArticleBookmarked(userEmail, articleId);

//     //then
//     assertThat(isBookmarked).isFalse();
//   }

//   @Test
//   @Transactional
//   void addArticle_새로운_북마크_생성() {
//     // given
//     String userEmail = "new_user@example.com";
//     Long articleId = 20L;

//     // when
//     bookmarkService.addArticle(userEmail, articleId);

//     // then
//     Optional<Bookmark> bookmark = bookmarkRepository.findByUserEmail(userEmail);
//     assertThat(bookmark).isPresent();
//     assertThat(bookmark.get().getArticleIds()).contains(articleId);
//   }

//   @Test
//   @Transactional
//   void addArticle_기존_북마크에_아티클_추가() {
//     // given
//     String userEmail = "user3@gmail.com"; // 이미 데이터베이스에 존재하는 사용자
//     Long articleId = 16L;

//     // when
//     bookmarkService.addArticle(userEmail, articleId);

//     // then
//     Optional<Bookmark> bookmark = bookmarkRepository.findByUserEmail(userEmail);
//     assertThat(bookmark).isPresent();
//     assertThat(bookmark.get().getArticleIds()).contains(articleId);
//   }

//   @Test
//   @Transactional
//   void addArticle_이미_존재하는_아티클_추가_안함() {
//     // given
//     String userEmail = "user3@gmail.com"; // 이미 데이터베이스에 존재하는 사용자
//     Long articleId = 13L; // 이미 북마크에 존재하는 아티클 ID

//     // when & then
//     assertThatThrownBy(() -> bookmarkService.addArticle(userEmail, articleId))
//         .isInstanceOf(IllegalArgumentException.class)
//         .hasMessage("이미 북마크에 추가된 아티클입니다");

//     // 북마크가 여전히 존재하는지 확인
//     Optional<Bookmark> bookmark = bookmarkRepository.findByUserEmail(userEmail);
//     assertThat(bookmark).isPresent();
//     // articleId가 북마크에 여전히 한 번만 존재하는지 확인
//     assertThat(bookmark.get().getArticleIds()).containsOnlyOnce(articleId);
//   }

//   @Test
//   @Transactional
//   void deleteArticle_기존_아티클_삭제() {
//     // given
//     String userEmail = "user3@gmail.com"; // 이미 데이터베이스에 존재하는 사용자
//     Long articleId = 13L; // 삭제할 아티클 ID

//     // when
//     bookmarkService.deleteArticle(userEmail, articleId);

//     // then
//     Optional<Bookmark> bookmark = bookmarkRepository.findByUserEmail(userEmail);
//     assertThat(bookmark).isPresent();
//     assertThat(bookmark.get().getArticleIds()).doesNotContain(articleId);
//   }

//   @Test
//   @Transactional
//   void deleteArticle_존재하지_않는_아티클_삭제_안함() {
//     // given
//     String userEmail = "user3@gmail.com"; // 이미 데이터베이스에 존재하는 사용자
//     Long articleId = 99L; // 존재하지 않는 아티클 ID

//     // when & then
//     assertThatThrownBy(() -> bookmarkService.deleteArticle(userEmail, articleId))
//         .isInstanceOf(IllegalArgumentException.class)
//         .hasMessage("북마크에 존재하지 않는 아티클입니다");

//     // 북마크가 여전히 존재하는지 확인
//     Optional<Bookmark> bookmark = bookmarkRepository.findByUserEmail(userEmail);
//     assertThat(bookmark).isPresent();
//     // articleId가 북마크에 없는지 확인
//     assertThat(bookmark.get().getArticleIds()).doesNotContain(articleId);
//   }

//   @Test
//   @Transactional
//   void deleteArticle_존재하지_않는_북마크_예외_발생() {
//     // given
//     String userEmail = "nonexistent_user@example.com"; // 존재하지 않는 사용자
//     Long articleId = 13L; // 삭제할 아티클 ID

//     // when & then
//     assertThatThrownBy(() -> bookmarkService.deleteArticle(userEmail, articleId))
//         .isInstanceOf(IllegalArgumentException.class)
//         .hasMessage(ErrorMessages.NOT_EXIST_BOOKMARK.getViewName());
//   }

//  @Test
//  @Transactional
//  void getUserBookmarkArticle_북마크된_기사_조회_성공() {
//    // given
//    String userEmail = "user3@gmail.com";
//    BookmarkArticleRequest request = new BookmarkArticleRequest();
//    request.setPage(0);
//    request.setSize(10);
//    request.setSort(new String[]{"receivedAt", "desc"});
//    request.setCategory("ECONOMY");
//
//    // when
//    Page<ArticleDTO> articles = bookmarkService.getUserBookmarkArticle(userEmail, request);
//
//    // then
//    assertThat(articles).isNotEmpty();
//    assertThat(articles.getContent()).isNotEmpty();
//    assertThat(articles.getContent().size()).isLessThanOrEqualTo(request.getSize());
//    assertThat(articles.getSort().getOrderFor("receivedAt").getDirection()).isEqualTo(Sort.Direction.DESC);
//  }

//  @Test
//  @Transactional
//  void getUserBookmarkArticle_파라미터를_작성하지_않아도__기본값으로_동작() {
//    // given
//    String userEmail = "user3@gmail.com";
//    BookmarkArticleRequest request = new BookmarkArticleRequest();
//    int size = 3; // 현재 테스트 db에서 해당 유저가 북마크 하고 있는 아티클은 3개
//
//    // when
//    Page<ArticleDTO> articles = bookmarkService.getUserBookmarkArticle(userEmail, request);
//
//    // then
//    assertThat(articles).isNotEmpty();
//    assertThat(articles.getContent()).isNotEmpty();
//    assertThat(articles.getContent().size()).isLessThanOrEqualTo(request.getSize());
//    assertThat(articles.getContent().size()).isEqualTo(size);
//    assertThat(articles.getSort().getOrderFor("receivedAt").getDirection()).isEqualTo(Sort.Direction.DESC);
//  }
}
