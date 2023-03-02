create table conv_details
(
    conv_details_id BIGSERIAL NOT NULL PRIMARY KEY,
    tutor_id BIGSERIAL NOT NULL,
    conv_type_id BIGSERIAL NOT NULL,
    price NUMERIC NOT NULL,
    address_id BIGSERIAL NOT NULL,
    min_lang_level_id BIGSERIAL NOT NULL,
    start_date TIMESTAMP NOT NULL,
    end_date TIMESTAMP NOT NULL,

    FOREIGN KEY (tutor_id) REFERENCES tutors(tutor_id),
    FOREIGN KEY (conv_type_id) REFERENCES conversation_types(conv_type_id),
    FOREIGN KEY (address_id) REFERENCES addresses(address_id),
    FOREIGN KEY (min_lang_level_id) REFERENCES language_levels(language_level_id)
)