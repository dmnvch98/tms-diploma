create table conv_statuses
(
    conv_status_id BIGSERIAL NOT NULL PRIMARY KEY,
    description    TEXT      NOT NULL,

    UNIQUE (description)
);

insert into conv_statuses(description) values ('Scheduled');
insert into conv_statuses(description) values ('Not Scheduled');
insert into conv_statuses(description) values ('Cancelled');
insert into conv_statuses(description) values ('In Process');
insert into conv_statuses(description) values ('Finished');