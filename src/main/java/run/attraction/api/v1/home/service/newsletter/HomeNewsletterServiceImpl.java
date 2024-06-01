package run.attraction.api.v1.home.service.newsletter;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import run.attraction.api.v1.archive.repository.SubscribeRepository;
import run.attraction.api.v1.home.service.dto.newsletter.NewsletterDetailDto;
import run.attraction.api.v1.introduction.Category;
import run.attraction.api.v1.introduction.Newsletter;
import run.attraction.api.v1.introduction.repository.NewsletterRepository;
import run.attraction.api.v1.user.Interest;
import run.attraction.api.v1.user.UserDetail;
import run.attraction.api.v1.user.repository.UserDetailRepository;
import run.attraction.api.v1.user.repository.UserRepository;

@Component
@RequiredArgsConstructor
public class HomeNewsletterServiceImpl implements  HomeNewsletterService {

  private static final Logger log = LoggerFactory.getLogger(HomeNewsletterServiceImpl.class);
  private final UserRepository userRepository;
  private final NewsletterRepository newsletterRepository;
  private final SubscribeRepository subscribeRepository;
  private final UserDetailRepository userDetailRepository;

  public List<String> getDefaultCategories() {
    return Stream.concat(
        Stream.of("RECOMMEND"),
        Stream.of(Category.values()).map(Enum::name)
    ).toList();
  }

  public boolean hasUserDetail(String email){
    return userDetailRepository.existsById(email);
  }

  public List<String> getUserCategories(String email) {
    UserDetail userDetail = userDetailRepository.findById(email)
        .orElseThrow(() -> new NoSuchElementException("추가 정보를 받지 않은 유저 입니다."));
    final Set<Interest> interests = userDetail.getInterests();
    return Stream.concat(Stream.concat(Stream.of("RECOMMEND"), interests.stream().map(Interest::name)),
        Stream.of(Category.values())
            .map(Category::name)
            .filter(categoryName -> interests.stream().map(Interest::name)
                .noneMatch(categoryName::equals))
    ).toList();
  }

  public List<NewsletterDetailDto> getMostNewsletterByCategory(String category, int size) {
    log.info("카테고리로 뉴스레터 검색");
    List<Object[]> newslettersByCategory = newsletterRepository.findByCategory(Category.valueOf(category));
    Map<Long, Newsletter> categoryNewsletterMap = getNewsletterMap(newslettersByCategory);
    log.info("가장 많이 구독한 뉴스레터 id 조회");
    List<Long> mostSubscribedNewsletterIds = subscribeRepository.findMostSubscribedNewsletterIds();

    // 추가해야되는 상황이 발생할 수도 있어서 .collect(Collectors.toList()) 으로 생성
    List<NewsletterDetailDto> trendyNewsletters = mostSubscribedNewsletterIds.stream()
        .filter(categoryNewsletterMap::containsKey)
        .limit(size)
        .map(categoryNewsletterMap::get)
        .map(this::getNewsletterDetailDto)
        .collect(Collectors.toList());

    if (trendyNewsletters.size()<size){
      log.info("조회 결과가 {}보다 적어 추가로 뉴스레터를 넣습니다.",size);
      addExtraTrendyNewsletters(size, categoryNewsletterMap, trendyNewsletters);
    }
    return trendyNewsletters;
  }

  private void addExtraTrendyNewsletters(int size,
                                         Map<Long, Newsletter> categoryNewsletterMap,
                                         List<NewsletterDetailDto> trendyNewsletters) {
    List<Newsletter> extraNewsletters = categoryNewsletterMap.values().stream()
        .filter(newsletter -> !trendyNewsletters.stream()
                                                .map(NewsletterDetailDto::id).toList()
                                                .contains(newsletter.getId()))
        .limit(size - trendyNewsletters.size())
        .toList();

    trendyNewsletters.addAll(extraNewsletters.stream()
                                              .map(this::getNewsletterDetailDto)
                                              .toList());
  }

  private static Map<Long, Newsletter> getNewsletterMap(List<Object[]> newsletters) {
    return newsletters.stream()
        .collect(Collectors.toMap(
            newsletter -> (Long) newsletter[0],
            newsletter -> (Newsletter) newsletter[1]
        ));
  }

  private NewsletterDetailDto getNewsletterDetailDto(Newsletter newsletter){
    return new NewsletterDetailDto(
        newsletter.getId(),
        newsletter.getThumbnailUrl(),
        newsletter.getName(),
        newsletter.getDescription());
  }

  public List<NewsletterDetailDto> getMostNewsletter(int size) {
    log.info("가장 많이 구독한 뉴스레터 id 조회");
    final List<Long> mostSubscribedNewsletterIds = subscribeRepository.findMostSubscribedNewsletterIds();

    List<Newsletter> mostNewsletters;
    log.info("id로 뉴스레터 조회");
    if (mostSubscribedNewsletterIds.size()>=size){
      mostNewsletters = newsletterRepository.findAllById(mostSubscribedNewsletterIds.subList(0, size));
    }else{
      final List<Newsletter> newsletters = newsletterRepository.findAllById(mostSubscribedNewsletterIds);
      log.info("가장 많이 구독한 구독한 뉴스레터 조회 결과가 {}보다 적어 랜덤으로 추가합니다.",size);
      final List<Newsletter> newsletterRandom = newsletterRepository.findNewsletterRandom(mostSubscribedNewsletterIds, size - mostSubscribedNewsletterIds.size());
      mostNewsletters = Stream.concat(newsletters.stream(), newsletterRandom.stream()).toList();
    }

    return mostNewsletters.stream()
        .map(this::getNewsletterDetailDto)
        .toList();
  }
}
