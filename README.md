# Attraction-Server
<p align="center">
  <img src="https://github.com/Atractorrr/Attraction-Server/assets/110734817/79695279-bc00-4be4-b119-2c7ef4acad47" width=800 height=500>
</p>
<br>


## Attraction Web Page v1.0
<p>다양한 뉴스레터들을 파악하는 것은 힘든일입니다.</p>
<p>구독하고 있는 뉴스레터를 정리해주고 비슷한 주제와 접해보지 못한 관심사를 다룰 수 있도록 제작했습니다.</p>
<p>웹 사이트 주소: https://attraction.run/</p>
<P>개발기간 : 2024-04 ~ 진행중</P>
<br>

## 팀원 구성

<div align="center">

| **유현우** | **강철원** | **김형주** |
| :------: |  :------: | :------: |
| [<img src="https://github.com/Atractorrr/Attraction-Server/assets/110734817/a954344b-f2b0-4183-bb43-d6521e032ab7" height=150 width=150> <br/> @uhanuu](https://github.com/uhanuu) | [<img src="https://github.com/Atractorrr/Attraction-Server/assets/110734817/f8a338d8-263d-4fba-968d-a352ae6ea8c3" height=150 width=150> <br/> @Ryan-Dia](https://github.com/Ryan-Dia) | [<img src="https://github.com/Atractorrr/Attraction-Server/assets/110734817/978b537e-3d1a-416d-aebd-905d6c23b100" height=150 width=150> <br/> @kim0527](https://github.com/kim0527) |

</div>
<br>

## ⭐️주요 기능
- 메인 페이지

| Light mode | Dark mode |
| :------: |  :------: |
| <img src="https://github.com/Atractorrr/Attraction-Server/assets/110734817/899e9029-32af-43d0-bbd6-8f0a00583ef1" height=250 width=700> | <img src="https://github.com/Atractorrr/Attraction-Server/assets/110734817/dda825ab-73a7-4e0e-8f0f-1186a1ede05f" height=250 width=700> | 
<br>

- 뉴스레터 보관함

| 전체 화면 | 필터 | 읽은 아티클 숨김 |
| :------: |  :------: |  :------: |
| <img src="https://github.com/Atractorrr/Attraction-Server/assets/110734817/5ef1565f-08a1-4001-8976-b952adea8854" height=250 width=700> | <img src="https://github.com/Atractorrr/Attraction-Server/assets/110734817/2b428b25-6ee8-4bb7-819a-0b53e476fa3a" height=250 width=700> | <img src="https://github.com/Atractorrr/Attraction-Server/assets/110734817/df6896ed-2953-4715-8b82-1bcbcd1d4b66" height=250 width=700> |
<br>

- 아티클 상세보기

| 상세보기 | 북마크 추가하기 |
| :------: |  :------: |
| <img src="https://github.com/Atractorrr/Attraction-Server/assets/110734817/68f137d0-50a8-4658-aaf3-fe139184a528" height=250 width=500> | <img src="https://github.com/Atractorrr/Attraction-Server/assets/110734817/1303a2e3-7d2e-4c89-bf70-20581de29b0e" height=250 width=500> |
<br>

- 나머지 페이지

| 마이페이지 | 북마크한 아티클 | 랭킹 |
| :------: |  :------: |  :------: |
| <img src="https://github.com/Atractorrr/Attraction-Server/assets/110734817/509e0ac2-2136-4418-9dcc-784346e0f10d" height=250 width=400> | <img src="https://github.com/Atractorrr/Attraction-Server/assets/110734817/ad07ebbc-0876-40ee-9be6-85224eef18e7" height=250 width=500> | <img src="https://github.com/Atractorrr/Attraction-Server/assets/110734817/4611be4d-f3a7-4241-8967-221dd886165e" height=300 width=280> |
<br>

## 역할 분담

### 😎유현우
- **기능**
  - 사용자 메일함 관리 (뉴스레터 구독시)
  - 아티클/썸네일 이미지 저장 (batch)
  - 뉴스레터 저장
  - API-Server 모니터링 구축(Prometheus + Grafana)

<br>
    
### 👻강철원
- **기능**
    - 작성해주세요

<br>

### 🤖김형주
- **기능**
    - 작성해주세요

<br>

## 개발 환경

- Back-end : Java, Kotlin, Spring Boot, Kafka, MariaDB
- 버전 및 이슈관리 : Github, Jira
- 협업 툴 : Discord, Notion, Jira, Swagger
- 모니터링 : Prometheus + Grafana
- CI/CD : Github Actions
- 서비스 배포 환경 : AWS
- 기획 : [Figma](https://www.figma.com/board/nYXIFI0vsEiO4jltUC79mS/2%EC%B0%A8-%ED%94%84%EB%A1%9C%EC%A0%9D%ED%8A%B8?node-id=2937-5003&t=Q9h42Rltki5tgYc1-0)
- [Git 전략](https://fast-pickle-381.notion.site/Git-963ed11a381848d78ca5889031a0d3eb?pvs=74)
- [코드 컨벤션](https://google.github.io/styleguide/javaguide.html)
<br>

## Database diagrams
<p align="center">
  <img src="https://github.com/Atractorrr/Attraction-Server/assets/110734817/f51f0ca7-7884-4092-9936-c9b5362d84fa">
</p>
<br>

## Test Code
<p align="center">
  <p>테스트 커버리지: 27%</p>
  <img src="https://github.com/Atractorrr/Attraction-Server/assets/110734817/712d9da8-3b0d-440f-9785-af78d5f4568c">
</p>
<br>

## 프로젝트 구조
```markdown
├── java
│   └── run
│       └── attraction
│           ├── AttractionApplication.java
│           └── api
│               ├── swagger
│               │   └── SwaggerConfig.java
│               └── v1
│                   ├── archive
│                   │   ├── Article.java
│                   │   ├── AuditableEntity.java
│                   │   ├── ReadBox.java
│                   │   ├── controller
│                   │   │   └── ArchiveController.java
│                   │   ├── dto
│                   │   │   ├── ArticleDTO.java
│                   │   │   ├── NewsletterDTO.java
│                   │   │   ├── NewsletterEmail.java
│                   │   │   ├── request
│                   │   │   │   └── UserArticlesRequest.java
│                   │   │   └── response
│                   │   │       └── ApiResponse.java
│                   │   ├── repository
│                   │   │   ├── ArticleRepository.java
│                   │   │   ├── ArticleRepositoryCustom.java
│                   │   │   ├── ArticleRepositoryImpl.java
│                   │   │   └── ReadBoxRepository.java
│                   │   └── service
│                   │       └── ArchiveService.java
│                   ├── auth
│                   │   ├── config
│                   │   │   ├── ApplicationConfig.java
│                   │   │   ├── AuthProviderConfig.java
│                   │   │   ├── GoogleLoginConfig.java
│                   │   │   ├── RedisConfig.java
│                   │   │   ├── SecurityConfig.java
│                   │   │   └── properties
│                   │   │       └── GoogleLoginProperties.java
│                   │   ├── controller
│                   │   │   ├── AuthController.java
│                   │   │   ├── AuthTestController.java
│                   │   │   ├── FaviconController.java
│                   │   │   └── advice
│                   │   │       └── AuthenticationExceptionHandler.java
│                   │   ├── filter
│                   │   │   ├── ErrorType.java
│                   │   │   ├── FilterExceptionHandler.java
│                   │   │   ├── FilterMessageResponseDto.java
│                   │   │   ├── HttpLogMessage.java
│                   │   │   ├── RequestLogFilter.java
│                   │   │   └── SessionFilter.java
│                   │   ├── provider
│                   │   │   ├── AuthProvider.java
│                   │   │   ├── exception
│                   │   │   │   ├── GoogleApiAccessTokenException.java
│                   │   │   │   └── GoogleApiCodeException.java
│                   │   │   ├── google
│                   │   │   │   ├── GoogleOAuth.java
│                   │   │   │   └── GoogleOAuthService.java
│                   │   │   └── oauth
│                   │   │       ├── OAuthService.java
│                   │   │       └── OAuthToken.java
│                   │   ├── service
│                   │   │   ├── AuthService.java
│                   │   │   ├── dto
│                   │   │   │   ├── ReissueTokenResponseDto.java
│                   │   │   │   ├── UserStateDto.java
│                   │   │   │   ├── join
│                   │   │   │   │   ├── CheckDuplicationRequsetDto.java
│                   │   │   │   │   ├── CheckDuplicationResponseDto.java
│                   │   │   │   │   └── JoinRequestDto.java
│                   │   │   │   └── login
│                   │   │   │       ├── FirstLoginResponseDto.java
│                   │   │   │       ├── LoginRequestDto.java
│                   │   │   │       └── LoginResponseDto.java
│                   │   │   └── helper
│                   │   │       ├── JoinHelper.java
│                   │   │       └── LoginHelper.java
│                   │   └── session
│                   │       ├── SessionService.java
│                   │       ├── dto
│                   │       │   └── UserDetailBySession.java
│                   │       └── exception
│                   │           ├── InValidUserException.java
│                   │           ├── ResignedUserException.java
│                   │           ├── SessionExceptionHandler.java
│                   │           └── SessionNotFoundException.java
│                   ├── bookmark
│                   │   ├── Bookmark.java
│                   │   ├── controller
│                   │   │   └── BookmarkController.java
│                   │   ├── dto
│                   │   │   └── BookmarkArticleRequest.java
│                   │   ├── exception
│                   │   │   └── BookmarkExceptionHandler.java
│                   │   ├── repository
│                   │   │   └── BookmarkRepository.java
│                   │   └── service
│                   │       └── BookmarkService.java
│                   ├── exception
│                   │   ├── ErrorCode.java
│                   │   ├── ErrorResponse.java
│                   │   └── GlobalExceptionHandler.java
│                   ├── gmail
│                   │   ├── config
│                   │   │   ├── AsyncConfig.java
│                   │   │   ├── KafkaProducerConfig.java
│                   │   │   ├── ProducerProperties.java
│                   │   │   └── SubscribeProducerListener.java
│                   │   ├── dto
│                   │   │   └── UserGmailDto.java
│                   │   ├── entity
│                   │   │   └── GoogleRefreshToken.java
│                   │   ├── event
│                   │   │   ├── SubscribeVo.java
│                   │   │   ├── UserMailEventHandler.java
│                   │   │   └── UserSubscribedEvent.java
│                   │   ├── repository
│                   │   │   └── GoogleRefreshTokenRepository.java
│                   │   └── service
│                   │       └── GmailService.java
│                   ├── home
│                   │   ├── controller
│                   │   │   └── HomeController.java
│                   │   └── service
│                   │       ├── HomeService.java
│                   │       ├── article
│                   │       │   ├── HomeArticleService.java
│                   │       │   └── HomeArticleServiceImpl.java
│                   │       ├── dto
│                   │       │   ├── article
│                   │       │   │   ├── ReceivedArticleNewsletterDto.java
│                   │       │   │   ├── ReceivedArticlesDto.java
│                   │       │   │   └── ReceivedArticlesResponseDto.java
│                   │       │   ├── categories
│                   │       │   │   └── CategoriesResponseDto.java
│                   │       │   ├── newsletter
│                   │       │   │   ├── NewsletterDetailDto.java
│                   │       │   │   └── NewslettersResponseDto.java
│                   │       │   └── search
│                   │       │       └── ArticleSearchDto.java
│                   │       └── newsletter
│                   │           ├── HomeNewsletterService.java
│                   │           └── HomeNewsletterServiceImpl.java
│                   ├── introduction
│                   │   ├── Category.java
│                   │   ├── Newsletter.java
│                   │   ├── Subscription.java
│                   │   ├── UserSubscribedNewsletterCategory.java
│                   │   ├── config
│                   │   │   ├── AutoSubscribeProducerListener.java
│                   │   │   └── KafkaProducerConfig.java
│                   │   ├── controller
│                   │   │   ├── IntroductionController.java
│                   │   │   └── SubscriptionController.java
│                   │   ├── dto
│                   │   │   └── response
│                   │   │       ├── ApiResponse.java
│                   │   │       ├── NewsletterResponse.java
│                   │   │       ├── NewslettersByCategoryResponse.java
│                   │   │       └── PreviousArticleResponse.java
│                   │   ├── event
│                   │   │   └── AutoSubscribeVo.java
│                   │   ├── exception
│                   │   │   ├── ErrorMessages.java
│                   │   │   ├── IntroductionExceptionHandler.java
│                   │   │   └── ResourceNotFoundException.java
│                   │   ├── repository
│                   │   │   ├── NewsletterRepository.java
│                   │   │   ├── SubscriptionRepository.java
│                   │   │   └── UserSubscribedNewsletterCategoryRepository.java
│                   │   ├── service
│                   │   │   ├── IntroductionService.java
│                   │   │   ├── KafkaProducerService.java
│                   │   │   └── SubscriptionService.java
│                   │   └── utils
│                   │       └── SubscriptionUtil.java
│                   ├── monitoring
│                   │   └── MicrometerConfig.java
│                   ├── mypage
│                   │   ├── controller
│                   │   │   └── MypageController.java
│                   │   └── service
│                   │       ├── MypageService.java
│                   │       ├── archive
│                   │       │   ├── article
│                   │       │   │   ├── MypageArticleService.java
│                   │       │   │   └── MypageArticleServiceImpl.java
│                   │       │   └── newsletter
│                   │       │       ├── MypageNewsletterService.java
│                   │       │       └── MypageNewsletterServiceImpl.java
│                   │       ├── calendar
│                   │       │   ├── MypageCalendarService.java
│                   │       │   └── MypageCalendarServiceImpl.java
│                   │       ├── dto
│                   │       │   ├── MessageResponse.java
│                   │       │   ├── archive
│                   │       │   │   ├── article
│                   │       │   │   │   ├── MypageArticle.java
│                   │       │   │   │   ├── RecentArticleNewsletterDto.java
│                   │       │   │   │   ├── RecentArticlesDto.java
│                   │       │   │   │   ├── RecentArticlesResponseDto.java
│                   │       │   │   │   ├── UserArticleDetail.java
│                   │       │   │   │   ├── UserArticleImg.java
│                   │       │   │   │   └── UserReadBoxDetail.java
│                   │       │   │   └── newsletter
│                   │       │   │       ├── MypageNewsletterDetail.java
│                   │       │   │       └── SubscribeResponseDto.java
│                   │       │   ├── calendar
│                   │       │   │   ├── CalendarDay.java
│                   │       │   │   └── CalendarResponseDto.java
│                   │       │   └── userDetail
│                   │       │       ├── UpdateImgRequestDto.java
│                   │       │       ├── UpdateInterestRequestDto.java
│                   │       │       ├── UpdateNicknameRequestDto.java
│                   │       │       ├── UpdateOccupationRequestDto.java
│                   │       │       ├── UpdateUserExpirationRequestDto.java
│                   │       │       ├── UserDetailDto.java
│                   │       │       └── UserDetailsResponseDto.java
│                   │       └── user
│                   │           ├── MypageUserService.java
│                   │           └── MypageUserServiceImpl.java
│                   ├── rank
│                   │   ├── ConsistencyRank.java
│                   │   ├── ExtensiveRank.java
│                   │   ├── Rank.java
│                   │   ├── ReadBoxEvent.java
│                   │   ├── controller
│                   │   │   └── RankController.java
│                   │   ├── repository
│                   │   │   ├── ConsistencyRankRepository.java
│                   │   │   ├── ExtensiveRankRepository.java
│                   │   │   └── ReadBoxEventRepository.java
│                   │   └── service
│                   │       ├── RankService.java
│                   │       ├── calculator
│                   │       │   ├── ConsistencyRankCalculator.java
│                   │       │   └── ExtensiveRankCalculator.java
│                   │       └── dto
│                   │           ├── ConsistencyRankResponseDto.java
│                   │           ├── ExtensiveRankResponseDto.java
│                   │           └── RankDetailDto.java
│                   ├── statistics
│                   │   ├── AgeGroup.java
│                   │   ├── AgeGroupStatistics.java
│                   │   ├── NewsletterEvent.java
│                   │   ├── OccupationStatistics.java
│                   │   ├── controller
│                   │   │   └── StatisticsController.java
│                   │   ├── repository
│                   │   │   ├── AgeGroupStatisticsRepository.java
│                   │   │   ├── NewsletterEventRepository.java
│                   │   │   └── OccupationStatisticsRepository.java
│                   │   └── service
│                   │       ├── StatisticsService.java
│                   │       ├── ageGroup
│                   │       │   └── AgeGroupStatisticsCalculator.java
│                   │       └── occupation
│                   │           └── OccupationStatisticsCalculator.java
│                   └── user
│                       ├── Interest.java
│                       ├── Occupation.java
│                       ├── Role.java
│                       ├── User.java
│                       ├── UserDetail.java
│                       ├── UserValidator.java
│                       ├── repository
│                       │   ├── UserDetailRepository.java
│                       │   └── UserRepository.java
│                       ├── service
│                       │   ├── UserDetailsServiceForSecurity.java
│                       │   ├── UserService.java
│                       │   └── UserServiceImpl.java
│                       └── validator
│                           ├── exception
│                           │   ├── InvalidNicknameException.java
│                           │   └── InvalidNicknameExceptionHandler.java
│                           └── nickname
│                               ├── EnglishValidator.java
│                               ├── KoreanValidator.java
│                               ├── NicknameValidator.java
│                               ├── NumberValidator.java
│                               └── SpecialValidator.java
└── resources
    ├── application.yml
    ├── credentials.json
    └── data.sql

```
