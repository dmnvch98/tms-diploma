create table conversation_types
(
    id BIGSERIAL NOT NULL PRIMARY KEY,
    description TEXT NOT NULL,

    UNIQUE (description)
);

insert into conversation_types (description)
values ('Offline');
insert into conversation_types (description)
values ('Online');