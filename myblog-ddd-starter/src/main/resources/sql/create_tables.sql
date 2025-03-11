CREATE TABLE myblog_user_task (
    task_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL,
    created_time DATETIME,
    updated_time DATETIME,
    deadline DATETIME,
    description VARCHAR(255),
    alarm VARCHAR(255),
    PRIMARY KEY (task_id)
)

CREATE TABLE myblog_china_city_code (
    name VARCHAR(255) NOT NULL,
    ad_code int NOT NULL,
    city_code int,
    PRIMARY KEY (ad_code)
)

CREATE TABLE myblog_user_info (
    id BIGINT NOT NULL,
    username VARCHAR(30) NOT NULL,
    password VARCHAR(255) NOT NULL,
    sex VARCHAR(1),
    birthday date,
    location VARCHAR(255),
    skills VARCHAR(255),
    feelings VARCHAR(255),
    description VARCHAR(255),
    PRIMARY KEY (id)
)
