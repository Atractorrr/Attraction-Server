-- 테이블이 이미 존재하는 경우 삭제
DROP TABLE IF EXISTS newsletter_ids;
DROP TABLE IF EXISTS subscription;
DROP TABLE IF EXISTS interests;
DROP TABLE IF EXISTS newsletter;

CREATE TABLE newsletter_ids (
    subscribe_id BIGINT,
    newsletter_id BIGINT,
    PRIMARY KEY (subscribe_id, newsletter_id)
);


CREATE TABLE interests (
   email VARCHAR(255) NOT NULL,
   interests VARCHAR(255) NOT NULL,
   PRIMARY KEY (email, interests)
);

CREATE TABLE IF NOT EXISTS newsletter
(
    id                        BIGINT AUTO_INCREMENT PRIMARY KEY,
    category                  VARCHAR(255),
    created_at                TIMESTAMP,
    description               VARCHAR(255),
    email                     VARCHAR(255),
    is_deleted                BOOLEAN,
    main_link                 VARCHAR(255),
    modified_at               TIMESTAMP,
    name                      VARCHAR(255),
    nickname                  VARCHAR(255),
    subscribe_link            VARCHAR(255),
    thumbnail_url             VARCHAR(255),
    prev_article_list_url     VARCHAR(255),
    upload_days               VARCHAR(255),
    has_confirmation_email    BOOLEAN,
    is_auto_subscribe_enabled BOOLEAN,
    is_agree_ad_info_reception BOOLEAN,
    is_agree_personal_info_collection BOOLEAN
);


INSERT INTO `interests` (email, interests) VALUES
    ('test1@gmail.com', 'LIVING_INTERIOR'),
    ('test1@gmail.com', 'FOOD'),
    ('test1@gmail.com', 'LOCAL_TRAVEL'),
    ('test1@gmail.com', 'HOBBY_SELF_DEVELOPMENT'),
    ('test2@gmail.com', 'LIVING_INTERIOR'),
    ('test2@gmail.com', 'IT_TECH'),
    ('test2@gmail.com', 'BUSINESS_FINANCIAL_TECHNOLOGY'),
    ('test2@gmail.com', 'CURRENT_AFFAIRS_SOCIETY'),
    ('test3@gmail.com', 'DESIGN'),
    ('test3@gmail.com', 'TREND_LIFE'),
    ('test3@gmail.com', 'LOCAL_TRAVEL');

INSERT INTO `newsletter_ids` (subscribe_id, newsletter_id) VALUES
    (2, 6),
    (2, 18),
    (2, 44),
    (2, 50),
    (2, 62),
    (3, 28),
    (3, 31);
