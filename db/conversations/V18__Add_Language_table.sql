alter table conv_details add column language_id BIGSERIAL;
alter table conv_details add foreign key (language_id) references languages (language_id);