package run.attraction.api.v1.archive.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserArticlesRequest {
  private int page = 0;
  private int size = 20;
  private String[] sort = {"receivedAt", "asc"};
  private String isHideRead = "false";
  private String category;
  private String q; // search query
}