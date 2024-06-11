package run.attraction.api.v1.mypage.service.archive;

public class MypageArticleServiceTest {
//  @Mock
//  private ArticleRepository articleRepository;
//
//  @Mock
//  private NewsletterRepository newsletterRepository;
//
//  @Mock
//  private ReadBoxRepository readBoxRepository;
//
//  @InjectMocks
//  private MypageArticleServiceImpl mypageArticleService;
//
//  @BeforeEach
//  void setUp() {
//    MockitoAnnotations.initMocks(this);
//    ReadBox readBox1 = ReadBox.builder()
//        .id(1L)
//        .articleId(1L)
//        .userEmail("test@gmail.com")
//        .readPercentage(100)
//        .readDate(LocalDate.now())
//        .build();
//
//
//    ReadBox readBox2 = ReadBox.builder()
//        .id(2L)
//        .articleId(2L)
//        .userEmail("test@gmail.com")
//        .readPercentage(70)
//        .readDate(LocalDate.now())
//        .build();
//
//    ReadBox readBox3 = ReadBox.builder()
//        .id(3L)
//        .articleId(3L)
//        .userEmail("test@gmail.com")
//        .readPercentage(60)
//        .readDate(LocalDate.now())
//        .build();
//
//    when(readBoxRepository.findByUserEmail("test@gmail.com"))
//        .thenReturn(List.of(readBox1,readBox2,readBox3));
//
//    LocalDateTime now = LocalDateTime.now();
//    when(readBox1.getModifiedAt()).thenReturn(now);
//    when(readBox2.getModifiedAt()).thenReturn(now);
//    when(readBox3.getModifiedAt()).thenReturn(now);
//
//    Article article1 = Article.builder()
//        .id(1L)
//        .newsletterEmail("newsletter1@gmail.com")
//        .userEmail("test@gmail.com")
//        .title("아티클1")
//        .thumbnailUrl("썸네일1")
//        .contentUrl("url1")
//        .readingTime(6)
//        .contentSummary("요약1")
//        .newsletterNickname("뉴닉")
//        .receivedAt(LocalDate.now().minusDays(3))
//        .build();
//
//    Article article2 = Article.builder()
//        .id(2L)
//        .newsletterEmail("newsletter1@gmail.com")
//        .userEmail("test@gmail.com")
//        .title("아티클2")
//        .thumbnailUrl("썸네일2")
//        .contentUrl("url2")
//        .readingTime(7)
//        .contentSummary("요약2")
//        .newsletterNickname("뉴닉")
//        .receivedAt(LocalDate.now().minusDays(2))
//        .build();
//
//    Article article3 = Article.builder()
//        .id(3L)
//        .newsletterEmail("newsletter2@gmail.com")
//        .userEmail("test@gmail.com")
//        .title("아티클3")
//        .thumbnailUrl("썸네일3")
//        .contentUrl("url3")
//        .readingTime(8)
//        .contentSummary("요약3")
//        .newsletterNickname("까탈로그")
//        .receivedAt(LocalDate.now().minusDays(1))
//        .build();
//
//    when(articleRepository.findArticleByIdAndUserEmail(List.of(1L, 2L, 3L),"test@gmail.com",LocalDate.now().minusDays(6),LocalDate.now()))
//        .thenReturn(List.of(article1,article2,article3));
//
//    Newsletter newsletter1 = Newsletter.builder()
//        .id(1L)
//        .email("newsletter1@gmail.com")
//        .name("뉴닉")
//        .description("뉴닉 묘사")
//        .uploadDays("월요일")
//        .category(Category.CULTURE_ART)
//        .mainLink("링크1")
//        .subscribeLink("구독 주소 1")
//        .thumbnailUrl("썸네일 Url 1")
//        .nickname("뉴닉")
//        .build();
//
//    Newsletter newsletter2 = Newsletter.builder()
//        .id(2L)
//        .email("newsletter2@gmail.com")
//        .name("까탈로그")
//        .description("까탈로그 묘사")
//        .uploadDays("화요일")
//        .category(Category.CURRENT_AFFAIRS_SOCIETY)
//        .mainLink("링크2")
//        .subscribeLink("구독 주소 2")
//        .thumbnailUrl("썸네일 Url 2")
//        .nickname("까탈로그")
//        .build();
//
//    when(newsletterRepository.findNewslettersByNewsletterEmails(List.of("newsletter@gmail.com","newsletter2@gmail.com")))
//        .thenReturn(List.of(
//        new Object[]{"newsletter@gmail.com", newsletter1},
//        new Object[]{"newsletter2@gmail.com", newsletter2}
//    ));
//
//  }
//
//  @Test
//  @DisplayName("마이페이지 최근 읽은 아티클 API 테스트")
//  void getUserCalendar(){
//    //Given
//    String email = "test@gmail.com";
//
//    //When
//    final List<MypageArticle> mypageArticles = mypageArticleService.getUserRecentArticles(email);
//
//    //Then
//    assertEquals(mypageArticles.size(), 2);
//  }
}
