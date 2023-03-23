create table tutors
(
    tutor_id BIGSERIAL NOT NULL PRIMARY KEY,
    user_id  BIGSERIAL NOT NULL,
    about_me TEXT,
    UNIQUE (user_id),
    FOREIGN KEY (user_id) REFERENCES users (id)
);

insert into tutors (user_id) values (34);
insert into tutors (user_id) values (35);
insert into tutors (user_id) values (36);
insert into tutors (user_id) values (18);
insert into tutors (user_id) values (20);
insert into tutors (user_id) values (23);
insert into tutors (user_id) values (25);
insert into tutors (user_id) values (27);
insert into tutors (user_id) values (28);
insert into tutors (user_id) values (30);