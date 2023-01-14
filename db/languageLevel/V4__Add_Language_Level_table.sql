create table language_level
(
    id          BIGSERIAL NOT NULL PRIMARY KEY,
    language_id BIGSERIAL NOT NULL,
    level_id    BIGSERIAL NOT NULL,

    UNIQUE (language_id, level_id),
    FOREIGN KEY (language_id) REFERENCES languages (id),
    FOREIGN KEY (level_id) REFERENCES levels (id)
);
