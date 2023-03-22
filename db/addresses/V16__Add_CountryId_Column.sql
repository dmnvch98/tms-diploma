alter table addresses add column country_id BIGSERIAL;
alter table addresses add foreign key (country_id) references countries (country_id);
