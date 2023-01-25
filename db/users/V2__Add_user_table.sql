create table users
(
    id            BIGSERIAL NOT NULL PRIMARY KEY,
    first_name    TEXT      NOT NULL,
    last_name     TEXT      NOT NULL,
    email         TEXT      NOT NULL,
    password      TEXT      NOT NULL,
    nationality   BIGSERIAL NOT NULL,
    location      TEXT,
    roles         TEXT      NOT NULL,
    gender        TEXT      NOT NULL,
    refresh_token TEXT,

    UNIQUE (email),
    FOREIGN KEY (nationality) REFERENCES countries (country_id)
);
