SET FOREIGN_KEY_CHECKS=0;
TRUNCATE TABLE article;
TRUNCATE TABLE newsletter;
TRUNCATE TABLE read_box;
TRUNCATE TABLE users;
TRUNCATE TABLE subscription;
SET FOREIGN_KEY_CHECKS=1;

INSERT INTO newsletter (has_confirmation_email,is_auto_subscribe_enabled,is_deleted,created_at,modified_at,name,email,description,main_link,nickname,subscribe_link,thumbnail_url,upload_days,category) VALUES
    (0,0,0,'2024-06-21 14:42:28.364766','2024-06-21 14:42:28.364766','remem','remem@maily.so','영감을 주는 메시지. 삶을 풍요롭게 만드는 좋은 문장들.','https://maily.so/remem?mid=b25d244e','remem','https://maily.so/remem?mid=b25d244e','https://cdn.maily.so/202401/1706402869895535.png','매주 월~금','TREND_LIFE'),
    (0,1,0,'2024-06-21 14:42:28.408371','2024-06-21 14:42:28.408371','큐레터',' Q-letter@i-boss.co.k','마케터를 위한 아이디어 조각','https://www.qletter.co.kr/','큐레터','https://www.qletter.co.kr/','https://img.stibee.com/b94a3cf1-a330-4fe9-a510-c1eee8792769.png','매주 월, 목 오전 10시','TREND_LIFE'),
    (0,0,0,'2024-06-21 14:42:28.419370','2024-06-21 14:42:28.419370','르코의 아모브레터','lecor.lee@gmail.com','브랜딩에 막막함을 느끼는 창업자를 위한, 단계별 접근을 도와주는 브랜드 비즈니스 뉴스레터 입니다. 브랜드 성공 케이스를 분석한 고품질 아티클과 실전 플레이북을 제공합니다.',' https://maily.so/lecor.amob','르코의 아모브레터',' https://maily.so/lecor.amob','https://cdn.maily.so/202404/1714297106187197.png','매주 수요일','TREND_LIFE'),
    (0,0,0,'2024-06-21 14:42:28.430016','2024-06-21 14:42:28.430016','슈톡',' shoetalk@maily.so','스니커즈 신의 다양한 모습을 개인의 취향으로 기록합니다','https://maily.so/shoetalk','슈톡','https://maily.so/shoetalk','https://cdn.maily.so/mailyd482cb3fe76d8e26e1e698647d1e4ba61607743849','매주 수요일','TREND_LIFE'),
    (0,0,0,'2024-06-21 14:42:28.441876','2024-06-21 14:42:28.441876','플리티 위키','plitywiki@maily.so','영양제 속 성분 정보를 공신력 있는 논문에 근거하여 알려드립니다.',' https://maily.so/plitywiki','플리티 위키',' https://maily.so/plitywiki','https://cdn.maily.so/202403/1710810670954539.png','매주 수요일','FOOD'),
    (0,0,0,'2024-06-21 14:42:28.452622','2024-06-21 14:42:28.452622','미쉬울랭가이드','mshl.guide@gmail.com',': 주말에 뭐 해먹지? 미쉬울랭과 함께 고민 끝! 맛나고 간편한 밀키트와 함께라면 우리 모두 방구석 셰프🧑‍🍳',' https://mshlguide.stibee.com/','미쉬울랭가이드','https://mshlguide.stibee.com/subscription/','https://img.stibee.com/4398f0f8-3f4c-43a8-b367-e3165ac5bbe7.png','매주 화요일','FOOD'),
    (0,0,0,'2024-06-21 14:42:28.465334','2024-06-21 14:42:28.465334','HBR Korea','editor@hbrkorea.com','하버드비즈니스리뷰는 현재의 리더급 직장인과 미래의 리더급 직장인을 위한 전문적 가이드라인을 제시해드리고 있습니다. 다른 어느 곳에서도 볼 수 없는, 비즈니스 분야의 최고 전문가들과 커리어 코치들의 조언을 전달 드립니다.','https://www.hbrkorea.com/','
HBR Korea','https://www.hbrkorea.com/','https://www.hbrkorea.com/images/common/site_logo_2020.png','매주 목요일','BUSINESS_FINANCIAL_TECHNOLOGY'),
    (0,1,0,'2024-06-21 14:42:28.474839','2024-06-21 14:42:28.474839','한경 CFO insight','editor@hankyung.com','경영 혁신의 최전선에서 활약하는 
CFO들을 위해 최신 자본시장 소식과 산업계 동향을 
매주 목요일 뉴스레터로 전해 드리겠습니다','https://www.hankyung.com/newsletter','
한국경제신문',' https://page.stibee.com/subscriptions/83403','https://s3.ap-northeast-2.amazonaws.com/img.stibee.com/35366_1632295289.jpg','매주 목요일','BUSINESS_FINANCIAL_TECHNOLOGY'),
    (0,1,0,'2024-06-21 14:42:28.484592','2024-06-21 14:42:28.484592','부딩','everybody@booding.co','부.알.못 밀레니얼을 위한 구독형 부동산 뉴스레터, 부딩','https://www.booding.co/blog','BOODING','https://www.booding.co/','https://static.wixstatic.com/media/6cb3fa_86cf0dfe2f3b4efaba51dc44b0de4433~mv2.jpg/v1/fill/w_627,h_698,al_c,q_85,enc_auto/6cb3fa_86cf0dfe2f3b4efaba51dc44b0de4433~mv2.jpg','매주 화~금','BUSINESS_FINANCIAL_TECHNOLOGY'),
    (0,1,0,'2024-06-21 14:42:28.496885','2024-06-21 14:42:28.496885','weekly biz letter',' letter@chosun.com','경영혁신의 최전선에서 활약하는 CFO들을 위한 뉴스레터 자본시장 소식과 산업계 동향을 매주 목요일마다 전해드립니다.','https://www.chosun.com/newsletter/','Weekly BIZ','https://page.stibee.com/subscriptions/146096','https://img.stibee.com/388b18c0-fdc8-452b-8235-5acc0946b86f.png','매주 월~금','BUSINESS_FINANCIAL_TECHNOLOGY');
INSERT INTO newsletter (has_confirmation_email,is_auto_subscribe_enabled,is_deleted,created_at,modified_at,name,email,description,main_link,nickname,subscribe_link,thumbnail_url,upload_days,category) VALUES
    (0,1,0,'2024-06-21 14:42:28.508733','2024-06-21 14:42:28.508733','디그 ','dig@mk.co.kr','세상 돌아가는 경제 이야기,
하루 5분 <디그>로 편하게 읽어보세요.','https://teamdig.imweb.me/','디그(dig)','https://page.stibee.com/subscriptions/159161','https://s3.ap-northeast-2.amazonaws.com/img.stibee.com/a82ab710-ca85-4f2e-bda8-3c7abcbad46a.png','매주 월, 수, 금','ECONOMY'),
    (0,1,0,'2024-06-21 14:42:28.519169','2024-06-21 14:42:28.519169','머니레터','moneyletter@uppity.co.kr','매일 출근길 5분, 떠먹여주는 경제 소식','https://uppity.co.kr/','UPPITY','https://uppity.co.kr/newsletter/money-letter/','https://i0.wp.com/uppity.co.kr/wp-content/uploads/2023/07/logo-uppity.png?fit=200%2C160&ssl=1','매일 월~금','ECONOMY'),
    (0,1,0,'2024-06-21 14:42:28.531765','2024-06-21 14:42:28.531765','H:730','daily@hani.co.kr','쉴 새 없이 쏟아지는 속보 더미에서 뉴스의 흐름을 붙잡는 게 쉬운 일이 아니죠. 바쁜 당신을 대신해 꼭 필요한 뉴스를 엄선해 배달하려 합니다. 기자 경력 25년 안팎의 뛰어난 ‘뉴스 선구안’을 지닌 <한겨레> 편집위원들이 ‘비대면 배달부’가 되어 매일 아침 여러분을 찾아갑니다. ','https://page.stibee.com/archives/70653','한겨레 H:730','https://page.stibee.com/subscriptions/70653','https://s3.ap-northeast-2.amazonaws.com/img.stibee.com/30367_list_70653_archives_header_image.gif?v=1612255300','매주 월~금','CURRENT_AFFAIRS_SOCIETY'),
    (0,1,0,'2024-06-21 14:42:28.543779','2024-06-21 14:42:28.543779','5분 칼럼','letter@chosun.com','무릎을 탁 치게 만드는 세상에 대한 통찰과 해석을 지금 만나보세요.','https://www.chosun.com/newsletter/','오피니언팀','https://page.stibee.com/subscriptions/91170','https://img.stibee.com/1fcd2832-af21-440b-9fde-86238c061003.png','매주 월~금','CURRENT_AFFAIRS_SOCIETY'),
    (0,1,0,'2024-06-21 14:42:28.556381','2024-06-21 14:42:28.556381','점선면','letter@khan.kr','월·수·금엔 엄선한 ''한 편의 기사''로 대화하는 점선면Lite','https://spark-guava-17f.notion.site/db1c6982572848119a26a02ec3b17077','경향신문 점선면','https://page.stibee.com/subscriptions/228606?groupIds=236936','','매주 월, 수, 금','CURRENT_AFFAIRS_SOCIETY'),
    (0,1,0,'2024-06-21 14:42:28.575003','2024-06-21 14:42:28.575003','뉴닉','whatsup@newneek.co','세상 돌아가는 소식, 알고는 싶지만 신문 볼 새 없이 바쁜 게 우리 탓은 아니잖아요!
월/화/수/목/금 아침마다 세상 돌아가는 소식을 메일로 받아보세요.','https://newneek.co/','NEWNEEK','https://newneek.co/','https://newneek.co/static/media/gosum-home.2af8a9fb.png','매주 월~금','CURRENT_AFFAIRS_SOCIETY'),
    (0,1,0,'2024-06-21 14:42:28.584367','2024-06-21 14:42:28.584367','미라클레터','miraklelab@mk.co.kr ','미라클레터는 대한민국 CEO들이 ''최애''하는 글로벌 트렌드 뉴스레터예요. 9만명 이상이 매일 아침 미라클레터로 미라클 모닝을 하고 있습니다. ','https://www.mk.co.kr/miraklelab/','미라클레터 ','https://page.stibee.com/subscriptions/33271','https://img.stibee.com/7bfa5546-fb4e-4c97-9ec0-ebb16cdd0b01.png','주 3회 이상','TREND_LIFE'),
    (0,0,0,'2024-06-21 14:42:28.595137','2024-06-21 14:42:28.595137','뭐지 뉴스레터','newsletter@moji.or.kr
','주간 IT 소식을 요약해 매주 수요일에 여러분의 메일함으로 찾아가는 뉴스레터!','https://moji.or.kr/','뭐지 뉴스레터','https://moji.or.kr/','https://moji.or.kr/img/intellectual-property/moji_newlogo_333333.png','매주 수요일','IT_TECH'),
    (0,1,0,'2024-06-21 14:42:28.607250','2024-06-21 14:42:28.607250','북플래터','bookplatter.letter-gmail.com@send.stibee.com','도서관이나 서점에서 어떤 책을 읽을지 몰라 헤맨 적이 있나요? 북플래터와 함께 여러분의 취향을 찾아봐요. 수많은 책들 사이에서 헤매지 않도록, 다양한 취향의 조각들을 엄선해서 만든 ‘북플래터’. 수많은 책들 중 나의 시선이 머무는 책으로 나만의 북플래터를 만들어보고 싶다면, 책을 통해 나를 더 잘 알고 싶다면, 북플래터와 함께 매주 세 권의 책을 만나보세요! 매주 월요일 오전, 따끈따끈한 플래터를 준비할게요','https://page.stibee.com/archives/112470','북플래터','https://page.stibee.com/subscriptions/112470','https://s3.ap-northeast-2.amazonaws.com/img.stibee.com/45248_list_112470_subscriptions_header_image.png?v=1648792178','매주 월요일','CULTURE_ART'),
    (0,0,0,'2024-06-21 14:42:28.619682','2024-06-21 14:42:28.619682','날마다 그림 한 점','artify@qwave.co.kr','미술플랫폼 아티파이에서 매일 한점의 그림을 소개해드려요!!! 클래식 명화에서부터 아티파이 소속  작가들의 현대미술 작품을 월요일부터 금요일까지 날마다 여러분의 메일 수신함으로  보내드립니다 ~~~','https://page.stibee.com/archives/171294','아티파이','https://page.stibee.com/subscriptions/171294','https://s3.ap-northeast-2.amazonaws.com/img.stibee.com/60609_list_171294_archives_header_image.jpg?v=1651129569','매주 월~금','CULTURE_ART');
INSERT INTO newsletter (has_confirmation_email,is_auto_subscribe_enabled,is_deleted,created_at,modified_at,name,email,description,main_link,nickname,subscribe_link,thumbnail_url,upload_days,category) VALUES
    (0,1,0,'2024-06-21 14:42:28.630568','2024-06-21 14:42:28.630568','안티 에그','editor@antiegg.kr','ANTIEGG가 발행하는 모든 콘텐츠를 뉴스레터로 빠르게 만나보세요.','https://antiegg.kr/','ANTIEGG 팀','https://antiegg.stibee.com/subscription/','https://s3.ap-northeast-2.amazonaws.com/img.stibee.com/8ab7a42a-0095-4362-9db7-3c68d0425495.jpg','매주 월~목','CULTURE_ART'),
    (0,1,0,'2024-06-21 14:42:28.640828','2024-06-21 14:42:28.640828','아트레터','info@artlamp.org','예술을 통해 영감을 얻고 내면의 성찰을 바라며 아트레터는 시작되었습니다. 고전 명화에서부터 동시대 미술까지, 한 편지에 4-5 작품을 소개하며 일상을 살아가는데 울림을 주고자 합니다. 일주일에 한 번, 단 5분의 시간을 예술에 집중해 보세요. 마음을 풍요롭게 만드는 아트 스토리와 미감을 높여줄 예술 작품이 삶을 보다 완성도있게 만들어 줄 것입니다.','https://artlamp.org/artletter','아트레터💌','https://artletter.stibee.com/subscription/','https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSsBrmP3tyBwEYN9SgCMC-kfh1mQKPDnnm-9tBKKCxJ1Z9XmrkHdLFy&usqp=CAE&s','매주 금요일','CULTURE_ART'),
    (0,1,0,'2024-06-21 14:42:28.651410','2024-06-21 14:42:28.651410','누벨바그','nouvellevague0401-gmail.com@send.stibee.com','누벨바그는 ''새로운 물결(New Wave)''이라는 뜻의 불어로, 1950년대 프랑스에서 일어난 영화계 운동입니다.
여러분의 일상 속 ''문화 콘텐츠''라는 새 바람🌊이 불도록 매주 소식을 전하고 있습니다
구독자분들께는 ''전시 및 공연/영화/책/방송 프로그램'' 4가지 테마로 매주 금요일 정오에 메일함📩으로 찾아가겠습니다! ','https://nouvelle.stibee.com/','누벨바그','https://nouvelle.stibee.com/subscription/','https://s3.ap-northeast-2.amazonaws.com/img.stibee.com/faee30a4-a2fd-4adb-b0f9-00037701dd57.jpg','매주 금요일','CULTURE_ART'),
    (0,0,0,'2024-06-21 14:42:28.660095','2024-06-21 14:42:28.660095','므흐','mhletterforu@naver.com
','안녕하세요. 뉴스레터 므흐입니다. 매주 수요일 오전 7시, 영화/드라마, 시, 콘텐츠, 전시회, 장르 등 다양한 분야의 새로운 문화예술 뉴스를 전해드립니다.','https://page.stibee.com/archives/169401','ㅁㅎ(MH)','https://page.stibee.com/subscriptions/169401','https://img.stibee.com/64443_1708423590.png','매주 수요일','CULTURE_ART'),
    (0,0,0,'2024-06-21 14:42:28.672010','2024-06-21 14:42:28.672010','Random Daily Art','es-reply@randomdailyart.com','Random Daily Art is a free daily newsletter with art delivered straight to your inbox every morning.','https://randomdailyart.com/art/','Random Daily Art','https://randomdailyart.com//','','매주 월~금','CULTURE_ART'),
    (0,1,0,'2024-06-21 14:42:28.682252','2024-06-21 14:42:28.682252','퀘스천퍼데이','contact@questionperday.me','우리는 스스로에 대해 아직 잘 몰라요. 알지 못하면 혼란스럽기 마련이죠. 퀘스천퍼데이는 매일, 나를 향한 질문을 보내드려요. 답하는 건, 여러분의 몫이에요. 꾸준히 나에 대해 생각하다 보면, 어느새 달라진 나를 발견할 거예요. 매일 아침, 질문을 받아보기로 결정하셨나요? 그 소중한 마음 이어갈 수 있도록, 좋은 질문들을 성실히 보내드릴게요.','https://questionperday.stibee.com/','퀘스천퍼데이','https://questionperday.stibee.com/subscription/','https://img.stibee.com/92773_1687023399.png','매주 월~금','HOBBY_SELF_DEVELOPMENT'),
    (0,0,0,'2024-06-21 14:42:28.692865','2024-06-21 14:42:28.692865','Trend A Word','trendaword@maily.so','오늘 꼭 알아야 하는 트렌드 한 단어!','https://maily.so/trendaword','Trend A Word','https://page.stibee.com/subscriptions/161860','https://cdn.maily.so/maily3a9ea478edb9ab39e568f7a2ef8019141616945993','매주 화~목','HOBBY_SELF_DEVELOPMENT'),
    (0,0,0,'2024-06-21 14:42:28.707231','2024-06-21 14:42:28.707231','사이드 프로젝트','sideinseoul@143684213.mailchimpapp.com','좋아하는 것도 너무 많고, 나만의 어떤 것을 시작해보고 싶고, 이것저것 해보고 싶은게 너무 많아서 고민인 사람을 위한 레터','https://sideproject.co.kr/newsletter','Side','https://sideproject.co.kr/newsletter','https://dim.mcusercontent.com/cs/a89600e386cefa6d4758d1106/images/3013399b-612d-4825-8f72-ff551ca963d6.png?w=580&dpr=2&rect=0%2C120%2C1890%2C1170','매주 수요일','HOBBY_SELF_DEVELOPMENT'),
    (0,1,0,'2024-06-21 14:42:28.717406','2024-06-21 14:42:28.717406','계발메이트','upmate2024@naver.com','알을 깬 우리는 무엇이든 될 수 있다
안녕하세요, ''알을 깬 우리는 무엇이든 될 수 있다'' "계발메이트"입니다.   자기계발에 대한 정보를 나누는 것뿐만 아니라,  함께 자기계발을 이루어가는 것을 취지로 합니다.','https://upmate.stibee.com/','계발메이트','https://upmate.stibee.com/subscription/','https://img.stibee.com/ec59b93b-9bf7-43f9-835d-8e0066dc4915.jpg','매주 수요일','HOBBY_SELF_DEVELOPMENT'),
    (0,1,0,'2024-06-21 14:42:28.729193','2024-06-21 14:42:28.729193','아하레터','aha.contents@wisdomhouse.co.kr','아이디어나 영감을 주는 책과 콘텐츠를 모아 저만의 방식으로 다시 글로 정리해 뉴스레터로 만들어보았습니다. 이를 더 많은 분들과 함께 보면 좋을 것 같아 이름을 ''아하!''로 정했어요. 이렇게 시작하게 된 아하레터, 지금은 많은 분들이 함께해주고 계세요. 격주에 한 번씩 여러분들께 성장을 위한 아하! 모먼트를 전합니다.','https://www.aha-contents.com//','아하레터','https://page.stibee.com/subscriptions/61765?groupIds=56635','https://img.stibee.com/26042_1704791202.png','격주 목요일','HOBBY_SELF_DEVELOPMENT');
INSERT INTO newsletter (has_confirmation_email,is_auto_subscribe_enabled,is_deleted,created_at,modified_at,name,email,description,main_link,nickname,subscribe_link,thumbnail_url,upload_days,category) VALUES
    (0,1,0,'2024-06-21 14:42:28.742564','2024-06-21 14:42:28.742564','밑미레터','hello@nicetomeetme.kr','매주 월요일 아침 ''진짜 나''를 찾아가는 사람들의 이야기와 마음을 위로하는 이야기가 당신의 메일함으로 도착합니다. 나의 사소한 고민도 밑미레터에 털어놔보세요.','https://meetmeletter.stibee.com','meet me(밑미)','https://meetmeletter.stibee.com/subscription/','https://s3.ap-northeast-2.amazonaws.com/img.stibee.com/2c07e8c6-3790-4998-8835-d6e8485b6567.png','매주 월요일','HOBBY_SELF_DEVELOPMENT'),
    (0,0,0,'2024-06-21 14:42:28.751632','2024-06-21 14:42:28.751632','프리즘','priiism@maily.so','프리즘은 MBTI 내향형(I형) 인간 세 명의 취향이 담긴 뮤직 큐레이션 뉴스레터 입니다.','https://maily.so/priiism','PRIIISM','https://maily.so/priiism','https://cdn.maily.so/maily046f54b5e61222f723219dd785c56aa61621068668','매주 목요일','ENTERTAINMENT'),
    (0,0,0,'2024-06-21 14:42:28.762929','2024-06-21 14:42:28.762929','씨네웨이','cineway.kr-gmail.com@send.stibee.com','할리우드 미디어 뉴스 소식을 다루는 항공사 씨네웨이에 오신 걸 환영합니다!
매주 월요일 여러분들의 비행을 책임질 예정이에요.','https://cineway.stibee.com/','씨네웨이','https://cineway.stibee.com/subscription/','https://img.stibee.com/fcea2ae7-3d61-4da5-a9e5-94c6e05c6920.png','매주 월요일','ENTERTAINMENT'),
    (0,0,0,'2024-06-21 14:42:28.776212','2024-06-21 14:42:28.776212','영화한잔','acupofmovie18-gmail.com@send.stibee.com','넘쳐나는 콘텐츠 속 어떤 것을 봐야 할지 모르는 당신!
OTT, 유튜브 썸네일만 배회하다 시간 낭비하는 당신!

현대인을 위한 콘텐츠 알짜배기 티타임
콘텐츠 복합뉴스레터 <영화한잔>

👣 격주 목요일 오전 9시에 찾아갑니다!
','https://acupofmovie.stibee.com/','
영화한잔','https://acupofmovie.stibee.com/subscription/','https://img.stibee.com/91d70c08-4500-4834-90cb-30128ad00114.png','매주 목요일','ENTERTAINMENT'),
    (0,0,0,'2024-06-21 14:42:28.790684','2024-06-21 14:42:28.790684','수플레','hello@sooplaylist.com','수요일의 플레이리스트, 수플레

오늘 하루, 얼마나 많은 음악이 당신을 스쳐 지나갔나요?
수많은 음악 중 기억에 남을만한 것을 찾기란 쉽지 않죠.
하지만 짧은 이야기와 함께라면? ','https://page.stibee.com/archives/66675','Yours, G','https://page.stibee.com/subscriptions/66675','https://s3.ap-northeast-2.amazonaws.com/img.stibee.com/28544_list_66675_archives_header_image.png?v=1642472269','매주 수요일','ENTERTAINMENT'),
    (0,1,0,'2024-06-21 14:42:28.803713','2024-06-21 14:42:28.803713','STEW!','newsletter@we-eat-stew.com','모두의 이야기가 어우러져 깊은 맛을 내는, 맛있는 케이팝 소식을 전해요
','https://page.stibee.com/archives/167678','stew!','https://page.stibee.com/subscriptions/167678','https://img.stibee.com/237ab09c-363c-4919-b1e0-6a4a048bce25.jpg','매달 5일, 15일, 25일','ENTERTAINMENT'),
    (0,0,0,'2024-06-21 14:42:28.814750','2024-06-21 14:42:28.814750','디스콰이엇','williamjung@disquiet.io
stevekwon@disquiet.io
hyunsolpark@disquiet.io','새로운 프로덕트 트렌드 및 다른 메이커들의 경험담을 뉴스레터로 받아보세요. 💌

','https://disquiet.io/newsletter','디스콰이엇 정민교
디스콰이엇 권도언
디스콰이엇 박현솔','https://disquiet.io/newsletter','https://img.stibee.com/f354c38f-d0e1-4ef9-8712-5445518c9bbf.png','주 1회 이상','IT_TECH'),
    (0,1,0,'2024-06-21 14:42:28.824353','2024-06-21 14:42:28.824353','미라클레터','miraklelab@mk.co.kr','"미라클 모닝을 하는 직장인들의 참고서"🌞

미라클레터는 대한민국 CEO들이 ''최애''하는 글로벌 트렌드 뉴스레터예요. 9만명 이상이 매일 아침 미라클레터로 미라클 모닝을 하고 있습니다. 💌

매일경제의 실리콘밸리 특파원과 기자들이 글로벌 트렌드, 테크놀로지 소식, 빅테크 주식, HR·리더십, 혁신 문화 스토리 등을 인사이트 있게 담아, 주 3회 이상 새벽 시간대에 이메일로 보내드리고 있어요. ','https://m.mk.co.kr/newsletter/past/7','미라클레터','https://page.stibee.com/subscriptions/33271','https://img.stibee.com/7bfa5546-fb4e-4c97-9ec0-ebb16cdd0b01.png','주 3회 이상','IT_TECH'),
    (0,0,0,'2024-06-21 14:42:28.832907','2024-06-21 14:42:28.832907','슫스레터','itnews@samsungsdsletter.com','삼성SDS 멘토 삼총사가 전하는
쉽고 재미있는 IT 트렌드!

슫스레터는 한 달에 한 번,
여러분의 메일함으로 찾아갑니다!🤗','https://sdsletter.stibee.com/','삼성SDS 소셜미디어','https://sdsletter.typeform.com/to/MvKvOb07','https://img.stibee.com/3aeeef53-d0ed-49e2-a344-daa6c850d968.jpg','한 달에 한 번','IT_TECH'),
    (0,0,0,'2024-06-21 14:42:28.842503','2024-06-21 14:42:28.842503','Korean FE Article','
kofearticle@substack.com','안녕하세요! 👋
Korean FE Article Team 입니다.
저희는 2022년 1월부터 시작된 활동으로 영어로 작성된 프론트엔드 아티클을 번역하고 공유하는 활동을 하고 있습니다. 매주 다양한 아티클을 번역하고 공유해 다양한 정보를 한글로 쉽게 이해하는 것을 목표로 하고 있습니다.
','https://kofearticle.substack.com/','
Korean FE article Team','https://kofearticle.substack.com/','https://substackcdn.com/image/fetch/w_96,c_limit,f_auto,q_auto:good,fl_progressive:steep/https%3A%2F%2Fbucketeer-e05bbc84-baa3-437e-9518-adb32be77984.s3.amazonaws.com%2Fpublic%2Fimages%2F13b944aa-4b63-4c0e-97f0-23df3c2261a7_1280x1280.png','비정기','IT_TECH');
INSERT INTO newsletter (has_confirmation_email,is_auto_subscribe_enabled,is_deleted,created_at,modified_at,name,email,description,main_link,nickname,subscribe_link,thumbnail_url,upload_days,category) VALUES
    (0,0,0,'2024-06-21 14:42:28.852172','2024-06-21 14:42:28.852172','노마드코더','help@nomadcoders.co','최신 개발 Dev 뉴스. IT 이슈 등 개발자의 일과 성장에 도움이 되는 정보를 모아 보내드려요.
AI 소식, 사이드프로젝트, UIUX, 서비스기획 등 개발 이외의 주제도 다루고 있어요.
노마드코더의 각종 이벤트, 강의 업데이트, 챌린지 소식도 함께 전해요.
매주 금요일, 당신의 메일함으로 찾아갑니다.
강의 추천, 이벤트 정보 등의 광고성 정보가 함께 전송될 수 있습니다.','https://nomadcoders.co/','
Nomad Coders','https://nomadcoders.us16.list-manage.com/subscription?u=a99b43453db5050f1f26b2744&id=4313d957c9','https://mcusercontent.com/a99b43453db5050f1f26b2744/images/d56edf62-963b-4631-8f7b-36780975677f.png','매주 금요일','IT_TECH'),
    (0,1,0,'2024-06-21 14:42:28.863190','2024-06-21 14:42:28.863190','SOSIC 소식','sosic.official-gmail.com@send.stibee.com','공간을 경험하는 모든 구성원들을 위해 놓쳐선 안될 트렌드를 큐레이팅하고 새로운 관점으로 이슈를 전합니다','https://sosicweekly.com/archive','SOSIC 소식','https://sosicweekly.com/subscription','https://cdn.imweb.me/thumbnail/20240617/c3900d53067c1.png','매주 월요일','LIVING_INTERIOR'),
    (0,1,0,'2024-06-21 14:42:28.877980','2024-06-21 14:42:28.877980','브리크brique','brique153@brique.co','도시, 공간, 사람을 담습니다. 도시인의 삶을 풍요롭게, 일상에 영감을 주는 공간과 라이프스타일을 전해드립니다.','https://page.stibee.com/archives/50582','브리크매거진','https://page.stibee.com/subscriptions/50582','https://s3.ap-northeast-2.amazonaws.com/img.stibee.com/21079_list_50582_archives_header_image.jpg?v=1615894612','매주 수요일','LIVING_INTERIOR'),
    (0,0,0,'2024-06-21 14:42:28.889708','2024-06-21 14:42:28.889708','낰낰','info@saho-official.com','내 방 좀 멋지게 꾸미고 싶은데 뭐부터 해야할 지 모르겠어..'' 라는 고민에서 시작한 뉴스레터','https://letterknockknock.stibee.com/','낰낰','https://letterknockknock.stibee.com/subscription/','https://img.stibee.com/d1504f7b-3aef-4e63-9baa-fb5c3c59752d.png','매주 수요일','LIVING_INTERIOR'),
    (0,0,0,'2024-06-21 14:42:28.900186','2024-06-21 14:42:28.900186','스위트스팟 팝콘 ','mailmaster@sweetspot.co.kr','주목해야 하는 공간 트렌드부터 가볼 만한 리테일과 팝업스토어 정보까지,
팝콘에서 한 번에 확인 하세요.','https://www.sweetspot.co.kr/popcon','스위트스팟 팝콘','https://page.stibee.com/subscriptions/149674?groupIds=293944','','격주에 한번','LIVING_INTERIOR'),
    (0,0,0,'2024-06-21 14:42:28.915309','2024-06-21 14:42:28.915309','FURSYS','officewelove@fursys.com','OFFICE INSIGHT에서는 전문적인 공간 솔루션은 물론,  우리 회사에 적용할 수 있는 가치 있는 정보를 전합니다.  오피스 라이프에 활기를 더하고 싶다면, 퍼시스 뉴스레터 속 유익한 이야기!','https://fursys.stibee.com/','FURSYS','https://fursys.stibee.com/subscription/','https://img.stibee.com/f221ffae-b76f-42ef-b343-b906ec56cd65.png','비정기','LIVING_INTERIOR'),
    (0,0,0,'2024-06-21 14:42:28.927377','2024-06-21 14:42:28.927377','FAPER','news@fstvl.life','공연 덕후들이 만드는 페스티벌, 공연, 음악 뉴스레터 반가운 공연 소식과 다양한 음악 콘텐츠를 담아,
매주 수요일 저녁 공연 덕후들을 찾아갑니다!','https://page.stibee.com/archives/70350','FAPER','https://faper.stibee.com/subscription/','https://img.stibee.com/30209_1649233724.png','매주 수요일','LIVING_INTERIOR'),
    (0,1,0,'2024-06-21 14:42:28.938197','2024-06-21 14:42:28.938197','카고레터','cagoletter@gorilladistrict.com','자본주의 필수템 신용카드와 체크카드! 멋 모르고 발급 받아 혜택은 못 받는 것 같다면? 카고레터의 PICKER가 되어보세요! 📊 사람들에게 인기 있는 카드 차트 💳 내게 필요한 알짜 신용/체크카드 📰 놓칠 수 없는 카드 출시&단종 소식까지!
','https://www.card-gorilla.com/newsletter','카고레터🦍','https://page.stibee.com/subscriptions/65321','https://img.stibee.com/27336_1678424237.png','매주 수요일','BUSINESS_FINANCIAL_TECHNOLOGY'),
    (0,0,0,'2024-06-21 14:42:28.947895','2024-06-21 14:42:28.947895','메일 한 끼','newsletter@ottogi.co.kr','식문화 트렌드 또는 요리에 관심이 많다면! ✅F&B 종사자 또는 꿈나무라면! ✅오뚜기 소식을 빠르게 알고 싶은 찐팬이라면! ✅먹을 것에 진심인 여러분 모두 💛구독💛 하시고 메일 한 끼 받아보세요! 💌','https://page.stibee.com/archives/324391','메일한끼','https://page.stibee.com/subscriptions/324391','https://img.stibee.com/105327_1712627934.png','격주 수요일','FOOD'),
    (0,0,0,'2024-06-21 14:42:28.959553','2024-06-21 14:42:28.959553','셀렉트스타 AI','marketing@selectstar.ai','논문 리뷰 X 비즈니스 트렌드 X 기업 분석과 새로운 AI 뉴스와 트렌드, 좋은 정보가 가득한 셀렉트스타 뉴스레터를 받아보고 싶으시다면 지금 바로 구독해주세요! ','https://page.stibee.com/archives/212479','셀렉트스타','https://page.stibee.com/subscriptions/212479','https://img2.stibee.com/78707_2239355_1718783612373439584.png','비정기','IT_TECH');
INSERT INTO newsletter (has_confirmation_email,is_auto_subscribe_enabled,is_deleted,created_at,modified_at,name,email,description,main_link,nickname,subscribe_link,thumbnail_url,upload_days,category) VALUES
    (0,0,0,'2024-06-21 14:42:28.976087','2024-06-21 14:42:28.976087','프롬디자이너','from.designer@maily.so','함께 성장하는 UXUI 커뮤니티','https://maily.so/from.designer','from.designer','https://maily.so/from.designer','https://cdn.maily.so/maily96b1a25984762d02e6b644e5b3ebf3321617349721','비정기','DESIGN'),
    (0,1,0,'2024-06-21 14:42:28.987820','2024-06-21 14:42:28.987820','노폴레터','webmaster@notefolio.net','국내 최대 디자이너 플랫폼 노트폴리오(https://notefolio.net/)에서 발행하는 뉴스레터, 디자인 트렌드 & 소식 총정리 노폴레터! 👀 이번 주 놓치면 안 될 위클리 디자인 트렌드 👀 노트폴리오가 말아주는 고민상담, 인사이트 아티클 👀 작업에 200% 도움 될 신상 폰트 소개 👀 꼭 가봐야 할 전시, 팝업 소식 총정리 💌 매주 수요일, 노폴레터에서 모두 확인해 보세요!','https://notefolio.net/','노트폴리오','https://page.stibee.com/subscriptions/75270','https://img.stibee.com/2176121b-2d5a-4eb5-ac21-bc9a59c2eb3c.gif','매주 수요일','DESIGN'),
    (0,0,0,'2024-06-21 14:42:28.998322','2024-06-21 14:42:28.998322','MSV Impact Letter','hello@missionit.co','사회적 가치를 만나는 MSV 임팩트레터에서는 소셜임팩트 시리즈에서 다루고 있는 핵심적인 주제들과 관련하여 흥미로운 인사이트를 전달 드립니다. 핵심적인 키워드는 ''디자인의 사회적 가치Design for Social Value''와 ''포용적인 디자인Inclusive Design'' 그리고 ''접근성Accessibility'' 입니다.','https://www.magazinemsv.com/Letter','MSV','https://www.magazinemsv.com/newsletter','https://cdn.imweb.me/thumbnail/20230327/142a114118c84.png','일주일에 한번','DESIGN'),
    (0,0,0,'2024-06-21 14:42:29.010774','2024-06-21 14:42:29.010774','디자인 나침반','contact@designcompass.org','놓치면 안 될 디자인 소식 모아보기','https://designcompass.org/','Design Compass','https://designcompass.org/subscription-a-newsletter/','https://img.stibee.com/2f7174ac-cb17-40a6-ab6f-fcc97f05c81c.png','매주 화요일','DESIGN'),
    (0,1,0,'2024-06-21 14:42:29.020450','2024-06-21 14:42:29.020450','weekly D','hello@weeklyd.xyz','수요일 아침 8시, 읽어볼 만한 글을 모아서 보냅니다. 주로 디자인 관련 글을 수집합니다.','https://weeklyd.stibee.com/','weekly D','https://page.stibee.com/subscriptions/29178','https://img.stibee.com/2f7174ac-cb17-40a6-ab6f-fcc97f05c81c.png','매월 0~2회','DESIGN'),
    (0,0,0,'2024-06-21 14:42:29.031104','2024-06-21 14:42:29.031104','d design travel JEJU 편집부 노트','d.travel.jeju@gmail.com','일본 도쿄의 신도 편집장과 서울의 한국인 취재,편집 담당인 이지나(콜링 북스) 가 함께 만들어가는 이야기. 함께 듣고, 공감하며 또 서포트 해주세요!','https://d-travel.stibee.com/','이지나','https://d-travel.stibee.com/subscription/','https://img.stibee.com/cca59a31-ca1f-48d3-a918-eada0f06fa21.png','매월 1회','LOCAL_TRAVEL'),
    (0,1,0,'2024-06-21 14:42:29.042961','2024-06-21 14:42:29.042961','궁궐에서 온 편지','gungwalk@naver.com','📬 궁궐 산책과 우리 문화재를 좋아하는 분을 위한 뉴스레터입니다. 📍제철 궁궐의 모습 📍박물관 전시와 행사 소식 📍역사를 다룬 영화와 드라마, 책 이야기 📍문화재 관련 뉴스 📍<궁궐을 걷는 시간> 산책 안내 등 ✅ 매월 마지막주 금요일에 도착합니다 ✅ 구독 신청을 하시면 ''구독 확인 메일''이 발송됩니다. 메일의 구독 확인 버튼을 눌러주셔야 신청이 완료됩니다.','https://page.stibee.com/subscriptions/228566','<궁궐에서 온 편지> 뉴스레터','https://page.stibee.com/subscriptions/228566','https://img.stibee.com/3587517c-cb44-4f6a-a5e4-53be8c5d017b.png','매월 마지막 주 금요일','LOCAL_TRAVEL'),
    (0,0,0,'2024-06-21 14:42:29.052872','2024-06-21 14:42:29.052872','남해로On','namhaeron@namhae.go.kr','여행의 새로운 경험을 선사하는 남해의 알림.
남해의 찐 로컬들이 남해의 매력을 직접 알립니다.  남해에서 여행으로 먹고 살아가는 에피소드 또한 여러분에게 전합니다.
궁금하지만 멀기만 한 남해! 격주 금요일 여러분의 메일함으로 보내드려요.','https://namhaeon.stibee.com/','남해로온','https://namhaeon.stibee.com/subscription/','https://img.stibee.com/7789828c-1441-454f-ae02-7bbe0fa6e599.png','격주 금요일','LOCAL_TRAVEL'),
    (0,1,0,'2024-06-21 14:42:29.062103','2024-06-21 14:42:29.062103','지리산에서 온 편지','eum@jirisaneum.net','지리산에서 변화를 만드는 사람들을 만나는 뉴스레터
👩‍🌾 지리산에서 변화를 만드는 사람들들의 생생한 인터뷰
🌟 하마터면 모르고 지나갈 뻔한 지리산권 따끈한 뉴스
🌷 지리산권의 ''작은변화''를 위한 시민활동과 지원사업소식
🍝 발로 뛰는 활동가들의 지리산권 찐 맛집 추천 ','https://jirisanletter.stibee.com/','지리산이음','https://jirisanletter.stibee.com/subscription/','https://s3.ap-northeast-2.amazonaws.com/img.stibee.com/1461c4e4-d7e0-42e4-9986-939912e74179.png','격주 목요일','LOCAL_TRAVEL'),
    (0,0,0,'2024-06-21 14:42:29.075296','2024-06-21 14:42:29.075296','페이퍼로컬','paper@belocal.kr','읽다보면 궁금해지는 그 곳, 로컬!

재미있고 다양한 로컬의 소식을 담아
구독자님의 메일함에 배송해드려요.

페이퍼로컬에서만 알려 드리는 로컬 이슈부터
담당자 희희의 취향이 가득 담긴 로컬 브랜드 등
한 눈에 보는 로컬 소식지예요.

로컬이 궁금하시다면?
매주 금요일, 메일함에서 만나요!','https://paperlocal.stibee.com/','페이퍼로컬','https://paperlocal.stibee.com/subscription/','https://img.stibee.com/3129688d-87ef-4740-bd1e-1cbaf2867ead.png','매주 금요일','LOCAL_TRAVEL');
INSERT INTO newsletter (has_confirmation_email,is_auto_subscribe_enabled,is_deleted,created_at,modified_at,name,email,description,main_link,nickname,subscribe_link,thumbnail_url,upload_days,category) VALUES
                                                                                                                                                                                                            (0,1,0,'2024-06-21 14:42:29.085438','2024-06-21 14:42:29.085438','주말랭이','newsletter@funjoomal.com','주말이 더 즐거워지는 방법 매주 금요일, 놀 거리를 메일로 받아보세요!','https://onemoreweekend.co.kr/','주말랭이','https://1moreweekend.stibee.com/subscription/','https://cdn.litt.ly/images/8GfOl0A6uRuYyZJ1kAyY8gw8qPeKq9xa?s=360x360&f=webp','매주 금요일','LOCAL_TRAVEL'),
                                                                                                                                                                                                            (0,0,0,'2024-06-21 14:42:29.093849','2024-06-21 14:42:29.093849','빈둥레터','bindungletter-gmail.com@send.stibee.com','여행은 사랑하지만 어딜 가야 할지 모르겠는 우리 빈둥이! 해외여행 계획할 땐, 빈둥레터를 열어봐💌','https://bindungletter.stibee.com/','빈둥레터','https://bindungletter.stibee.com/subscription/','https://img.stibee.com/cd14986b-4d30-4410-b76d-ad64678fe700.jpg','격주 금요일','LOCAL_TRAVEL'),
                                                                                                                                                                                                            (0,1,0,'2024-06-21 14:42:29.104676','2024-06-21 14:42:29.104676','까탈로그','newsletter@the-edit.co.kr','어떤 제품이 새로 나왔는지,
어떤 물건을 사면 행복해지는지.
에디터들이 까탈스럽게 골라 메일로 배달해드립니다.
디에디트 채널에 업로드 되는 영상과 리뷰,
신제품 소식을 한 번에 감상하세요.','https://the-edit.co.kr/newsletter','까탈로그','https://the-edit.co.kr/newsletter','https://the-edit.co.kr/wp-content/themes/theedit-data/resources/images/ccatalog_v3/01_title.png','매주 금요일','TREND_LIFE'),
                                                                                                                                                                                                            (0,1,0,'2024-06-21 14:42:29.116433','2024-06-21 14:42:29.116433','머니네버슬립',' moneyneversleeptv@gmaiil.com','미국의 경제와 기업, 그리고 주식에 대해 이야기해요. 읽다 보면 어느새 지식이 수북하게 쌓여있을 거에요! 남들과는 다른 지식을 쌓고 싶다면, 머니네버슬립만한 게 없어요😎','https://money.stibee.com/','머니네버슬립 팀','https://page.stibee.com/subscriptions/106246','https://img.stibee.com/bb3c4810-a1a9-4c82-9bb8-990e3062d868.jpg','매주 월~금','BUSINESS_FINANCIAL_TECHNOLOGY'),
                                                                                                                                                                                                            (0,1,0,'2024-06-21 14:42:29.127687','2024-06-21 14:42:29.127687','빵슐랭 가이드','newsletter@breadguide.co.kr','요즘은 퀵서비스로 배송 오는 빵도, 택배로 시켜먹을 수 있는 지방 빵집도 있답니다. 다양한 수단으로 즐길 수 있는 빵들을 알려드려요.','https://breadguide.co.kr/','빵슐랭 가이드','https://page.stibee.com/subscriptions/69711','https://breadguide.co.kr/wp-content/uploads/elementor/thumbs/Logo_%ED%9D%B0_%ED%88%AC%EB%AA%85-okk93tvi06rvo0lfrmlfmynix0s5in6t3qxxpw7kgs.png','매주 수요일','FOOD'),
                                                                                                                                                                                                            (0,0,0,'2024-06-21 14:42:29.140442','2024-06-21 14:42:29.140442','캐릿','careet@careet.net','오늘… 혹시 ‘라떼’ 소리 들으셨나요?
요즘 친구들이 뭘 좋아하는지 당최 모르겠다고요?

걱정 마세요.당신이 놓친 트렌드,
캐릿이 빠르게 잡아서 알려드릴게요.
MZ세대와 세 발 더 가까워질 수 있는 인사이트를
매주 화요일 출근 전에 쏴드립니다. 렛츠 캐릿!','https://www.careet.net/','캐릿(Careet)','https://www.careet.net/Subscription','https://yt3.googleusercontent.com/Vf5_6SY0mdXKj8N_9pr6kQHWjJxmyhG_DrSruIKrnFnIUU3NpOwu-udfgsk-Eq2y_vEodNXZ=s900-c-k-c0x00ffffff-no-rj','매주 화요일','TREND_LIFE'),
                                                                                                                                                                                                            (0,0,0,'2024-06-21 14:42:29.151690','2024-06-21 14:42:29.151690','경제용 레터','edragon@sedaily.com
','경제용이란 경제나 투자를 처음 시작하려는 사람들이 경제에 쉽게 다가갈 수 있도록 브릿지 역할을 하는 미디어예요.','https://page.stibee.com/subscriptions/130181','팀 경제용','https://page.stibee.com/subscriptions/130181','https://img2.stibee.com/d2fad6b1-3012-4b5c-943a-3ca4c6a1b546.png','매주 금요일','ECONOMY'),
                                                                                                                                                                                                            (0,1,0,'2024-06-21 14:42:29.162287','2024-06-21 14:42:29.162287','데일리바이트','byteteam365@mydailybyte.com','✅어려운 용어 때문에 뉴스 읽기가 어려웠다면? 핵심적인 개념을 해설해요!
✅매일 꼭 알아야 할 비즈니스&경제 뉴스를 큐레이션하고, 요약해요!
✅이슈에 대한 큰 흐름을 알 수 있도록 쉽고 자세히 설명해요!
가장 쉬운 경제&비즈니스 뉴스 읽기, Daily Byte  ','https://www.mydailybyte.com/','
DAILY_BYTE ','https://page.stibee.com/subscriptions/81111','https://www.mydailybyte.com/logo.svg','매주 월~금 아침 6시','ECONOMY'),
                                                                                                                                                                                                            (0,1,0,'2024-06-21 14:42:29.174429','2024-06-21 14:42:29.174429','커피팟','good@coffeepot.me','쉽고 재밌는 해외 비즈니스 이야기
빅테크, AI, 리테일, 미디어, 에너지 그리고 거시경제 이슈까지. 세상을 바꾸는 비즈니스 이야기를 전합니다. 주요 이슈에 대한 맥락과 새로운 관점을 얻어가세요.','https://coffeepot.me/','COFFEEPOT','https://page.stibee.com/subscriptions/52057','https://img.stibee.com/235900f7-d070-456a-af19-cee7d314d794.jpg','매주 1회','BUSINESS_FINANCIAL_TECHNOLOGY');


# INSERT INTO newsletter (id, email, name, description, category, main_link, subscribe_link, thumbnail_url, is_deleted, upload_days) VALUES
#     (1, 'techweekly@example.com', 'Tech Weekly', 'Weekly newsletter about the latest in tech.', 'IT_TECH', 'http://techweekly.com', 'http://techweekly.com/subscription', 'http://techweekly.com/thumbnail.jpg', false, "매주 수요일"),
#     (2, 'healthinsights@example.com', 'Health Insights', 'Daily health tips and news.', 'ECONOMY', 'http://healthinsights.com', 'http://healthinsights.com/subscription', 'http://healthinsights.com/thumbnail.jpg', false, "매주 수요일"),
#     (3, 'financedaily@example.com', 'Finance Daily', 'Daily updates on the stock market.', 'BUSINESS_FINANCIAL_TECHNOLOGY', 'http://financedaily.com', 'http://financedaily.com/subscription', 'http://financedaily.com/thumbnail.jpg', false, "매주 수요일"),
#     (4, 'gadgetworld@example.com', 'Gadget World', 'Latest news on gadgets and electronics.', 'IT_TECH', 'http://gadgetworld.com', 'http://gadgetworld.com/subscription', 'http://gadgetworld.com/thumbnail.jpg', false, "매주 수요일"),
#     (5, 'healthwellness@example.com', 'Health and Wellness', 'Health and wellness tips.', 'ECONOMY', 'http://healthwellness.com', 'http://healthwellness.com/subscription', 'http://healthwellness.com/thumbnail.jpg', false, "매주 수요일"),
#     (6, 'investmentweekly@example.com', 'Investment Weekly', 'Weekly investment tips and news.', 'BUSINESS_FINANCIAL_TECHNOLOGY', 'http://investmentweekly.com', 'http://investmentweekly.com/subscription', 'http://investmentweekly.com/thumbnail.jpg', false, "매주 수요일"),
#     (7, 'techinnovations@example.com', 'Tech Innovations', 'Innovations and breakthroughs in tech.', 'IT_TECH', 'http://techinnovations.com', 'http://techinnovations.com/subscription', 'http://techinnovations.com/thumbnail.jpg', false, "매주 수요일"),
#     (8, 'fitnessdaily@example.com', 'Fitness Daily', 'Daily fitness routines and tips.', 'ECONOMY', 'http://fitnessdaily.com', 'http://fitnessdaily.com/subscription', 'http://fitnessdaily.com/thumbnail.jpg', false, "매주 수요일"),
#     (9, 'marketwatch@example.com', 'Market Watch', 'Daily market insights and analysis.', 'BUSINESS_FINANCIAL_TECHNOLOGY', 'http://marketwatch.com', 'http://marketwatch.com/subscription', 'http://marketwatch.com/thumbnail.jpg', false, "매주 수요일"),
#     (10, 'techtoday@example.com', 'Tech Today', 'Today’s top tech news.', 'IT_TECH', 'http://techtoday.com', 'http://techtoday.com/subscription', 'http://techtoday.com/thumbnail.jpg', false, "매주 수요일"),
#     (11, 'nutritionguide@example.com', 'Nutrition Guide', 'Guides to better nutrition.', 'ECONOMY', 'http://nutritionguide.com', 'http://nutritionguide.com/subscription', 'http://nutritionguide.com/thumbnail.jpg', false, "매주 수요일"),
#     (12, 'cryptocorner@example.com', 'Crypto Corner', 'All about cryptocurrency.', 'BUSINESS_FINANCIAL_TECHNOLOGY', 'http://cryptocorner.com', 'http://cryptocorner.com/subscription', 'http://cryptocorner.com/thumbnail.jpg', false, "매주 수요일"),
#     (13, 'futuretech@example.com', 'Future Tech', 'Future tech trends and predictions.', 'IT_TECH', 'http://futuretech.com', 'http://futuretech.com/subscription', 'http://futuretech.com/thumbnail.jpg', false, "매주 수요일"),
#     (14, 'mentalhealth@example.com', 'Mental Health', 'Mental health awareness and tips.', 'ECONOMY', 'http://mentalhealthmatters.com', 'http://mentalhealthmatters.com/subscription', 'http://mentalhealthmatters.com/thumbnail.jpg', false, "매주 수요일"),
#     (15, 'financeinsights@example.com', 'Finance Insights', 'Insights into financial markets.', 'BUSINESS_FINANCIAL_TECHNOLOGY', 'http://financeinsights.com', 'http://financeinsights.com/subscription', 'http://financeinsights.com/thumbnail.jpg', false, "매주 수요일"),
#     (16, 'techtrends@example.com', 'Tech Trends', 'Current trends in technology.', 'IT_TECH', 'http://techtrends.com', 'http://techtrends.com/subscription', 'http://techtrends.com/thumbnail.jpg', false, "매주 수요일"),
#     (17, 'healthyliving@example.com', 'Healthy Living', 'Tips for a healthier lifestyle.', 'ECONOMY', 'http://healthyliving.com', 'http://healthyliving.com/subscription', 'http://healthyliving.com/thumbnail.jpg', false, "매주 수요일"),
#     (18, 'stocksandbonds@example.com', 'Stocks and Bonds', 'News on stocks and bonds.', 'BUSINESS_FINANCIAL_TECHNOLOGY', 'http://stocksandbonds.com', 'http://stocksandbonds.com/subscription', 'http://stocksandbonds.com/thumbnail.jpg', false, "매주 수요일"),
#     (19, 'techreview@example.com', 'Tech Review', 'In-depth tech reviews.', 'IT_TECH', 'http://techreview.com', 'http://techreview.com/subscription', 'http://techreview.com/thumbnail.jpg', false, "매주 수요일"),
#     (20, 'medicalnewstoday@example.com', 'Medical News Today', 'Latest in medical news.', 'ECONOMY', 'http://medicalnewstoday.com', 'http://medicalnewstoday.com/subscription', 'http://medicalnewstoday.com/thumbnail.jpg', false, "매주 수요일"),
#     (21, 'financeweekly@example.com', 'Finance Weekly', 'Weekly financial news and tips.', 'BUSINESS_FINANCIAL_TECHNOLOGY', 'http://financeweekly.com', 'http://financeweekly.com/subscription', 'http://financeweekly.com/thumbnail.jpg', false, "매주 수요일"),
#     (22, 'gadgetupdates@example.com', 'Gadget Updates', 'Updates on the latest gadgets.', 'IT_TECH', 'http://gadgetupdates.com', 'http://gadgetupdates.com/subscription', 'http://gadgetupdates.com/thumbnail.jpg', false, "매주 수요일"),
#     (23, 'healthdigest@example.com', 'Health Digest', 'Daily health news and articles.', 'ECONOMY', 'http://healthdigest.com', 'http://healthdigest.com/subscription', 'http://healthdigest.com/thumbnail.jpg', false, "매주 수요일"),
#     (24, 'cryptoweekly@example.com', 'Crypto Weekly', 'Weekly cryptocurrency news.', 'BUSINESS_FINANCIAL_TECHNOLOGY', 'http://cryptoweekly.com', 'http://cryptoweekly.com/subscription', 'http://cryptoweekly.com/thumbnail.jpg', false, "매주 수요일"),
#     (25, 'technews@example.com', 'Tech News', 'Daily tech news updates.', 'IT_TECH', 'http://technews.com', 'http://technews.com/subscription', 'http://technews.com/thumbnail.jpg', false, "매주 수요일"),
#     (26, 'wellnessweekly@example.com', 'Wellness Weekly', 'Weekly wellness tips.', 'ECONOMY', 'http://wellnessweekly.com', 'http://wellnessweekly.com/subscription', 'http://wellnessweekly.com/thumbnail.jpg', false, "매주 수요일"),
#     (27, 'investmentdaily@example.com', 'Investment Daily', 'Daily investment tips and news.', 'BUSINESS_FINANCIAL_TECHNOLOGY', 'http://investmentdaily.com', 'http://investmentdaily.com/subscription', 'http://investmentdaily.com/thumbnail.jpg', false, "매주 수요일"),
#     (28, 'innovationinsights@example.com', 'Innovation Insights', 'Insights into the latest tech innovations.', 'IT_TECH', 'http://innovationinsights.com', 'http://innovationinsights.com/subscription', 'http://innovationinsights.com/thumbnail.jpg', false, "매주 수요일"),
#     (29, 'healthyhabits@example.com', 'Healthy Habits', 'Habits for a healthier life.', 'ECONOMY', 'http://healthyhabits.com', 'http://healthyhabits.com/subscription', 'http://healthyhabits.com/thumbnail.jpg', false, "매주 수요일"),
#     (30, 'financialtimes@example.com', 'Financial Times', 'Financial news and market analysis.', 'BUSINESS_FINANCIAL_TECHNOLOGY', 'http://financialtimes.com', 'http://financialtimes.com/subscription', 'http://financialtimes.com/thumbnail.jpg', false, "매주 수요일");



INSERT INTO article (id, newsletter_email, user_email, title, thumbnail_url, content_url, reading_time, received_at, content_summary, newsletter_nickname, is_deleted, created_at) VALUES
     (1, 'techweekly@example.com', 'test@gmail.com', '테스트 아티클1 2024', 'http://techweekly.com/thumbnails/trends2024.jpg', 'http://techweekly.com/articles/trends2024', 5, '2024-05-01', 'content test1', "a",  false, '2024-05-01'),
     (2, 'techweekly@example.com', 'test@gmail.com', '테스트 아티클2 2022', 'http://tecsdffe.com/thumbnails/trends2024.jpg', 'http://techweekly.com/articles/trends2022', 5, '2024-04-05', 'content test1' , "b", false, '2024-05-02'),
     (3, 'healthinsights@example.com', 'test@gmail.com', '테스트 아티클3 Living Tips', 'http://healthinsights.com/thumbnails/healthyliving.jpg', 'http://healthinsights.com/articles/healthyliving', 3, '2024-05-02', 'content test2', "c", false, '2024-05-01'),
     (4, 'financedaily@example.com', 'user2@example.com', 'Stock Market Analysis', 'http://financedaily.com/thumbnails/stockmarket.jpg', 'http://financedaily.com/articles/stockmarket', 7, '2024-05-03', 'content test3', "d" , false, '2024-05-01'),
     (5, 'gadgetworld@example.com', 'user4@example.com', 'Latest Gadgets', 'http://gadgetworld.com/thumbnails/latestgadgets.jpg', 'http://gadgetworld.com/articles/latestgadgets', 2, '2024-01-01', 'content test4' ,"e", false, '2024-05-01'),
     (6, 'healthwellness@example.com', 'user5@example.com', 'Wellness Tips', 'http://healthwellness.com/thumbnails/wellnesstips.jpg', 'http://healthwellness.com/articles/wellnesstips', 4, '2024-08-01', 'content test5',"f", false, '2024-05-01'),
     (7, 'healthwellness@example.com', 'user6@example.com', 'Test Article', 'http://test.com/thumbnails/wellnesstips.jpg', 'http://test.com/articles/wellnesstips', 10, '2024-07-01', 'content test6', "g", false, '2024-05-01'),
    (12, 'healthwellness@example.com', 'user3@gmail.com', '테스트 1번 Article', 'http://test.com/thumbnails/wellnesstips.jpg', 'http://test.com/articles/wellnesstips', 12, '2024-07-01', 'content test6', "g", false, '2024-05-02'),
    (13, 'healthwellness@example.com', 'user3@gmail.com', '테스트 2번 Article', 'http://test.com/thumbnails/wellnesstips.jpg', 'http://test.com/articles/wellnesstips', 10, '2024-07-01', 'content test6', "g", false, '2024-05-02');



-- Readbox 데이터 삽입
INSERT INTO read_box (user_email, article_id, read_percentage) VALUES
   ("test@gmail.com", 1, 50),
   ("test@gmail.com", 2, 60),
   ("test@gmail.com", 3, 70);

-- User 데이터 삽입
INSERT  INTO users (email,profile_img,background_img,created_at,update_at,role) VALUES
    ("user1@gmail.com","profileImgUrl1","backgroundImgUrl1","2024-03-10","2024-05-10","USER"),
    ("user2@gmail.com","profileImgUrl2","backgroundImgUrl2","2024-04-17","2024-04-29","USER"),
    ("user3@gmail.com","profileImgUrl3","backgroundImgUrl3","2024-05-01","2024-05-02","USER");

INSERT INTO user_detail (email,nick_Name,birth_date,user_expiration,occupation,created_at,modified_at) VALUES
    ("user1@gmail.com","KIM","1998-02-19","2025-05-10","OFFICE","2024-03-10","2024-03-10"),
    ("user2@gmail.com","RYU","1999-05-22","2024-10-29","SELLING","2024-04-17","2024-04-17"),
    ("user3@gmail.com","KANG","1996-11-18","2034-05-02","SERVICE","2024-05-01","2024-05-01");

# -- Interest 데이터 삽입
# INSERT INTO interests (email, interests) VALUES
#     ("user1@gmail.com","LOCAL_TRAVEL"),
#     ("user1@gmail.com","TREND_LIFE"),
#     ("user1@gmail.com","IT_TECH"),
#     ("user2@gmail.com","DESIGN"),
#     ("user2@gmail.com","CULTURE_ART"),
#     ("user2@gmail.com","LIVING_INTERIOR"),
#     ("user3@gmail.com","HEALTH_MEDICINE"),
#     ("user3@gmail.com","LOCAL_TRAVEL"),
#     ("user3@gmail.com","IT_TECH");

-- Readbox 데이터 삽입 (완독한 경우 추가)
# INSERT INTO read_box (user_email,read_date,article_id, percentage) VALUES
#     ("user1@gmail.com","2024-04-20", 1, 100),
#     ("user1@gmail.com","2024-04-20", 2, 100),
#     ("user1@gmail.com",NULL, 3, 70),
#     ("user1@gmail.com",NULL, 4, 70),
#     ("user1@gmail.com","2024-04-20", 5, 100),
#     ("user1@gmail.com","2024-04-29", 6, 100),
#     ("user2@gmail.com","2024-05-02", 2, 100),
#     ("user2@gmail.com","2024-05-01", 4, 100),
#     ("user2@gmail.com",NULL, 5, 70),
#     ("user2@gmail.com",NULL, 6, 50);

-- 마이페이지 최근 읽은 아티클 조회 Test용

INSERT INTO article (id, newsletter_email, user_email, title, thumbnail_url, content_url, reading_time, received_at, content_summary, newsletter_nickname, is_deleted, created_at) VALUES
    (8, 'techweekly@example.com', 'user3@gmail.com', 'Tech Trends 2024', 'http://techweekly.com/thumbnails/trends2024.jpg', 'http://techweekly.com/articles/trends2024', 5, '2024-06-22', 'content test1', "a",  false, '2024-06-22'),
    (9, 'techweekly@example.com', 'user2@gmail.com', 'Tech Trends 2022', 'http://tecsdffe.com/thumbnails/trends2024.jpg', 'http://techweekly.com/articles/trends2022', 5, '2024-06-16', 'content test1' , "b", false, '2024-06-16'),
    (10, 'techweekly@example.com', 'user2@gmail.com', 'Healthy Living Tips', 'http://healthinsights.com/thumbnails/healthyliving.jpg', 'http://healthinsights.com/articles/healthyliving', 3, '2024-06-15', 'content test2', "c", false, '2024-06-05'),
    (11, 'financedaily@example.com', 'user2@gmail.com', 'Stock Market Analysis', 'http://financedaily.com/thumbnails/stockmarket.jpg', 'http://financedaily.com/articles/stockmarket', 7, '2024-06-17', 'content test3', "d" , false, '2024-05-01'),
    (15, 'techweekly@example.com', 'test@gmail.com', '테스트 아티클 15Tech Trends 2024', 'http://techweekly.com/thumbnails/trends2024.jpg', 'http://techweekly.com/articles/trends2024', 5, '2024-05-28', 'content test1', "a",  false, '2024-05-01');

INSERT INTO read_box (user_email,modified_at,article_id, read_percentage) VALUES
    ("user3@gmail.com","2024-04-20", 8, 100),
    ("user2@gmail.com","2024-04-20", 9, 70),
    ("user2@gmail.com","2024-04-20", 10, 100),
    ("user4@gmail.com","2024-04-20", 11, 100);

INSERT INTO subscription (id, newsletter_id,user_email) VALUES
    (1, 1, "user1@gmail.com"),
    (2, 2, "user2@gmail.com"),
    (3, 3, "user3@gmail.com");



