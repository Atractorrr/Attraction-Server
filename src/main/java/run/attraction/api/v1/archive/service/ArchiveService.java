package run.attraction.api.v1.archive.service;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
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


@Service
@RequiredArgsConstructor
public class ArchiveService {

  final private ArticleRepository articleRepository;
  final private ReadBoxRepository readBoxRepository;

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
}
