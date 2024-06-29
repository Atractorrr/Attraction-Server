package run.attraction.api.v1.home.service.dto.search;

import lombok.Getter;
import run.attraction.api.v1.archive.dto.NewsletterDTO;

import java.time.LocalDate;

@Getter
public class ArticleSearchDto {

    private final Long id;
    private final String title;
    private final String thumbnailUrl;
    private final String contentSummary;
    private final int readingTime;
    private final LocalDate receivedAt;
    private final NewsletterDTO newsletter;

    public ArticleSearchDto(Long id, String title, String thumbnailUrl, String contentSummary, int readingTime, LocalDate receivedAt, NewsletterDTO newsletterDTO) {
        this.id = id;
        this.title = title;
        this.thumbnailUrl = buildUrl("/thumbnail/", thumbnailUrl);
        this.contentSummary = contentSummary;
        this.readingTime = readingTime;
        this.receivedAt = receivedAt;
        this.newsletter = newsletterDTO;
    }

    private static String buildUrl(String type, String path) {
        return  type + path;
    }
}
