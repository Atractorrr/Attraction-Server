package run.attraction.api.v1.home.service.dto.search;

import lombok.Getter;
import run.attraction.api.v1.archive.dto.NewsletterDTO;

import java.time.LocalDate;

@Getter
public class ArticleSearchDto {

    private static final String BASE_URL = "https://attraction-bucket.s3.ap-northeast-2.amazonaws.com/";

    private Long id;
    private String title;
    private String thumbnailUrl;
    private String contentUrl;
    private String contentSummary;
    private int readingTime;
    private LocalDate receivedAt;
    private String newsletterName;
    private NewsletterDTO newsletterDTO;

    public ArticleSearchDto(Long id, String title, String thumbnailUrl, String contentUrl, String contentSummary, int readingTime, LocalDate receivedAt, String newsletterName, NewsletterDTO newsletterDTO) {
        this.id = id;
        this.title = title;
        this.thumbnailUrl = buildUrl("thumbnail/", thumbnailUrl);
        this.contentUrl = buildUrl("article/", contentUrl);
        this.contentSummary = contentSummary;
        this.readingTime = readingTime;
        this.receivedAt = receivedAt;
        this.newsletterName = newsletterName;
        this.newsletterDTO = newsletterDTO;
    }

    private static String buildUrl(String type, String path) {
        return BASE_URL + type + path;
    }
}
