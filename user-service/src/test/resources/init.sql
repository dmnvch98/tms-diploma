create table countries
(
    country_id  BIGSERIAL NOT NULL PRIMARY KEY,
    description TEXT      NOT NULL,

    UNIQUE (description)
);
create table users
(
    id            BIGSERIAL NOT NULL PRIMARY KEY,
    first_name    TEXT      NOT NULL,
    last_name     TEXT      NOT NULL,
    email         TEXT      NOT NULL,
    password      TEXT      NOT NULL,
    nationality   BIGSERIAL NOT NULL,
    location      TEXT,
    roles         TEXT[]    NOT NULL,
    gender        TEXT      NOT NULL,
    refresh_token TEXT,
    avatar_name   TEXT,

    UNIQUE (email),
    FOREIGN KEY (nationality) REFERENCES countries (country_id)
);

create table tutors
(
    tutor_id BIGSERIAL NOT NULL PRIMARY KEY,
    user_id  BIGSERIAL NOT NULL,
    about_me TEXT,
    UNIQUE (user_id),
    FOREIGN KEY (user_id) REFERENCES users (id)
);

create table students
(
    student_id BIGSERIAL NOT NULL PRIMARY KEY,
    user_id    BIGSERIAL NOT NULL,
    about_me   TEXT,
    UNIQUE (user_id),
    FOREIGN KEY (user_id) REFERENCES users (id)
);


insert into countries(description)
values ('Austria');
insert into countries(description)
values ('Belarus');
insert into countries(description)
values ('Belgium');
insert into countries(description)
values ('Bulgaria');
insert into countries(description)
values ('Croatia');
insert into countries(description)
values ('Republic of Cyprus');
insert into countries(description)
values ('Czech Republic');
insert into countries(description)
values ('Denmark');
insert into countries(description)
values ('Estonia');
insert into countries(description)
values ('France');
insert into countries(description)
values ('Germany');
insert into countries(description)
values ('Greece');
insert into countries(description)
values ('Hungary');
insert into countries(description)
values ('Ireland');
insert into countries(description)
values ('Italy');
insert into countries(description)
values ('Latvia');
insert into countries(description)
values ('Lithuania');
insert into countries(description)
values ('Luxembourg');
insert into countries(description)
values ('Malta');
insert into countries(description)
values ('Netherlands');
insert into countries(description)
values ('Poland');
insert into countries(description)
values ('Portugal');
insert into countries(description)
values ('Romania');
insert into countries(description)
values ('Russia');
insert into countries(description)
values ('Slovakia');
insert into countries(description)
values ('Slovenia');
insert into countries(description)
values ('Spain');
insert into countries(description)
values ('Sweden');
insert into countries(description)
values ('Ukraine');


