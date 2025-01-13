package run.attraction.api.v1.announcement.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import run.attraction.api.v1.bookmark.repository.BookmarkRepository;
import run.attraction.api.v1.bookmark.service.BookmarkService;

@SpringBootTest
@ActiveProfiles("test")
class AnnouncementServiceTest {

    @Autowired
    private BookmarkService bookmarkService;

    @Autowired
    private BookmarkRepository bookmarkRepository;

    @Test
    void createPost() {

    }
}
