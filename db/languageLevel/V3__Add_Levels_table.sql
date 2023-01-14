create table levels
(
    id          BIGSERIAL NOT NULL PRIMARY KEY,
    description TEXT      NOT NULL,

    UNIQUE (description)
);