create table user_language_levels
(
    id                BIGSERIAL NOT NULL PRIMARY KEY,
    user_id           BIGSERIAL NOT NULL,
    language_level_id BIGSERIAL NOT NULL,

    UNIQUE (user_id, language_level_id),
    FOREIGN KEY (user_id) REFERENCES users (id),
    FOREIGN KEY (language_level_id) REFERENCES language_levels (language_level_id)
);

insert into user_language_levels (user_id, language_level_id) values (1, 7);
insert into user_language_levels (user_id, language_level_id) values (2, 77);
insert into user_language_levels (user_id, language_level_id) values (3, 6);
insert into user_language_levels (user_id, language_level_id) values (4, 6);
insert into user_language_levels (user_id, language_level_id) values (5, 24);
insert into user_language_levels (user_id, language_level_id) values (6, 135);
insert into user_language_levels (user_id, language_level_id) values (7, 56);
insert into user_language_levels (user_id, language_level_id) values (8, 31);
insert into user_language_levels (user_id, language_level_id) values (9, 97);
insert into user_language_levels (user_id, language_level_id) values (10, 12);
insert into user_language_levels (user_id, language_level_id) values (11, 100);
insert into user_language_levels (user_id, language_level_id) values (12, 53);
insert into user_language_levels (user_id, language_level_id) values (13, 65);
insert into user_language_levels (user_id, language_level_id) values (14, 59);
insert into user_language_levels (user_id, language_level_id) values (15, 58);
insert into user_language_levels (user_id, language_level_id) values (16, 8);
insert into user_language_levels (user_id, language_level_id) values (17, 23);
insert into user_language_levels (user_id, language_level_id) values (18, 135);
insert into user_language_levels (user_id, language_level_id) values (19, 86);
insert into user_language_levels (user_id, language_level_id) values (20, 82);
insert into user_language_levels (user_id, language_level_id) values (1, 38);
insert into user_language_levels (user_id, language_level_id) values (2, 90);
insert into user_language_levels (user_id, language_level_id) values (3, 110);
insert into user_language_levels (user_id, language_level_id) values (4, 12);
insert into user_language_levels (user_id, language_level_id) values (5, 75);