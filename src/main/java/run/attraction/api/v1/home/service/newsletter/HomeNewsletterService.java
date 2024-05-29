package run.attraction.api.v1.home.service.newsletter;

import java.util.List;

public interface HomeNewsletterService {
  List<String> getDefaultCategories();
  List<String> getUserCategories(String email);
}
