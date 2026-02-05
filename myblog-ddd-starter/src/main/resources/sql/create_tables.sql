CREATE DATABASE IF NOT EXISTS myblog;
USE myblog;

CREATE TABLE IF NOT EXISTS myblog_user_task (
    task_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL,
    created_time DATETIME,
    updated_time DATETIME,
    deadline DATETIME,
    description VARCHAR(255),
    alarm VARCHAR(255),
    PRIMARY KEY (task_id),
    INDEX idx_user_id (user_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE IF NOT EXISTS myblog_china_city_code (
    name VARCHAR(255) NOT NULL,
    ad_code INT NOT NULL,
    city_code INT,
    PRIMARY KEY (ad_code)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE IF NOT EXISTS myblog_user_info (
    id BIGINT NOT NULL,
    username VARCHAR(30) NOT NULL,
    password VARCHAR(255) NOT NULL,
    sex VARCHAR(1),
    birthday DATE,
    location VARCHAR(255),
    skills VARCHAR(255),
    feelings VARCHAR(255),
    description VARCHAR(255),
    PRIMARY KEY (id),
    UNIQUE KEY uk_username (username)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
