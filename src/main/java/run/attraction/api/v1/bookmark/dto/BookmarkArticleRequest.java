package run.attraction.api.v1.bookmark.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BookmarkArticleRequest {
  private int page = 0;
  private int size = 20;
  private String[] sort = {"receivedAt", "desc"};
  private String category;
  private String q; // search query
}