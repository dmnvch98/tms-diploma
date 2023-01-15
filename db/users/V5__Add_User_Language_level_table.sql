create table user_language_levels
(
    id                BIGSERIAL NOT NULL PRIMARY KEY,
    user_id           BIGSERIAL NOT NULL,
    language_level_id BIGSERIAL NOT NULL,

    UNIQUE (user_id, language_level_id),
    FOREIGN KEY (user_id) REFERENCES users (id),
    FOREIGN KEY (language_level_id) REFERENCES language_levels (id)
);