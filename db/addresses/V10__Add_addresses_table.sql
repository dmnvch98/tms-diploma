create table addresses
(
    address_id BIGSERIAL NOT NULL PRIMARY KEY,
    city       TEXT      NOT NULL,
    address    TEXT,
    lat        TEXT      NOT NULL,
    long       TEXT      NOT NULL,
    user_id    BIGSERIAL NOT NULL
)