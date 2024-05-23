package run.attraction.api.v1.archive.service;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import run.attraction.api.v1.archive.dto.ArticleDTO;
import run.attraction.api.v1.archive.dto.request.UserArticlesRequest;
import run.attraction.api.v1.archive.repository.ArticleRepository;


@Service
@RequiredArgsConstructor
public class ArchiveService {

  final private ArticleRepository articleRepository;

  @Transactional(readOnly = true)
  public Page<ArticleDTO> findArticlesByUserId(Long userId, UserArticlesRequest request) {

    String ASC = "asc";
    String HIDE_READ_TRUE = "true";

    String sortDirection = request.getSort().length > 1 ? request.getSort()[1] : ASC;
    Boolean isHideReadFilter = request.getIsHideRead().equalsIgnoreCase(HIDE_READ_TRUE);
    Sort sortObj = Sort.by(sortDirection.equalsIgnoreCase(ASC) ? Sort.Order.asc(request.getSort()[0]) : Sort.Order.desc(request.getSort()[0]));
    Pageable pageable = PageRequest.of(request.getPage(), request.getSize(), sortObj);

    return articleRepository.findArticlesByUserId(userId, request.getCategory(), isHideReadFilter, request.getQ(), pageable);
  }
}