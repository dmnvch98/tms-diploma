create table tutors
(
    tutor_id BIGSERIAL NOT NULL PRIMARY KEY,
    user_id  BIGSERIAL NOT NULL,
    about_me TEXT,
    presentation_file_name TEXT,
    UNIQUE (user_id),
    FOREIGN KEY (user_id) REFERENCES users (id)
);

INSERT INTO tutors (user_id)
VALUES (1), (3), (6), (8), (10), (11), (13), (17), (18), (19);
