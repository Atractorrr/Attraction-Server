package run.attraction.api.v1.bookmark.service;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import run.attraction.api.v1.archive.dto.ArticleDTO;
import run.attraction.api.v1.archive.repository.ArticleRepository;
import run.attraction.api.v1.bookmark.Bookmark;
import run.attraction.api.v1.bookmark.dto.BookmarkArticleRequest;
import run.attraction.api.v1.bookmark.repository.BookmarkRepository;
import run.attraction.api.v1.introduction.exception.ErrorMessages;

@Service
@RequiredArgsConstructor
public class BookmarkService {

  final private BookmarkRepository bookmarkRepository;
  final private ArticleRepository articleRepository;

  @Transactional(readOnly = true)
  public Page<ArticleDTO> getUserBookmarkArticle(String userEmail, BookmarkArticleRequest request) {
    String DESC = "desc";

    List<Long> bookmarkArticleIds = bookmarkRepository.findBookmarkArticleIdsByUserEmail(userEmail)
        .orElseThrow(() -> new IllegalArgumentException(ErrorMessages.NOT_EXIST_BOOKMARK.getViewName()));
    String sortDirection = request.getSort().length > 1 ? request.getSort()[1] : DESC;
    Sort sortObj = Sort.by(sortDirection.equalsIgnoreCase(DESC) ? Sort.Order.desc(request.getSort()[0]) : Sort.Order.asc(request.getSort()[0]));
    Pageable pageable = PageRequest.of(request.getPage(), request.getSize(), sortObj);

    return articleRepository.findArticlesByArticleIds(bookmarkArticleIds, request, pageable);
  }

  @Transactional
  public void addArticle(String userEmail, Long articleId) {
    Bookmark bookmark = bookmarkRepository.findByUserEmail(userEmail)
        .orElse(createBookmark(userEmail));

    if(hasArticleId(bookmark, articleId)) {
      throw new IllegalArgumentException("이미 북마크에 추가된 아티클입니다");
    }

    bookmark.getArticleIds().add(articleId);
    bookmarkRepository.save(bookmark);
  }

  private boolean hasArticleId(Bookmark bookmark, Long articleId) {
    return bookmark.getArticleIds().contains(articleId);
  }

  private Bookmark createBookmark(String userEmail) {
    return new Bookmark(userEmail);
  }

  @Transactional
  public Bookmark deleteArticle(String userEmail, Long articleId) {
    Bookmark bookmark = bookmarkRepository.findByUserEmail(userEmail)
        .orElseThrow(() -> new IllegalArgumentException(ErrorMessages.NOT_EXIST_BOOKMARK.getViewName()));

    if(hasArticleId(bookmark, articleId)) {
      bookmark.getArticleIds().remove(articleId);
      return bookmarkRepository.save(bookmark);
    }

    throw new IllegalArgumentException("북마크에 존재하지 않는 아티클입니다");
  }

  @Transactional(readOnly = true)
  public boolean isArticleBookmarked(String userEmail, Long articleId) {
    Optional<Bookmark> bookmark = bookmarkRepository.findByUserEmail(userEmail);

    return bookmark.map(value -> value.getArticleIds().contains(articleId)).orElse(false);
  }
}
