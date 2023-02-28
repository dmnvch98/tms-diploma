create table addresses
(
    address_id BIGSERIAL NOT NULL PRIMARY KEY,
    city       TEXT      NOT NULL,
    address    TEXT,
    lat        TEXT      NOT NULL,
    long       TEXT      NOT NULL,
    tutor_id   BIGSERIAL NOT NULL,
    FOREIGN KEY (tutor_id) REFERENCES tutors (tutor_id)
)