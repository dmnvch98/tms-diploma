create table user_language_levels
(
    id                BIGSERIAL NOT NULL PRIMARY KEY,
    user_id           BIGSERIAL NOT NULL,
    language_level_id BIGSERIAL NOT NULL,

    UNIQUE (user_id, language_level_id),
    FOREIGN KEY (user_id) REFERENCES users (id),
    FOREIGN KEY (language_level_id) REFERENCES language_levels (language_level_id)
);

insert into user_language_levels (user_id, language_level_id) values (31, 7);
insert into user_language_levels (user_id, language_level_id) values (35, 77);
insert into user_language_levels (user_id, language_level_id) values (19, 6);
insert into user_language_levels (user_id, language_level_id) values (29, 6);
insert into user_language_levels (user_id, language_level_id) values (37, 24);
insert into user_language_levels (user_id, language_level_id) values (27, 135);
insert into user_language_levels (user_id, language_level_id) values (32, 56);
insert into user_language_levels (user_id, language_level_id) values (24, 31);
insert into user_language_levels (user_id, language_level_id) values (20, 97);
insert into user_language_levels (user_id, language_level_id) values (27, 12);
insert into user_language_levels (user_id, language_level_id) values (22, 100);
insert into user_language_levels (user_id, language_level_id) values (31, 53);
insert into user_language_levels (user_id, language_level_id) values (25, 65);
insert into user_language_levels (user_id, language_level_id) values (18, 59);
insert into user_language_levels (user_id, language_level_id) values (24, 58);
insert into user_language_levels (user_id, language_level_id) values (20, 8);
insert into user_language_levels (user_id, language_level_id) values (36, 23);
insert into user_language_levels (user_id, language_level_id) values (29, 135);
insert into user_language_levels (user_id, language_level_id) values (29, 86);
insert into user_language_levels (user_id, language_level_id) values (33, 82);
insert into user_language_levels (user_id, language_level_id) values (32, 38);
insert into user_language_levels (user_id, language_level_id) values (31, 90);
insert into user_language_levels (user_id, language_level_id) values (27, 110);
insert into user_language_levels (user_id, language_level_id) values (31, 12);
insert into user_language_levels (user_id, language_level_id) values (23, 75);