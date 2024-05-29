package run.attraction.api.v1.home.service.newsletter;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import run.attraction.api.v1.introduction.Category;
import run.attraction.api.v1.user.Interest;
import run.attraction.api.v1.user.User;
import run.attraction.api.v1.user.repository.UserRepository;

@Component
@RequiredArgsConstructor
public class HomeNewsletterServiceImpl implements  HomeNewsletterService {

  private final UserRepository userRepository;

  public List<String> getDefaultCategories() {
    return Stream.concat(
        Stream.of("RECOMMAND"),
        Stream.of(Category.values()).map(Enum::name)
    ).toList();
  }

  public List<String> getUserCategories(String email) {
    final User user = userRepository.findById(email).orElseThrow(() -> new NoSuchElementException("존재하지 않은 유저입니다."));
    final Set<Interest> interests = user.getInterests();
    return Stream.concat(Stream.concat(Stream.of("RECOMMAND"), interests.stream().map(Interest::name)),
        Stream.of(Category.values())
            .map(Category::name)
            .filter(categoryName -> interests.stream().map(Interest::name)
                .noneMatch(categoryName::equals))
    ).toList();
  }
}
