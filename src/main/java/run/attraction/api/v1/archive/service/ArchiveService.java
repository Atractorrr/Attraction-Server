package run.attraction.api.v1.archive.service;

import io.micrometer.core.annotation.Counted;
import io.micrometer.core.annotation.Timed;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import run.attraction.api.v1.archive.ReadBox;
import run.attraction.api.v1.archive.dto.ArticleDTO;
import run.attraction.api.v1.archive.dto.request.UserArticlesRequest;
import run.attraction.api.v1.archive.repository.ArticleRepository;
import run.attraction.api.v1.archive.repository.ReadBoxRepository;
import run.attraction.api.v1.introduction.exception.ErrorMessages;
import run.attraction.api.v1.introduction.repository.NewsletterRepository;
import run.attraction.api.v1.introduction.repository.SubscriptionRepository;
import run.attraction.api.v1.introduction.utils.SubscriptionUtil;
import run.attraction.api.v1.rank.ReadBoxEvent;
import run.attraction.api.v1.rank.repository.ReadBoxEventRepository;
import run.attraction.api.v1.statistics.AgeGroup;
import run.attraction.api.v1.statistics.NewsletterEvent;
import run.attraction.api.v1.statistics.repository.NewsletterEventRepository;
import run.attraction.api.v1.user.UserDetail;
import run.attraction.api.v1.user.repository.UserDetailRepository;

@Timed("archive.service")
@Service
@RequiredArgsConstructor
public class ArchiveService {

  private static final int PULL_PERCENTAGE = 100;

  final private ArticleRepository articleRepository;
  final private ReadBoxRepository readBoxRepository;
  final private NewsletterRepository newsletterRepository;
  final private SubscriptionRepository subscriptionRepository;
  private final UserDetailRepository userDetailRepository;
  private final NewsletterEventRepository newsletterEventRepository;
  private final ReadBoxEventRepository readBoxEventRepository;

  private final SubscriptionUtil subscriptionUtil;

  @Counted("archive.service")
  @Transactional(readOnly = true)
  public Page<ArticleDTO> findArticlesByUserId(String userEmail, UserArticlesRequest request) {
    String DESC = "desc";
    String HIDE_READ_TRUE = "true";

    List<String> newsletterEmails = subscriptionUtil.getNewsletterEmailsSubscribedByUser(userEmail,
        subscriptionRepository,
        newsletterRepository);

    if (newsletterEmails.isEmpty()) {
      return new PageImpl<>(Collections.emptyList(), PageRequest.of(request.getPage(), request.getSize()), 0);
    }

    String sortDirection = request.getSort().length > 1 ? request.getSort()[1] : DESC;
    Boolean isHideReadFilter = request.getIsHideRead().equalsIgnoreCase(HIDE_READ_TRUE);
    Sort sortObj = Sort.by(sortDirection.equalsIgnoreCase(DESC) ? Sort.Order.desc(request.getSort()[0])
        : Sort.Order.asc(request.getSort()[0]));
    Pageable pageable = PageRequest.of(request.getPage(), request.getSize(), sortObj);

    return articleRepository.findArticlesByUserEmail(userEmail, newsletterEmails, request.getCategory(),
        isHideReadFilter, request.getQ(), pageable);
  }

  @Transactional(readOnly = true)
  public ArticleDTO findArticleByArticleId(String userEmail, Long articleId) {
    return articleRepository.findArticleByUserEmailAndArticleId(userEmail, articleId)
        .orElseThrow(() -> new IllegalArgumentException(ErrorMessages.NOT_EXIST_ARTICLE.getViewName()));
  }

  @Counted("archive.service")
  @Transactional
  public void saveUserArticleProgress(String userEmail, Long articleId, int readPercentage) {
    ReadBox readBox = readBoxRepository.findByUserEmailAndArticleId(userEmail, articleId)
        .orElse(createReadBox(userEmail, articleId));

    readBox.updateReadPercentagePercentage(readPercentage);
    readBoxRepository.save(readBox);

    if (readPercentage == PULL_PERCENTAGE) {
      saveNewsletterEvent(userEmail, articleId);
      updateReadBoxEvent(userEmail, LocalDate.now());
    }
  }

  private ReadBox createReadBox(String userEmail, Long articleId) {
    return new ReadBox(userEmail, articleId);
  }

  private void saveNewsletterEvent(String userEmail, Long articleId) {
    UserDetail userDetail = userDetailRepository.findById(userEmail)
        .orElseThrow(() -> new NoSuchElementException("존재하지 않은 유저입니다."));
    Long newsletterId = articleRepository.findNewsletterIdByArticleId(articleId);

    newsletterEventRepository.save(NewsletterEvent.builder()
        .newsletterId(newsletterId)
        .occupation(userDetail.getOccupation())
        .ageGroup(AgeGroup.calculateAge(userDetail.getBirthDate()))
        .build());
  }

  private void updateReadBoxEvent(String userEmail, LocalDate date) {
    Optional<ReadBoxEvent> readBoxEventOptional = readBoxEventRepository.findById(userEmail);

    readBoxEventOptional.ifPresentOrElse(
        readBoxEvent -> updateReadBoxEvent(readBoxEvent, date),
        () -> readBoxEventRepository.save(ReadBoxEvent.builder()
            .email(userEmail)
            .consistencyValue(1)
            .createdAt(LocalDateTime.now())
            .modifiedAt(LocalDateTime.now())
            .build())
    );
  }

  private void updateReadBoxEvent(ReadBoxEvent readBoxEvent, LocalDate date) {
    LocalDate modifiedAtDate = readBoxEvent.getModifiedAt().toLocalDate();

    if (isToday(modifiedAtDate, date)) {

    } else if (shouldResetConsistency(modifiedAtDate, date)) {
      readBoxEvent.updateConsistencyValue(1);
    } else if (isPreviousDay(modifiedAtDate, date)) {
      readBoxEvent.updateConsistencyValue(readBoxEvent.getConsistencyValue() + 1);
    }
  }

  private boolean isToday(LocalDate readBoxEventModifiedAt, LocalDate now) {
    return readBoxEventModifiedAt.equals(now);
  }

  private boolean shouldResetConsistency(LocalDate readBoxEventModifiedAt, LocalDate now) {
    return !isSameMonth(readBoxEventModifiedAt, now) || !isPreviousDay(readBoxEventModifiedAt, now);
  }

  private boolean isSameMonth(LocalDate readBoxEventModifiedAt, LocalDate now) {
    return readBoxEventModifiedAt.getYear() == now.getYear() && readBoxEventModifiedAt.getMonth() == now.getMonth();
  }

  private boolean isPreviousDay(LocalDate readBoxEventModifiedAt, LocalDate now) {
    return readBoxEventModifiedAt.plusDays(1).equals(now);
  }
}
