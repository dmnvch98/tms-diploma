create table languages
(
    language_id          BIGSERIAL NOT NULL PRIMARY KEY,
    description TEXT      NOT NULL,

    UNIQUE (description)
);

insert into languages(description) values ('Belarusian');
insert into languages(description) values ('Bulgarian');
insert into languages(description) values ('Croatian');
insert into languages(description) values ('Czech');
insert into languages(description) values ('Danish');
insert into languages(description) values ('Dutch');
insert into languages(description) values ('English');
insert into languages(description) values ('Estonian');
insert into languages(description) values ('Finnish');
insert into languages(description) values ('French');
insert into languages(description) values ('German');
insert into languages(description) values ('Greek');
insert into languages(description) values ('Hungarian');
insert into languages(description) values ('Irish');
insert into languages(description) values ('Italian');
insert into languages(description) values ('Latvian');
insert into languages(description) values ('Lithuanian');
insert into languages(description) values ('Maltese');
insert into languages(description) values ('Polish');
insert into languages(description) values ('Portuguese');
insert into languages(description) values ('Romanian');
insert into languages(description) values ('Russian');
insert into languages(description) values ('Slovak');
insert into languages(description) values ('Slovenian');
insert into languages(description) values ('Spanish');
insert into languages(description) values ('Swedish');
insert into languages(description) values ('Ukrainian');
