

-- 기존 데이터를 삭제합니다.
DELETE FROM bookmark_article_ids;
DELETE FROM bookmark;
DELETE FROM newsletter;
DELETE FROM article;

-- 테스트 데이터를 삽입합니다.

INSERT INTO newsletter (id, email, name, description, category, main_link, subscribe_link, thumbnail_url, is_deleted, upload_days, is_auto_subscribe_enabled, has_confirmation_email, nickname) VALUES
    (1, 'techweekly@example.com', 'Tech Weekly', 'Weekly newsletter about the latest in tech.', 'IT_TECH', 'http://techweekly.com', 'http://techweekly.com/subscription', 'http://techweekly.com/thumbnail.jpg', false, '매주 수요일', false, false, NULL),
    (2, 'healthinsights@example.com', 'Health Insights', 'Daily health tips and news.', 'ECONOMY', 'http://healthinsights.com', 'http://healthinsights.com/subscription', 'http://healthinsights.com/thumbnail.jpg', false, '매주 수요일', false, false, NULL),
    (3, 'financedaily@example.com', 'Finance Daily', 'Daily updates on the stock market.', 'BUSINESS_FINANCIAL_TECHNOLOGY', 'http://financedaily.com', 'http://financedaily.com/subscription', 'http://financedaily.com/thumbnail.jpg', false, '매주 수요일', false, false, NULL),
    (4, 'gadgetworld@example.com', 'Gadget World', 'Latest news on gadgets and electronics.', 'IT_TECH', 'http://gadgetworld.com', 'http://gadgetworld.com/subscription', 'http://gadgetworld.com/thumbnail.jpg', false, '매주 수요일', false, false, NULL),
    (5, 'healthwellness@example.com', 'Health and Wellness', 'Health and wellness tips.', 'ECONOMY', 'http://healthwellness.com', 'http://healthwellness.com/subscription', 'http://healthwellness.com/thumbnail.jpg', false, '매주 수요일', false, false, NULL),
    (6, 'investmentweekly@example.com', 'Investment Weekly', 'Weekly investment tips and news.', 'BUSINESS_FINANCIAL_TECHNOLOGY', 'http://investmentweekly.com', 'http://investmentweekly.com/subscription', 'http://investmentweekly.com/thumbnail.jpg', false, '매주 수요일', false, false, NULL),
    (7, 'techinnovations@example.com', 'Tech Innovations', 'Innovations and breakthroughs in tech.', 'IT_TECH', 'http://techinnovations.com', 'http://techinnovations.com/subscription', 'http://techinnovations.com/thumbnail.jpg', false, '매주 수요일', false, false, NULL),
    (8, 'fitnessdaily@example.com', 'Fitness Daily', 'Daily fitness routines and tips.', 'ECONOMY', 'http://fitnessdaily.com', 'http://fitnessdaily.com/subscription', 'http://fitnessdaily.com/thumbnail.jpg', false, '매주 수요일', false, false, NULL),
    (9, 'marketwatch@example.com', 'Market Watch', 'Daily market insights and analysis.', 'BUSINESS_FINANCIAL_TECHNOLOGY', 'http://marketwatch.com', 'http://marketwatch.com/subscription', 'http://marketwatch.com/thumbnail.jpg', false, '매주 수요일', false, false, NULL),
    (10, 'techtoday@example.com', 'Tech Today', 'Today’s top tech news.', 'IT_TECH', 'http://techtoday.com', 'http://techtoday.com/subscription', 'http://techtoday.com/thumbnail.jpg', false, '매주 수요일', false, false, NULL),
    (11, 'nutritionguide@example.com', 'Nutrition Guide', 'Guides to better nutrition.', 'ECONOMY', 'http://nutritionguide.com', 'http://nutritionguide.com/subscription', 'http://nutritionguide.com/thumbnail.jpg', false, '매주 수요일', false, false, NULL),
    (12, 'cryptocorner@example.com', 'Crypto Corner', 'All about cryptocurrency.', 'BUSINESS_FINANCIAL_TECHNOLOGY', 'http://cryptocorner.com', 'http://cryptocorner.com/subscription', 'http://cryptocorner.com/thumbnail.jpg', false, '매주 수요일', false, false, NULL),
    (13, 'futuretech@example.com', 'Future Tech', 'Future tech trends and predictions.', 'IT_TECH', 'http://futuretech.com', 'http://futuretech.com/subscription', 'http://futuretech.com/thumbnail.jpg', false, '매주 수요일', false, false, NULL),
    (14, 'mentalhealth@example.com', 'Mental Health', 'Mental health awareness and tips.', 'ECONOMY', 'http://mentalhealthmatters.com', 'http://mentalhealthmatters.com/subscription', 'http://mentalhealthmatters.com/thumbnail.jpg', false, '매주 수요일', false, false, NULL),
    (15, 'financeinsights@example.com', 'Finance Insights', 'Insights into financial markets.', 'BUSINESS_FINANCIAL_TECHNOLOGY', 'http://financeinsights.com', 'http://financeinsights.com/subscription', 'http://financeinsights.com/thumbnail.jpg', false, '매주 수요일', false, false, NULL),
    (16, 'techtrends@example.com', 'Tech Trends', 'Current trends in technology.', 'IT_TECH', 'http://techtrends.com', 'http://techtrends.com/subscription', 'http://techtrends.com/thumbnail.jpg', false, '매주 수요일', false, false, NULL),
    (17, 'healthyliving@example.com', 'Healthy Living', 'Tips for a healthier lifestyle.', 'ECONOMY', 'http://healthyliving.com', 'http://healthyliving.com/subscription', 'http://healthyliving.com/thumbnail.jpg', false, '매주 수요일', false, false, NULL),
    (18, 'stocksandbonds@example.com', 'Stocks and Bonds', 'News on stocks and bonds.', 'BUSINESS_FINANCIAL_TECHNOLOGY', 'http://stocksandbonds.com', 'http://stocksandbonds.com/subscription', 'http://stocksandbonds.com/thumbnail.jpg', false, '매주 수요일', false, false, NULL),
    (19, 'techreview@example.com', 'Tech Review', 'In-depth tech reviews.', 'IT_TECH', 'http://techreview.com', 'http://techreview.com/subscription', 'http://techreview.com/thumbnail.jpg', false, '매주 수요일', false, false, NULL),
    (20, 'medicalnewstoday@example.com', 'Medical News Today', 'Latest in medical news.', 'ECONOMY', 'http://medicalnewstoday.com', 'http://medicalnewstoday.com/subscription', 'http://medicalnewstoday.com/thumbnail.jpg', false, '매주 수요일', false, false, NULL),
    (21, 'financeweekly@example.com', 'Finance Weekly', 'Weekly financial news and tips.', 'BUSINESS_FINANCIAL_TECHNOLOGY', 'http://financeweekly.com', 'http://financeweekly.com/subscription', 'http://financeweekly.com/thumbnail.jpg', false, '매주 수요일', false, false, NULL),
    (22, 'gadgetupdates@example.com', 'Gadget Updates', 'Updates on the latest gadgets.', 'IT_TECH', 'http://gadgetupdates.com', 'http://gadgetupdates.com/subscription', 'http://gadgetupdates.com/thumbnail.jpg', false, '매주 수요일', false, false, NULL),
    (23, 'healthdigest@example.com', 'Health Digest', 'Daily health news and articles.', 'ECONOMY', 'http://healthdigest.com', 'http://healthdigest.com/subscription', 'http://healthdigest.com/thumbnail.jpg', false, '매주 수요일', false, false, NULL),
    (24, 'cryptoweekly@example.com', 'Crypto Weekly', 'Weekly cryptocurrency news.', 'BUSINESS_FINANCIAL_TECHNOLOGY', 'http://cryptoweekly.com', 'http://cryptoweekly.com/subscription', 'http://cryptoweekly.com/thumbnail.jpg', false, '매주 수요일', false, false, NULL),
    (25, 'technews@example.com', 'Tech News', 'Daily tech news updates.', 'IT_TECH', 'http://technews.com', 'http://technews.com/subscription', 'http://technews.com/thumbnail.jpg', false, '매주 수요일', false, false, NULL),
    (26, 'wellnessweekly@example.com', 'Wellness Weekly', 'Weekly wellness tips.', 'ECONOMY', 'http://wellnessweekly.com', 'http://wellnessweekly.com/subscription', 'http://wellnessweekly.com/thumbnail.jpg', false, '매주 수요일', false, false, NULL),
    (27, 'investmentdaily@example.com', 'Investment Daily', 'Daily investment tips and news.', 'BUSINESS_FINANCIAL_TECHNOLOGY', 'http://investmentdaily.com', 'http://investmentdaily.com/subscription', 'http://investmentdaily.com/thumbnail.jpg', false, '매주 수요일', false, false, NULL),
    (28, 'innovationinsights@example.com', 'Innovation Insights', 'Insights into the latest tech innovations.', 'IT_TECH', 'http://innovationinsights.com', 'http://innovationinsights.com/subscription', 'http://innovationinsights.com/thumbnail.jpg', false, '매주 수요일', false, false, NULL),
    (29, 'healthyhabits@example.com', 'Healthy Habits', 'Habits for a healthier life.', 'ECONOMY', 'http://healthyhabits.com', 'http://healthyhabits.com/subscription', 'http://healthyhabits.com/thumbnail.jpg', false, '매주 수요일', false, false, NULL),
    (30, 'financialtimes@example.com', 'Financial Times', 'Financial news and market analysis.', 'BUSINESS_FINANCIAL_TECHNOLOGY', 'http://financialtimes.com', 'http://financialtimes.com/subscription', 'http://financialtimes.com/thumbnail.jpg', false, '매주 수요일', false, false, NULL);



INSERT INTO article (id, newsletter_email, user_email, title, thumbnail_url, content_url, reading_time, received_at, content_summary, newsletter_nickname, is_deleted, created_at) VALUES
   (1, 'techweekly@example.com', 'test@gmail.com', '테스트 아티클1 2024', 'http://techweekly.com/thumbnails/trends2024.jpg', 'http://techweekly.com/articles/trends2024', 5, '2024-05-01', 'content test1', 'w',  false, '2024-05-01'),
   (2, 'techweekly@example.com', 'test@gmail.com', '테스트 아티클2 2022', 'http://tecsdffe.com/thumbnails/trends2024.jpg', 'http://techweekly.com/articles/trends2022', 5, '2024-04-05', 'content test1' , 'b', false, '2024-05-02'),
   (3, 'healthinsights@example.com', 'test@gmail.com', '테스트 아티클3 Living Tips', 'http://healthinsights.com/thumbnails/healthyliving.jpg', 'http://healthinsights.com/articles/healthyliving', 3, '2024-05-02', 'content test2', 'c', false, '2024-05-01'),
   (4, 'financedaily@example.com', 'user2@example.com', 'Stock Market Analysis', 'http://financedaily.com/thumbnails/stockmarket.jpg', 'http://financedaily.com/articles/stockmarket', 7, '2024-05-03', 'content test3', 'd' , false, '2024-05-01'),
   (5, 'gadgetworld@example.com', 'user4@example.com', 'Latest Gadgets', 'http://gadgetworld.com/thumbnails/latestgadgets.jpg', 'http://gadgetworld.com/articles/latestgadgets', 2, '2024-01-01', 'content test4' ,'e', false, '2024-05-01'),
   (6, 'healthwellness@example.com', 'user5@example.com', 'Wellness Tips', 'http://healthwellness.com/thumbnails/wellnesstips.jpg', 'http://healthwellness.com/articles/wellnesstips', 4, '2024-08-01', 'content test5','f', false, '2024-05-01'),
   (7, 'healthwellness@example.com', 'user6@example.com', 'Test Article', 'http://test.com/thumbnails/wellnesstips.jpg', 'http://test.com/articles/wellnesstips', 10, '2024-07-01', 'content test6', 'g', false, '2024-05-01'),
   (12, 'healthwellness@example.com', 'user3@gmail.com', '테스트 1번 Article', 'http://test.com/thumbnails/wellnesstips.jpg', 'http://test.com/articles/wellnesstips', 12, '2024-06-04', 'content test6', 'g', false, '2024-05-02'),
   (13, 'healthwellness@example.com', 'user3@gmail.com', '테스트 2번 Article', 'http://test.com/thumbnails/wellnesstips.jpg', 'http://test.com/articles/wellnesstips', 10, '2024-07-01', 'content test6', 'g', false, '2024-05-02');

INSERT INTO article (id, newsletter_email, user_email, title, thumbnail_url, content_url, reading_time, received_at, content_summary, newsletter_nickname, is_deleted, created_at) VALUES
(8, 'techweekly@example.com', 'user3@gmail.com', 'Tech Trends 2024', 'http://techweekly.com/thumbnails/trends2024.jpg', 'http://techweekly.com/articles/trends2024', 5, '2024-05-28', 'content test1', 'a',  false, '2024-05-01'),
(9, 'techweekly@example.com', 'user2@gmail.com', 'Tech Trends 2022', 'http://tecsdffe.com/thumbnails/trends2024.jpg', 'http://techweekly.com/articles/trends2022', 5, '2024-05-28', 'content test1' , 'b', false, '2024-05-02'),
(10, 'healthinsights@example.com', 'user2@gmail.com', 'Healthy Living Tips', 'http://healthinsights.com/thumbnails/healthyliving.jpg', 'http://healthinsights.com/articles/healthyliving', 3, '2024-05-28', 'content test2', 'c', false, '2024-05-01'),
(11, 'financedaily@example.com', 'user2@gmail.com', 'Stock Market Analysis', 'http://financedaily.com/thumbnails/stockmarket.jpg', 'http://financedaily.com/articles/stockmarket', 7, '2024-05-28', 'content test3', 'd' , false, '2024-05-01'),
(15, 'techweekly@example.com', 'test@gmail.com', '테스트 아티클 15Tech Trends 2024', 'http://techweekly.com/thumbnails/trends2024.jpg', 'http://techweekly.com/articles/trends2024', 5, '2024-05-28', 'content test1', 'a',  false, '2024-05-01');

INSERT INTO bookmark (id, user_email) VALUES
    (100, 'user3@gmail.com');

INSERT INTO bookmark_article_ids (bookmark_id, article_id) VALUES
                                                               (100, 8),
                                                               (100, 13),
                                                               (100, 12);