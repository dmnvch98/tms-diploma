create table countries
(
    country_id  BIGSERIAL NOT NULL PRIMARY KEY,
    description TEXT      NOT NULL,

    UNIQUE (description)
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
