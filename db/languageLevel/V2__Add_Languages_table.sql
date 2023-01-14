create table languages
(
    id          BIGSERIAL NOT NULL PRIMARY KEY,
    description TEXT      NOT NULL,

    UNIQUE (description)
);
