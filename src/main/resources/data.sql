SET FOREIGN_KEY_CHECKS=0;
TRUNCATE TABLE article;
TRUNCATE TABLE newsletter;
TRUNCATE TABLE in_box;
TRUNCATE TABLE read_box;
SET FOREIGN_KEY_CHECKS=1;


INSERT INTO newsletter (id, name, description, category, main_link, subscription_link, thumbnail) VALUES
      (1, 'Tech Weekly', 'Weekly newsletter about the latest in tech.', 'IT_TECH', 'http://techweekly.com', 'http://techweekly.com/subscribe', 'http://techweekly.com/thumbnail.jpg'),
      (2, 'Health Insights', 'Daily health tips and news.', 'HEALTH_MEDICINE', 'http://healthinsights.com', 'http://healthinsights.com/subscribe', 'http://healthinsights.com/thumbnail.jpg'),
      (3, 'Finance Daily', 'Daily updates on the stock market.', 'BUSINESS_FINANCIAL_TECHNOLOGY', 'http://financedaily.com', 'http://financedaily.com/subscribe', 'http://financedaily.com/thumbnail.jpg'),
      (4, 'Gadget World', 'Latest news on gadgets and electronics.', 'IT_TECH', 'http://gadgetworld.com', 'http://gadgetworld.com/subscribe', 'http://gadgetworld.com/thumbnail.jpg'),
      (5, 'Health and Wellness', 'Health and wellness tips.', 'HEALTH_MEDICINE', 'http://healthwellness.com', 'http://healthwellness.com/subscribe', 'http://healthwellness.com/thumbnail.jpg'),
      (6, 'Investment Weekly', 'Weekly investment tips and news.', 'BUSINESS_FINANCIAL_TECHNOLOGY', 'http://investmentweekly.com', 'http://investmentweekly.com/subscribe', 'http://investmentweekly.com/thumbnail.jpg'),
      (7, 'Tech Innovations', 'Innovations and breakthroughs in tech.', 'IT_TECH', 'http://techinnovations.com', 'http://techinnovations.com/subscribe', 'http://techinnovations.com/thumbnail.jpg'),
      (8, 'Fitness Daily', 'Daily fitness routines and tips.', 'HEALTH_MEDICINE', 'http://fitnessdaily.com', 'http://fitnessdaily.com/subscribe', 'http://fitnessdaily.com/thumbnail.jpg'),
      (9, 'Market Watch', 'Daily market insights and analysis.', 'BUSINESS_FINANCIAL_TECHNOLOGY', 'http://marketwatch.com', 'http://marketwatch.com/subscribe', 'http://marketwatch.com/thumbnail.jpg'),
      (10, 'Tech Today', 'Today’s top tech news.', 'IT_TECH', 'http://techtoday.com', 'http://techtoday.com/subscribe', 'http://techtoday.com/thumbnail.jpg'),
      (11, 'Nutrition Guide', 'Guides to better nutrition.', 'HEALTH_MEDICINE', 'http://nutritionguide.com', 'http://nutritionguide.com/subscribe', 'http://nutritionguide.com/thumbnail.jpg'),
      (12, 'Crypto Corner', 'All about cryptocurrency.', 'BUSINESS_FINANCIAL_TECHNOLOGY', 'http://cryptocorner.com', 'http://cryptocorner.com/subscribe', 'http://cryptocorner.com/thumbnail.jpg'),
      (13, 'Future Tech', 'Future tech trends and predictions.', 'IT_TECH', 'http://futuretech.com', 'http://futuretech.com/subscribe', 'http://futuretech.com/thumbnail.jpg'),
      (14, 'Mental Health', 'Mental health awareness and tips.', 'HEALTH_MEDICINE', 'http://mentalhealthmatters.com', 'http://mentalhealthmatters.com/subscribe', 'http://mentalhealthmatters.com/thumbnail.jpg'),
      (15, 'Finance Insights', 'Insights into financial markets.', 'BUSINESS_FINANCIAL_TECHNOLOGY', 'http://financeinsights.com', 'http://financeinsights.com/subscribe', 'http://financeinsights.com/thumbnail.jpg'),
      (16, 'Tech Trends', 'Current trends in technology.', 'IT_TECH', 'http://techtrends.com', 'http://techtrends.com/subscribe', 'http://techtrends.com/thumbnail.jpg'),
      (17, 'Healthy Living', 'Tips for a healthier lifestyle.', 'HEALTH_MEDICINE', 'http://healthyliving.com', 'http://healthyliving.com/subscribe', 'http://healthyliving.com/thumbnail.jpg'),
      (18, 'Stocks and Bonds', 'News on stocks and bonds.', 'BUSINESS_FINANCIAL_TECHNOLOGY', 'http://stocksandbonds.com', 'http://stocksandbonds.com/subscribe', 'http://stocksandbonds.com/thumbnail.jpg'),
      (19, 'Tech Review', 'In-depth tech reviews.', 'IT_TECH', 'http://techreview.com', 'http://techreview.com/subscribe', 'http://techreview.com/thumbnail.jpg'),
      (20, 'Medical News Today', 'Latest in medical news.', 'HEALTH_MEDICINE', 'http://medicalnewstoday.com', 'http://medicalnewstoday.com/subscribe', 'http://medicalnewstoday.com/thumbnail.jpg'),
      (21, 'Finance Weekly', 'Weekly financial news and tips.', 'BUSINESS_FINANCIAL_TECHNOLOGY', 'http://financeweekly.com', 'http://financeweekly.com/subscribe', 'http://financeweekly.com/thumbnail.jpg'),
      (22, 'Gadget Updates', 'Updates on the latest gadgets.', 'IT_TECH', 'http://gadgetupdates.com', 'http://gadgetupdates.com/subscribe', 'http://gadgetupdates.com/thumbnail.jpg'),
      (23, 'Health Digest', 'Daily health news and articles.', 'HEALTH_MEDICINE', 'http://healthdigest.com', 'http://healthdigest.com/subscribe', 'http://healthdigest.com/thumbnail.jpg'),
      (24, 'Crypto Weekly', 'Weekly cryptocurrency news.', 'BUSINESS_FINANCIAL_TECHNOLOGY', 'http://cryptoweekly.com', 'http://cryptoweekly.com/subscribe', 'http://cryptoweekly.com/thumbnail.jpg'),
      (25, 'Tech News', 'Daily tech news updates.', 'IT_TECH', 'http://technews.com', 'http://technews.com/subscribe', 'http://technews.com/thumbnail.jpg'),
      (26, 'Wellness Weekly', 'Weekly wellness tips.', 'HEALTH_MEDICINE', 'http://wellnessweekly.com', 'http://wellnessweekly.com/subscribe', 'http://wellnessweekly.com/thumbnail.jpg'),
      (27, 'Investment Daily', 'Daily investment tips and news.', 'BUSINESS_FINANCIAL_TECHNOLOGY', 'http://investmentdaily.com', 'http://investmentdaily.com/subscribe', 'http://investmentdaily.com/thumbnail.jpg'),
      (28, 'Innovation Insights', 'Insights into the latest tech innovations.', 'IT_TECH', 'http://innovationinsights.com', 'http://innovationinsights.com/subscribe', 'http://innovationinsights.com/thumbnail.jpg'),
      (29, 'Healthy Habits', 'Habits for a healthier life.', 'HEALTH_MEDICINE', 'http://healthyhabits.com', 'http://healthyhabits.com/subscribe', 'http://healthyhabits.com/thumbnail.jpg'),
      (30, 'Financial Times', 'Financial news and market analysis.', 'BUSINESS_FINANCIAL_TECHNOLOGY', 'http://financialtimes.com', 'http://financialtimes.com/subscribe', 'http://financialtimes.com/thumbnail.jpg'),
      (31, 'Tech Buzz', 'Buzz in the tech world.', 'IT_TECH', 'http://techbuzz.com', 'http://techbuzz.com/subscribe', 'http://techbuzz.com/thumbnail.jpg'),
      (32, 'Mind and Body', 'Wellness tips for mind and body.', 'HEALTH_MEDICINE', 'http://mindandbody.com', 'http://mindandbody.com/subscribe', 'http://mindandbody.com/thumbnail.jpg'),
      (33, 'Market Trends', 'Trends in the financial markets.', 'BUSINESS_FINANCIAL_TECHNOLOGY', 'http://markettrends.com', 'http://markettrends.com/subscribe', 'http://markettrends.com/thumbnail.jpg'),
      (34, 'Tech Insights', 'Insights into the tech industry.', 'IT_TECH', 'http://techinsights.com', 'http://techinsights.com/subscribe', 'http://techinsights.com/thumbnail.jpg'),
      (35, 'Fitness Focus', 'Focus on fitness and health.', 'HEALTH_MEDICINE', 'http://fitnessfocus.com', 'http://fitnessfocus.com/subscribe', 'http://fitnessfocus.com/thumbnail.jpg');

INSERT INTO article (id, newsletter_id, title, thumbnail, content, content_url, reading_time, received_at) VALUES
   (4, 1, 'Tech Trends 2024', 'http://techweekly.com/thumbnails/trends2024.jpg', "content test1", 'http://techweekly.com/articles/trends2024', 5, '2024-05-01'),
   (5, 2, 'Healthy Living Tips', 'http://healthinsights.com/thumbnails/healthyliving.jpg', "content test2", 'http://healthinsights.com/articles/healthyliving', 3, '2024-05-02'),
   (6, 3, 'Stock Market Analysis', 'http://financedaily.com/thumbnails/stockmarket.jpg', "content test3", 'http://financedaily.com/articles/stockmarket', 7, '2024-05-03'),
   (7, 4, 'Latest Gadgets', 'http://gadgetworld.com/thumbnails/latestgadgets.jpg', "content test4", 'http://gadgetworld.com/articles/latestgadgets', 2, '2024-01-01'),
   (8, 5, 'Wellness Tips', 'http://healthwellness.com/thumbnails/wellnesstips.jpg', "content test5", 'http://healthwellness.com/articles/wellnesstips', 4, '2024-08-01'),
   (9, 5, 'test article', 'http://test.com/thumbnails/wellnesstips.jpg', "content test6", 'http://test.com/articles/wellnesstips', 10, '2024-07-01');

INSERT INTO newsletter_upload_days (newsletter_id, upload_days) VALUES
    (1, 'MON'),
    (2, 'FRI'),
    (2, 'TUE'),
    (2, 'THU'),
    (3, 'WED'),
    (4, 'WED'),
    (5, 'WED');

-- InBox 데이터 삽입
INSERT INTO in_box (user_id, article_id) VALUES
     (1, 4),
     (1, 5),
     (1, 6),
     (1, 7),
     (1, 8),
     (1, 9);

-- Readbox 데이터 삽입
INSERT INTO read_box (user_id, article_id, percentage) VALUES
   (1, 4, 50),
   (1, 5, 60),
   (1, 6, 70),
   (1, 7, 80),
   (1, 8, 100),
   (1, 9, 100);
