package run.attraction.api.v1.archive.service;

import java.util.Collections;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import run.attraction.api.v1.archive.ReadBox;
import run.attraction.api.v1.archive.Subscribe;
import run.attraction.api.v1.archive.dto.ArticleDTO;
import run.attraction.api.v1.archive.dto.NewsletterEmail;
import run.attraction.api.v1.archive.dto.request.UserArticlesRequest;
import run.attraction.api.v1.archive.repository.ArticleRepository;
import run.attraction.api.v1.archive.repository.ReadBoxRepository;
import run.attraction.api.v1.archive.repository.SubscribeRepository;
import run.attraction.api.v1.introduction.Category;
import run.attraction.api.v1.introduction.Newsletter;
import run.attraction.api.v1.introduction.UserSubscribedNewsletterCategory;
import run.attraction.api.v1.introduction.exception.ErrorMessages;
import run.attraction.api.v1.introduction.repository.NewsletterRepository;
import run.attraction.api.v1.introduction.repository.UserSubscribedNewsletterCategoryRepository;
import run.attraction.api.v1.rank.ReadBoxEvent;
import run.attraction.api.v1.rank.repository.ReadBoxEventRepository;
import run.attraction.api.v1.statistics.AgeGroup;
import run.attraction.api.v1.statistics.NewsletterEvent;
import run.attraction.api.v1.statistics.repository.NewsletterEventRepository;
import run.attraction.api.v1.user.UserDetail;
import run.attraction.api.v1.user.repository.UserDetailRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ArchiveService {

  private static final int PULL_PERCENTAGE = 100;

  final private ArticleRepository articleRepository;
  final private ReadBoxRepository readBoxRepository;
  final private NewsletterRepository newsletterRepository;
  final private SubscribeRepository subscribeRepository;
  final private UserSubscribedNewsletterCategoryRepository userSubscribedNewsletterCategoryRepository;
  private final UserDetailRepository userDetailRepository;
  private final NewsletterEventRepository newsletterEventRepository;
  private final ReadBoxEventRepository readBoxEventRepository;

  @Transactional(readOnly = true)
  public Page<ArticleDTO> findArticlesByUserId(String userEmail, UserArticlesRequest request) {
    String DESC = "desc";
    String HIDE_READ_TRUE = "true";

    Subscribe subscribe = subscribeRepository.findByUserEmail(userEmail)
        .orElse(null);

    if(subscribe == null) {
      return new PageImpl<>(Collections.emptyList(), PageRequest.of(request.getPage(), request.getSize()), 0);
    }

    List<String> newsletterEmails = subscribe.getNewsletterIds()
        .stream()
        .map(id -> newsletterRepository.findById(id).get().getEmail())
        .toList();

    String sortDirection = request.getSort().length > 1 ? request.getSort()[1] : DESC;
    Boolean isHideReadFilter = request.getIsHideRead().equalsIgnoreCase(HIDE_READ_TRUE);
    Sort sortObj = Sort.by(sortDirection.equalsIgnoreCase(DESC) ? Sort.Order.desc(request.getSort()[0]) : Sort.Order.asc(request.getSort()[0]));
    Pageable pageable = PageRequest.of(request.getPage(), request.getSize(), sortObj);

    return articleRepository.findArticlesByUserEmail(userEmail, newsletterEmails, request.getCategory(), isHideReadFilter, request.getQ(), pageable);
  }

  @Transactional(readOnly = true)
  public ArticleDTO findArticleByArticleId(String userEmail, Long articleId) {
    return articleRepository.findArticleByUserEmailAndArticleId(userEmail, articleId)
        .orElseThrow(() -> new IllegalArgumentException(ErrorMessages.NOT_EXIST_ARTICLE.getViewName()));
  }

  @Transactional
  public void saveUserArticleProgress(String userEmail, Long articleId, int readPercentage) {
    ReadBox readBox = readBoxRepository.findByUserEmailAndArticleId(userEmail, articleId)
        .orElse(createReadBox(userEmail, articleId));

    readBox.updateReadPercentagePercentage(readPercentage);
    readBoxRepository.save(readBox);

    if(readPercentage==PULL_PERCENTAGE){
      log.info("NewsletterEvent 저장 시작 ");
      saveNewsletterEvent(userEmail, articleId);
      log.info("ReadBoxEvent 저장 시작 ");
      updateReadBoxEvent(userEmail,LocalDate.now());
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

  private void updateReadBoxEvent(String userEmail, LocalDate date){
    Optional<ReadBoxEvent> readBoxEventOptional = readBoxEventRepository.findById(userEmail);

    readBoxEventOptional.ifPresentOrElse(
            readBoxEvent -> updateReadBoxEvent(readBoxEvent,date),
            () -> readBoxEventRepository.save(ReadBoxEvent.builder()
                                                          .email(userEmail)
                                                          .consistencyValue(1)
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

  @Transactional
  public NewsletterEmail addNewsletter(String userEmail, Long newsletterId) {
    Newsletter newsletter = newsletterRepository.findById(newsletterId)
        .orElseThrow(() -> new IllegalArgumentException(ErrorMessages.NOT_EXIST_NEWSLETTER.getViewName()));
    Subscribe subscribe = subscribeRepository.findByUserEmail(userEmail)
        .orElse(createSubscribe(userEmail));

    if(subscribe.getNewsletterIds().contains(newsletterId)) {
      throw new IllegalArgumentException(ErrorMessages.ALREADY_EXIST_NEWSLETTER.getViewName());
    }

    saveUserSubscribedNewsletterCategory(userEmail, newsletter.getCategory());
    subscribe.saveNewsletterId(newsletter.getId());
    subscribeRepository.save(subscribe);

    return new NewsletterEmail(newsletter.getEmail());
  }

  private Subscribe createSubscribe(String userEmail) {
    return Subscribe.builder()
        .userEmail(userEmail)
        .newsletterIds(new ArrayList<>())
        .build();
  }

  private void saveUserSubscribedNewsletterCategory(String userEmail, Category category) {
    UserSubscribedNewsletterCategory userSubscribedNewsletterCategory = userSubscribedNewsletterCategoryRepository.findByUserEmail(
        userEmail).orElseGet(() -> {
      var newCategory = createUserSubscribedNewsletterCategory(userEmail, category);
      userSubscribedNewsletterCategoryRepository.save(newCategory);
      return newCategory;
    });

    if(hasCategory(userSubscribedNewsletterCategory,category)) {
      return;
    }

    userSubscribedNewsletterCategory.getCategories().add(category);
    userSubscribedNewsletterCategoryRepository.save(userSubscribedNewsletterCategory);
  }

  private UserSubscribedNewsletterCategory createUserSubscribedNewsletterCategory(String userEmail, Category category) {
    return UserSubscribedNewsletterCategory.builder()
        .userEmail(userEmail)
        .categories(new ArrayList<>(List.of(category)))
        .build();
  }

  private boolean hasCategory(UserSubscribedNewsletterCategory userSubscribedNewsletterCategory, Category category) {
    return userSubscribedNewsletterCategory.getCategories().contains(category);
  }


  @Transactional
  public List<Newsletter> getSubscribedNewslettersByUser(String userEmail) {
    List<Long> newsletterIds = subscribeRepository.findNewsletterIdsByUserEmail(userEmail);

    return newsletterRepository.findNewslettersByNewsletterIds(newsletterIds);
  }

  @Transactional(readOnly = true)
  public List<?> getUserSubscribedNewsletterCategories(String userEmail) {
    return userSubscribedNewsletterCategoryRepository.findByUserEmail(userEmail)
        .map(UserSubscribedNewsletterCategory::getCategories)
        .orElseGet(ArrayList::new);
  }

}
