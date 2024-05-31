SET FOREIGN_KEY_CHECKS=0;
TRUNCATE TABLE article;
TRUNCATE TABLE admin_article;
TRUNCATE TABLE newsletter;
TRUNCATE TABLE read_box;
TRUNCATE TABLE users;
SET FOREIGN_KEY_CHECKS=1;

INSERT INTO newsletter (id, email, name, description, category, main_link, subscribe_link, thumbnail_url, is_deleted, upload_days) VALUES
    (1, 'techweekly@example.com', 'Tech Weekly', 'Weekly newsletter about the latest in tech.', 'IT_TECH', 'http://techweekly.com', 'http://techweekly.com/subscribe', 'http://techweekly.com/thumbnail.jpg', false, "매주 수요일"),
    (2, 'healthinsights@example.com', 'Health Insights', 'Daily health tips and news.', 'HEALTH_MEDICINE', 'http://healthinsights.com', 'http://healthinsights.com/subscribe', 'http://healthinsights.com/thumbnail.jpg', false, "매주 수요일"),
    (3, 'financedaily@example.com', 'Finance Daily', 'Daily updates on the stock market.', 'BUSINESS_FINANCIAL_TECHNOLOGY', 'http://financedaily.com', 'http://financedaily.com/subscribe', 'http://financedaily.com/thumbnail.jpg', false, "매주 수요일"),
    (4, 'gadgetworld@example.com', 'Gadget World', 'Latest news on gadgets and electronics.', 'IT_TECH', 'http://gadgetworld.com', 'http://gadgetworld.com/subscribe', 'http://gadgetworld.com/thumbnail.jpg', false, "매주 수요일"),
    (5, 'healthwellness@example.com', 'Health and Wellness', 'Health and wellness tips.', 'HEALTH_MEDICINE', 'http://healthwellness.com', 'http://healthwellness.com/subscribe', 'http://healthwellness.com/thumbnail.jpg', false, "매주 수요일"),
    (6, 'investmentweekly@example.com', 'Investment Weekly', 'Weekly investment tips and news.', 'BUSINESS_FINANCIAL_TECHNOLOGY', 'http://investmentweekly.com', 'http://investmentweekly.com/subscribe', 'http://investmentweekly.com/thumbnail.jpg', false, "매주 수요일"),
    (7, 'techinnovations@example.com', 'Tech Innovations', 'Innovations and breakthroughs in tech.', 'IT_TECH', 'http://techinnovations.com', 'http://techinnovations.com/subscribe', 'http://techinnovations.com/thumbnail.jpg', false, "매주 수요일"),
    (8, 'fitnessdaily@example.com', 'Fitness Daily', 'Daily fitness routines and tips.', 'HEALTH_MEDICINE', 'http://fitnessdaily.com', 'http://fitnessdaily.com/subscribe', 'http://fitnessdaily.com/thumbnail.jpg', false, "매주 수요일"),
    (9, 'marketwatch@example.com', 'Market Watch', 'Daily market insights and analysis.', 'BUSINESS_FINANCIAL_TECHNOLOGY', 'http://marketwatch.com', 'http://marketwatch.com/subscribe', 'http://marketwatch.com/thumbnail.jpg', false, "매주 수요일"),
    (10, 'techtoday@example.com', 'Tech Today', 'Today’s top tech news.', 'IT_TECH', 'http://techtoday.com', 'http://techtoday.com/subscribe', 'http://techtoday.com/thumbnail.jpg', false, "매주 수요일"),
    (11, 'nutritionguide@example.com', 'Nutrition Guide', 'Guides to better nutrition.', 'HEALTH_MEDICINE', 'http://nutritionguide.com', 'http://nutritionguide.com/subscribe', 'http://nutritionguide.com/thumbnail.jpg', false, "매주 수요일"),
    (12, 'cryptocorner@example.com', 'Crypto Corner', 'All about cryptocurrency.', 'BUSINESS_FINANCIAL_TECHNOLOGY', 'http://cryptocorner.com', 'http://cryptocorner.com/subscribe', 'http://cryptocorner.com/thumbnail.jpg', false, "매주 수요일"),
    (13, 'futuretech@example.com', 'Future Tech', 'Future tech trends and predictions.', 'IT_TECH', 'http://futuretech.com', 'http://futuretech.com/subscribe', 'http://futuretech.com/thumbnail.jpg', false, "매주 수요일"),
    (14, 'mentalhealth@example.com', 'Mental Health', 'Mental health awareness and tips.', 'HEALTH_MEDICINE', 'http://mentalhealthmatters.com', 'http://mentalhealthmatters.com/subscribe', 'http://mentalhealthmatters.com/thumbnail.jpg', false, "매주 수요일"),
    (15, 'financeinsights@example.com', 'Finance Insights', 'Insights into financial markets.', 'BUSINESS_FINANCIAL_TECHNOLOGY', 'http://financeinsights.com', 'http://financeinsights.com/subscribe', 'http://financeinsights.com/thumbnail.jpg', false, "매주 수요일"),
    (16, 'techtrends@example.com', 'Tech Trends', 'Current trends in technology.', 'IT_TECH', 'http://techtrends.com', 'http://techtrends.com/subscribe', 'http://techtrends.com/thumbnail.jpg', false, "매주 수요일"),
    (17, 'healthyliving@example.com', 'Healthy Living', 'Tips for a healthier lifestyle.', 'HEALTH_MEDICINE', 'http://healthyliving.com', 'http://healthyliving.com/subscribe', 'http://healthyliving.com/thumbnail.jpg', false, "매주 수요일"),
    (18, 'stocksandbonds@example.com', 'Stocks and Bonds', 'News on stocks and bonds.', 'BUSINESS_FINANCIAL_TECHNOLOGY', 'http://stocksandbonds.com', 'http://stocksandbonds.com/subscribe', 'http://stocksandbonds.com/thumbnail.jpg', false, "매주 수요일"),
    (19, 'techreview@example.com', 'Tech Review', 'In-depth tech reviews.', 'IT_TECH', 'http://techreview.com', 'http://techreview.com/subscribe', 'http://techreview.com/thumbnail.jpg', false, "매주 수요일"),
    (20, 'medicalnewstoday@example.com', 'Medical News Today', 'Latest in medical news.', 'HEALTH_MEDICINE', 'http://medicalnewstoday.com', 'http://medicalnewstoday.com/subscribe', 'http://medicalnewstoday.com/thumbnail.jpg', false, "매주 수요일"),
    (21, 'financeweekly@example.com', 'Finance Weekly', 'Weekly financial news and tips.', 'BUSINESS_FINANCIAL_TECHNOLOGY', 'http://financeweekly.com', 'http://financeweekly.com/subscribe', 'http://financeweekly.com/thumbnail.jpg', false, "매주 수요일"),
    (22, 'gadgetupdates@example.com', 'Gadget Updates', 'Updates on the latest gadgets.', 'IT_TECH', 'http://gadgetupdates.com', 'http://gadgetupdates.com/subscribe', 'http://gadgetupdates.com/thumbnail.jpg', false, "매주 수요일"),
    (23, 'healthdigest@example.com', 'Health Digest', 'Daily health news and articles.', 'HEALTH_MEDICINE', 'http://healthdigest.com', 'http://healthdigest.com/subscribe', 'http://healthdigest.com/thumbnail.jpg', false, "매주 수요일"),
    (24, 'cryptoweekly@example.com', 'Crypto Weekly', 'Weekly cryptocurrency news.', 'BUSINESS_FINANCIAL_TECHNOLOGY', 'http://cryptoweekly.com', 'http://cryptoweekly.com/subscribe', 'http://cryptoweekly.com/thumbnail.jpg', false, "매주 수요일"),
    (25, 'technews@example.com', 'Tech News', 'Daily tech news updates.', 'IT_TECH', 'http://technews.com', 'http://technews.com/subscribe', 'http://technews.com/thumbnail.jpg', false, "매주 수요일"),
    (26, 'wellnessweekly@example.com', 'Wellness Weekly', 'Weekly wellness tips.', 'HEALTH_MEDICINE', 'http://wellnessweekly.com', 'http://wellnessweekly.com/subscribe', 'http://wellnessweekly.com/thumbnail.jpg', false, "매주 수요일"),
    (27, 'investmentdaily@example.com', 'Investment Daily', 'Daily investment tips and news.', 'BUSINESS_FINANCIAL_TECHNOLOGY', 'http://investmentdaily.com', 'http://investmentdaily.com/subscribe', 'http://investmentdaily.com/thumbnail.jpg', false, "매주 수요일"),
    (28, 'innovationinsights@example.com', 'Innovation Insights', 'Insights into the latest tech innovations.', 'IT_TECH', 'http://innovationinsights.com', 'http://innovationinsights.com/subscribe', 'http://innovationinsights.com/thumbnail.jpg', false, "매주 수요일"),
    (29, 'healthyhabits@example.com', 'Healthy Habits', 'Habits for a healthier life.', 'HEALTH_MEDICINE', 'http://healthyhabits.com', 'http://healthyhabits.com/subscribe', 'http://healthyhabits.com/thumbnail.jpg', false, "매주 수요일"),
    (30, 'financialtimes@example.com', 'Financial Times', 'Financial news and market analysis.', 'BUSINESS_FINANCIAL_TECHNOLOGY', 'http://financialtimes.com', 'http://financialtimes.com/subscribe', 'http://financialtimes.com/thumbnail.jpg', false, "매주 수요일");



INSERT INTO article (id, newsletter_email, user_email, title, thumbnail_url, content_url, reading_time, received_at, content_summary, newsletter_nickname, is_deleted, create_at) VALUES
     (1, 'techweekly@example.com', 'test@gmail.com', 'Tech Trends 2024', 'http://techweekly.com/thumbnails/trends2024.jpg', 'http://techweekly.com/articles/trends2024', 5, '2024-05-01', 'content test1', "a",  false, '2024-05-01'),
     (2, 'techweekly@example.com', 'test@gmail.com', 'Tech Trends 2022', 'http://tecsdffe.com/thumbnails/trends2024.jpg', 'http://techweekly.com/articles/trends2022', 5, '2024-04-05', 'content test1' , "b", false, '2024-05-02'),
     (3, 'healthinsights@example.com', 'test@gmail.com', 'Healthy Living Tips', 'http://healthinsights.com/thumbnails/healthyliving.jpg', 'http://healthinsights.com/articles/healthyliving', 3, '2024-05-02', 'content test2', "c", false, '2024-05-01'),
     (4, 'financedaily@example.com', 'user3@example.com', 'Stock Market Analysis', 'http://financedaily.com/thumbnails/stockmarket.jpg', 'http://financedaily.com/articles/stockmarket', 7, '2024-05-03', 'content test3', "d" , false, '2024-05-01'),
     (5, 'gadgetworld@example.com', 'user4@example.com', 'Latest Gadgets', 'http://gadgetworld.com/thumbnails/latestgadgets.jpg', 'http://gadgetworld.com/articles/latestgadgets', 2, '2024-01-01', 'content test4' ,"e", false, '2024-05-01'),
     (6, 'healthwellness@example.com', 'user5@example.com', 'Wellness Tips', 'http://healthwellness.com/thumbnails/wellnesstips.jpg', 'http://healthwellness.com/articles/wellnesstips', 4, '2024-08-01', 'content test5',"f", false, '2024-05-01'),
     (7, 'healthwellness@example.com', 'user6@example.com', 'Test Article', 'http://test.com/thumbnails/wellnesstips.jpg', 'http://test.com/articles/wellnesstips', 10, '2024-07-01', 'content test6', "g", false, '2024-05-01');

INSERT INTO admin_article (id, newsletter_email, user_email, title, thumbnail_url, content_url, reading_time, received_at, content_summary, newsletter_nickname, is_deleted, create_at) VALUES
     (1, 'techweekly@example.com', 'user1@example.com', 'Tech Trends 2024', 'http://techweekly.com/thumbnails/trends2024.jpg', 'http://techweekly.com/articles/trends2024', 5, '2024-05-01', 'content test1',  "a",false, '2024-05-01'),
     (2, 'techweekly@example.com', 'userTest@example.com', 'Tech Trends 2022', 'http://tecsdffe.com/thumbnails/trends2024.jpg', 'http://techweekly.com/articles/trends2022', 5, '2024-04-05', 'content test1', "b" , false, '2024-05-02'),
     (3, 'healthinsights@example.com', 'user2@example.com', 'Healthy Living Tips', 'http://healthinsights.com/thumbnails/healthyliving.jpg', 'http://healthinsights.com/articles/healthyliving', 3, '2024-05-02', 'content test2', "c", false, '2024-05-01'),
     (4, 'financedaily@example.com', 'user3@example.com', 'Stock Market Analysis', 'http://financedaily.com/thumbnails/stockmarket.jpg', 'http://financedaily.com/articles/stockmarket', 7, '2024-05-03', 'content test3',  "d", false, '2024-05-01'),
     (5, 'gadgetworld@example.com', 'user4@example.com', 'Latest Gadgets', 'http://gadgetworld.com/thumbnails/latestgadgets.jpg', 'http://gadgetworld.com/articles/latestgadgets', 2, '2024-01-01', 'content test4',  "e", false, '2024-05-01'),
     (6, 'healthwellness@example.com', 'user5@example.com', 'Wellness Tips', 'http://healthwellness.com/thumbnails/wellnesstips.jpg', 'http://healthwellness.com/articles/wellnesstips', 4, '2024-08-01', 'content test5',"f", false, '2024-05-01'),
     (7, 'healthwellness@example.com', 'user6@example.com', 'Test Article', 'http://test.com/thumbnails/wellnesstips.jpg', 'http://test.com/articles/wellnesstips', 10, '2024-07-01', 'content test6', "g",false, '2024-05-01');


-- Readbox 데이터 삽입
INSERT INTO read_box (user_email, article_id, percentage) VALUES
   ("test@gmail.com", 4, 50),
   ("test@gmail.com", 5, 60),
   ("test@gmail.com", 6, 70),
   ("test@gmail.com", 7, 80),
   ("test@gmail.com", 8, 77),
   ("test@gmail.com", 9, 66);

-- User 데이터 삽입
INSERT  INTO users (email,profile_img,background_img,created_at,update_at,role,nick_Name,birth_date,user_expiration,occupation) VALUES
    ("user1@gmail.com","profileImgUrl1","backgroundImgUrl1","2024-03-10","2024-05-10","USER","KIM","1998-02-19","2025-05-10","OFFICE"),
    ("user2@gmail.com","profileImgUrl2","backgroundImgUrl2","2024-04-17","2024-04-29","USER","RYU","1999-05-22","2024-10-29","SELLING"),
    ("user3@gmail.com","profileImgUrl3","backgroundImgUrl3","2024-05-01","2024-05-02","USER","KANG","1996-11-18","2034-05-02","SERVICE");

-- Interest 데이터 삽입
INSERT INTO interests (email, interests) VALUES
    ("user1@gmail.com","LOCAL_TRAVEL"),
    ("user1@gmail.com","TREND_LIFE"),
    ("user1@gmail.com","IT_TECH"),
    ("user2@gmail.com","DESIGN"),
    ("user2@gmail.com","CULTURE_ART"),
    ("user2@gmail.com","LIVING_INTERIOR"),
    ("user3@gmail.com","HEALTH_MEDICINE"),
    ("user3@gmail.com","LOCAL_TRAVEL"),
    ("user3@gmail.com","IT_TECH");

-- Readbox 데이터 삽입 (완독한 경우 추가)
INSERT INTO read_box (user_email,read_date,article_id, percentage) VALUES
    ("user1@gmail.com","2024-04-20", 1, 100),
    ("user1@gmail.com","2024-04-20", 2, 100),
    ("user1@gmail.com",NULL, 3, 70),
    ("user1@gmail.com",NULL, 4, 70),
    ("user1@gmail.com","2024-04-20", 5, 100),
    ("user1@gmail.com","2024-04-29", 6, 100),
    ("user2@gmail.com","2024-05-02", 2, 100),
    ("user2@gmail.com","2024-05-01", 4, 100),
    ("user2@gmail.com",NULL, 5, 70),
    ("user2@gmail.com",NULL, 6, 50);

-- 마이페이지 최근 읽은 아티클 조회 Test용

INSERT INTO article (id, newsletter_email, user_email, title, thumbnail_url, content_url, reading_time, received_at, content_summary, newsletter_nickname, is_deleted, create_at) VALUES
    (8, 'techweekly@example.com', 'user3@gmail.com', 'Tech Trends 2024', 'http://techweekly.com/thumbnails/trends2024.jpg', 'http://techweekly.com/articles/trends2024', 5, '2024-05-28', 'content test1', "a",  false, '2024-05-01'),
    (9, 'techweekly@example.com', 'user3@gmail.com', 'Tech Trends 2022', 'http://tecsdffe.com/thumbnails/trends2024.jpg', 'http://techweekly.com/articles/trends2022', 5, '2024-05-28', 'content test1' , "b", false, '2024-05-02'),
    (10, 'healthinsights@example.com', 'user3@gmail.com', 'Healthy Living Tips', 'http://healthinsights.com/thumbnails/healthyliving.jpg', 'http://healthinsights.com/articles/healthyliving', 3, '2024-05-28', 'content test2', "c", false, '2024-05-01'),
    (11, 'financedaily@example.com', 'user4@gmail.com', 'Stock Market Analysis', 'http://financedaily.com/thumbnails/stockmarket.jpg', 'http://financedaily.com/articles/stockmarket', 7, '2024-05-28', 'content test3', "d" , false, '2024-05-01');

INSERT INTO read_box (user_email,update_at,article_id, percentage) VALUES
    ("user3@gmail.com","2024-04-20", 8, 50),
    ("user3@gmail.com","2024-04-20", 9, 70),
    ("user3@gmail.com","2024-04-20", 10, 100),
    ("user4@gmail.com","2024-04-20", 11, 100);

INSERT INTO subscribe (id,user_email) VALUES
    (1,"user1@gmail.com"),
    (2,"user2@gmail.com"),
    (3,"user3@gmail.com");

UPDATE newsletter SET subscribe_id = 1 WHERE id = 1;
UPDATE newsletter SET subscribe_id = 1 WHERE id = 2;
UPDATE newsletter SET subscribe_id = 2 WHERE id = 1;
UPDATE newsletter SET subscribe_id = 2 WHERE id = 2;
UPDATE newsletter SET subscribe_id = 2 WHERE id = 3;
UPDATE newsletter SET subscribe_id = 2 WHERE id = 4;
UPDATE newsletter SET subscribe_id = 3 WHERE id = 1;
UPDATE newsletter SET subscribe_id = 3 WHERE id = 2;
UPDATE newsletter SET subscribe_id = 3 WHERE id = 3;
UPDATE newsletter SET subscribe_id = 3 WHERE id = 4;
UPDATE newsletter SET subscribe_id = 3 WHERE id = 5;
UPDATE newsletter SET subscribe_id = 3 WHERE id = 6;



