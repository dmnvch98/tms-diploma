create table users
(
    id          BIGSERIAL NOT NULL PRIMARY KEY,
    first_name  TEXT      NOT NULL,
    last_name   TEXT      NOT NULL,
    email       TEXT      NOT NULL,
    password    TEXT      NOT NULL,
    nationality TEXT      NOT NULL,
    roles       TEXT      NOT NULL,

    UNIQUE (email)
);
