create table levels
(
    id          BIGSERIAL NOT NULL PRIMARY KEY,
    description TEXT      NOT NULL,

    UNIQUE (description)
);
insert into levels(description) values ('Beginner');
insert into levels(description) values ('Elementary');
insert into levels(description) values ('Intermediate');
insert into levels(description) values ('Upper-Intermediate');
insert into levels(description) values ('Advanced');
insert into levels(description) values ('Proficiency');
