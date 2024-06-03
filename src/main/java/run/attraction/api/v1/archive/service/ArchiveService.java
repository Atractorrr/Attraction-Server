package run.attraction.api.v1.archive.service;

import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
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

@Service
@RequiredArgsConstructor
public class ArchiveService {

  final private ArticleRepository articleRepository;
  final private ReadBoxRepository readBoxRepository;
  final private NewsletterRepository newsletterRepository;
  final private SubscribeRepository subscribeRepository;
  final private UserSubscribedNewsletterCategoryRepository userSubscribedNewsletterCategoryRepository;

  @Transactional(readOnly = true)
  public Page<ArticleDTO> findArticlesByUserId(String userEmail, UserArticlesRequest request) {
    String ASC = "asc";
    String HIDE_READ_TRUE = "true";

    String sortDirection = request.getSort().length > 1 ? request.getSort()[1] : ASC;
    Boolean isHideReadFilter = request.getIsHideRead().equalsIgnoreCase(HIDE_READ_TRUE);
    Sort sortObj = Sort.by(sortDirection.equalsIgnoreCase(ASC) ? Sort.Order.asc(request.getSort()[0]) : Sort.Order.desc(request.getSort()[0]));
    Pageable pageable = PageRequest.of(request.getPage(), request.getSize(), sortObj);

    return articleRepository.findArticlesByUserEmail(userEmail, request.getCategory(), isHideReadFilter, request.getQ(), pageable);
  }

  @Transactional(readOnly = true)
  public ArticleDTO findArticleByArticleId(String userEmail, Long articleId) {
    return articleRepository.findArticleByUserEmailAndArticleId(userEmail, articleId)
        .orElseThrow(() -> new IllegalArgumentException(ErrorMessages.NOT_EXIST_ARTICLE.getViewName()));
  }

  @Transactional
  public void saveUserArticleProgress(String userEmail, Long articleId, int percentage) {
    ReadBox readBox = readBoxRepository.findByUserEmailAndArticleId(userEmail, articleId)
        .orElse(createReadBox(userEmail, articleId));

    readBox.updatePercentage(percentage);
    readBoxRepository.save(readBox);
  }

  private ReadBox createReadBox(String userEmail, Long articleId) {
    return new ReadBox(userEmail, articleId);
  }

  @Transactional
  public NewsletterEmail addNewsletter(String userEmail, Long newsletterId) {
    Newsletter newsletter = newsletterRepository.findById(newsletterId)
        .orElseThrow(() -> new IllegalArgumentException(ErrorMessages.NOT_EXIST_NEWSLETTER.getViewName()));
    Subscribe subscribe = subscribeRepository.findByUserEmail(userEmail)
        .orElse(createSubscribe(userEmail));

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
